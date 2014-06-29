(ns hwo2014bot.replan
  (:require [hwo2014bot.plan :as p]))

(defn plan-dec [n]
  (if n (max 0 (dec n))
    0))

(defn data-sum [d]
  (+ (get-in d [:piecePosition :inPieceDistance] 0)
     (get-in d [:angle] 0)))

(defn plan-diff [m]
  (let [planed-d (first (get-in m [:race :my-plan]))
        actual-d (first (get-in m [:cars :me]))]
    (Math/abs (- (data-sum planed-d)
                 (data-sum actual-d)))))

(defn plan-good? [m eps] ;must be before plan-tick!
  (cond
    (empty? (get-in m [:race :my-plan]))
    (or (println "plan empty")
        (assoc-in m [:request :replan] true))
    (< eps (plan-diff m))
    (or (println "plan is bad with: " (plan-diff m))
        (println "got: " (first (get-in m [:cars :me])))
        (println "planed:" (first (get-in m [:race :my-plan])))
        (-> m
          (assoc-in [:request :replan] true)
          (update-in [:race :bad-plan-ticks] (fnil inc 0))))
    true (assoc-in m [:race :bad-plan-ticks] 0)))

(defn plan-tick [m]
  (-> m
    (update-in [:race :plan :valid-ticks] plan-dec)
    (update-in [:race :my-plan] rest)))

(defn should-replan? [m]
  (or 
    (get-in m [:request :replan]) ;replan is set .. plan is bad somehow
    (<= (get-in m [:race :plan :valid-ticks] 0) 0) ;valid-ticks expired
    (get-in m [:plans :left])
    (get-in m [:plans :right]) ;switch got planed
    ))

(defn turbo? [m]
  (and (get-in m [:cars :me 0 :turbo :ticks]) ;not nil 
       (not (zero? (get-in m [:cars :me 0 :turbo :ticks]))))) ;but not zero

(defn do-replan [m predict-size step-predict-size max-alter-steps]
  (let [sps (if (turbo? m) (* 2 step-predict-size) step-predict-size)] ;double stepsize while in turbo
    (-> m
      (assoc-in [:plans :throttle-plan]
                (p/create-plan m predict-size sps max-alter-steps)))))

(defn replan [m predict-size step-predict-size max-alter-steps]
  (if (should-replan? m)
    (or #_(println "replan") (do-replan m predict-size step-predict-size max-alter-steps))
    m))