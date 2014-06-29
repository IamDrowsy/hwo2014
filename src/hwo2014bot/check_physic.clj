(ns hwo2014bot.check-physic
  (:require [hwo2014bot.util :as u]
            [hwo2014bot.position :as pos]
            [hwo2014bot.physic :as phy]))

(defn pos [data]
  (:piecePosition data))

(defn pi [pos]
  (:pieceIndex pos))
(defn ipd [pos]
  (:inPieceDistance pos))

(defn physics-diff [p1 p2]
  (+ (Math/abs (if true
                 (- (get p1 :friction-factor 0.0) (get p2 :friction-factor 0.0))
                 0))
     (Math/abs (if true
                 (- (get p1 :throttle-factor 0.0) (get p2 :throttle-factor 0.0))
                 0))
     (Math/abs (if true  (- (get p1 :a-x5 1.0) (get p2 :a-x5 1.0))
                 0))
     (Math/abs (if true  (- (get p1 :a-x4 0.9) (get p2 :a-x4 0.9))
                  0))))

(defn merge-physic [old new]
  (println "phyisc-changed" old new)
  (merge old new))

(defn solve-d-x [d d-1 d-2 d-3 t-1 t-2]
  (/ (+ (* t-2 (- d d-1)) (* t-1 (- d-2 d-1)))
     (+ (* t-2 (- d-1 d-2)) (* t-1 (- d-3 d-2)))))

(defn solve-d-y [d d-1 d-2 d-3 t-1 t-2 x]
  (/ (- d-1 (+ d-2 (* x (- d-2 d-3))))
     t-2))

(defn solve-d [d d-1 d-2 d-3 t-1 t-2]
  (let [x (solve-d-x d d-1 d-2 d-3 t-1 t-2)
        y (solve-d-y d d-1 d-2 d-3 t-1 t-2 x)]
    {'y y 'x x}))

(defn to-d-physic [solv-erg]
  {:throttle-factor (get solv-erg 'y)
   :friction-factor (get solv-erg 'x)})

(defn check-d-physic [m [d d-1 d-2 d-3 :as datas]]
  (let [poss (map pos datas)
        pis (map pi poss)
        ts (map #(:throttle % 1.0) [d-2 d-1])
        args (into [] (concat (map ipd poss) ts))
        old-physic (get-in m [:race :physic] {})]
    (if (and (every? identity args) (apply = pis) ;all index have to be the same!
             (not-any? zero? ts)) ;no zero throttle
      (try
        (let [solved (apply solve-d args)]
          (let [new-physic (merge-physic old-physic (to-d-physic solved))]
            (assoc-in m [:race :physic] (assoc new-physic :checked-d true))))
        (catch Exception e
          (or (println e "error in solving d-physic!") m)))
      m)))

(defn- ff [a a-1 a-2]
  (- a a-1 (* 0.9 (- a-1 a-2))))

(defn- gf [agn s a-1]
  (- (/ (* s a-1) -800)
     (* agn s 0.75 0.4)))

(defn- hf [agn s]
  (* agn s s 0.75))

(defn solve-x3 [a+1 a a-1 a-2 s+1 s agn]
  (let [g+ (gf agn s+1 a) g (gf agn s a-1)
        h+ (hf agn s+1) h (hf agn s)
        f+ (ff a+1 a a-1) f (ff a a-1 a-2)]
    (/ (- (* f+ h) (* h+ f))
       (- (* g+ h) (* h+ g)))))

(defn solve-x5 [a a-1 a-2 s agn sqrR2 x3]
  (let [h (hf agn s)
        g (gf agn s a-1)
        f (ff a a-1 a-2)]
    (/ (* x3 h) (* sqrR2 (- f (* x3 g))))))

(defn solve-x4 [a a-1 a-2 a-3 s s-1 agn]
  (let [g' (gf s a-1) g (gf s-1 a-2)
        h' (hf agn s) h (hf agn s-1)
        f' (ff a-1 a-2) f (ff a-2 a-3)
        q (/ (* h' s) (* h s-1))]
    (/ (- a a-1 g' (* -0.4 h') (* q (- a-1 a-2 g (* -0.4 h))))
       (- f' (* q f)))))

(defn solve-a [a a-1 a-2 a-3 s s-1 agn sqrR2]
 (let [x3 (solve-x3 a a-1 a-2 a-3 s s-1 agn)
       x5 (solve-x5 a-1 a-2 a-3 s-1 agn sqrR2 x3)]
   {:a-x5 x5 :a-x3 x3}))

(defn check-a-physic [m datas]
  (let [poss (map pos datas)
        ags (map :angle datas)
        track (get-in m [:race :track])
        s (phy/speed track (nth poss 2) (second poss))
        s-1 (phy/speed track (nth poss 3) (nth poss 2))
        agn (u/sgn (pos/track-angle track (second poss)))
        sqrR2 (Math/sqrt (* 2 (pos/effectiv-radius track (second poss))))
        args (into [] (concat ags [s s-1 agn sqrR2]))
        old-physic (get-in m [:race :physic] {})]
    (if (and (every? identity args) (not-any? zero? (take 4 ags)))
      (try
        (let [solved (apply solve-a args)]
          (if (symbol? solved)
            m
            (assoc-in m [:race :physic] (assoc (merge-physic old-physic (apply solve-a args))
                                         :checked-a true))))
        (catch Exception e
          (or (println e "error in solving a-physic!") m)))
      m)))

(defn recalc-physic-on-bad-plans [m n]
  (if (>= (get-in m [:race :bad-plan-ticks] 0) n)
    (or (println "recalc-physic")
        (-> m
          (assoc-in [:race :physic :checked-a] false)
          (assoc-in [:race :physic :checked-d] false)))
    m))

(defn check-physic-once
  [m]
  (let [physic (get-in m [:race :phyisc] {})
        datas (get-in m [:cars :me])]
    (cond-> m
            (and (not (get-in m [:race :physic :checked-d])) (<= 4 (count datas)))
            (check-d-physic  (take 4 datas))
            (and (not (get-in m [:race :physic :checked-a])) (<= 4 (count datas)))
            (check-a-physic (take 4 datas)))))
