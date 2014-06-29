(ns hwo2014bot.physic
  (:require [hwo2014bot.position :as p]
            [hwo2014bot.track :as t]
            [hwo2014bot.cars :as c]
            [hwo2014bot.util :as u]))

(defn pos [data]
  (:piecePosition data))

(defn pi [pos]
  (:pieceIndex pos 0))

(defn ipd [pos]
  (:inPieceDistance pos 0))

(defn speed
  "Base physik formular of the speed
   for now its the diff between the last 2 positions"
  [track pos-2 pos-1]
   (if (= (pi pos-2) (pi pos-1))
     (- (ipd pos-1) (ipd pos-2))
     (let [l (p/effectiv-length track pos-2)]
       (+ (ipd pos-1) (- l (ipd pos-2))))))

;Position Forward

(defn next-d
  [{:keys [^Double throttle-factor ^Double friction-factor] :or {throttle-factor 0.2 friction-factor 0.98}}
   ^Double speed ^Double d-1 ^Double turbo ^Double throttle]
  (+ d-1 (* friction-factor speed)
     (* throttle-factor turbo throttle)))

#_(defn next-ipd
   [physic speed ipd-2 ipd-1 ipd-2-length ipd-1-length turbo throttle]
    (let [n (next-d physic speed ipd-1 turbo throttle)]
      (if (< n ipd-1-length) ;normal case
        n
        (- n ipd-1-length))))

(defn next-ipd-pi-lap
  [track next-d ipd-1-length pi-1 ipd-1 lap]
  (let [change-piece? (> next-d ipd-1-length)
        change-lap? (and change-piece? (= (inc pi-1) (t/piece-count track)))
        nipd (if change-piece? (- next-d ipd-1-length) next-d)
        npi (if change-piece? (mod (inc pi-1) (t/piece-count track)) pi-1)
        nlap (if change-lap? (inc lap) lap)]
    [nipd npi nlap]))

(defn next-lane*
  [track lane switch]
  (if switch
    (assoc lane :endLaneIndex (t/lane-switch track (:startLaneIndex lane) switch))
    (assoc lane :startLaneIndex (:endLaneIndex lane))))

(defn next-lane [track req-switch switching? npi pi-1 lane]
  (cond (and req-switch ;switch requested
             (not= npi pi-1) ;and we change piece
             (t/switch? track npi)) ;to a switch-piece
        (next-lane* track lane req-switch) ;-> init switching
        (and (not= npi pi-1) ;we change piece
             switching?) ; we are switching
        (next-lane* track lane false) ;-> stop switching
        :else
        lane))

(defn switching?
  [pos-1]
  (not= (get-in pos-1 [:lane :startLaneIndex])
        (get-in pos-1 [:lane :endLaneIndex])))

(defn next-pos
  [physic track pos-2 pos-1 turbo throttle req-switch]
  (let [sw? (switching? pos-1)
        ipd-1 (ipd pos-1)
        ipd-2 (ipd pos-2)
        pi-1 (pi pos-1)
        pi-2 (pi pos-2)
        lap (:lap pos-1)
        lane (:lane pos-1)
        l-1 (p/effectiv-length track pos-1)
        l-2 (p/effectiv-length track pos-2)
        speed (speed track pos-2 pos-1)
        nd (next-d physic speed ipd-1 turbo throttle)
        [nipd npi nlap] (next-ipd-pi-lap track nd l-1 pi-1 ipd-1 lap)
        nl(next-lane track req-switch sw? npi pi-1 lane)]
     {:pieceIndex npi
      :inPieceDistance nipd
      :lane nl
      :lap nlap}))

;Angle forward

(defn next-angle
  "Returns just the angle"
  [{:keys [^Double a-x5 ^Double a-x3] :or {a-x5 0.9 a-x3 1.2}}
   track ^Double a-2  ^Double a-1 pos-2 pos-1]
  (let [er (* (p/effectiv-radius track pos-1) 2)
        agn (u/sgn (p/track-angle track pos-1))
        s (speed track pos-2 pos-1)
        os (- (/ s (* a-x5 (Math/sqrt er)))
                    0.4)]
    (+ a-1;last angle
       (* 0.9 (- a-1 a-2));angle-speed
       (* a-x3 s a-1 (/ 1 -800))
       (* a-x3 (if (< 0 os)
                  (* agn s 0.75 os)
                  0)))))

;Data Forward

(defn add-turbo-or-switch [data input]
  (cond (:switch input) (assoc data :requested-switch (:switch input))
        (:turbo input) (c/turbo-start* data)
        true data))

(defn remove-switch [data pos-1 npos]
  (if (and (switching? npos) (not (switching? pos-1)))
    (assoc data :requested-switch false)
    data))

(defn next-data-entry
  [track physic d-2 d-1 input]
  (let [pos-1 (pos d-1)
        pos-2 (pos d-2)
        throttle (if (:throttle input) (:throttle input) (:throttle d-1 0.0))
        turbo (get-in d-1 [:turbo :factor] 1.0)
        req-switch (or (:switch input) (:requested-switch d-1 false))
        npos (next-pos physic track pos-2 pos-1 turbo throttle req-switch)
        na (next-angle physic track (:angle d-2 0.0) (:angle d-1 0.0) pos-2 pos-1)]
     (-> d-1
       (add-turbo-or-switch input)
       (c/turbo-step*)
       (remove-switch pos-1 npos)
       (assoc :throttle throttle)
       (assoc :input input)
       (assoc :angle na)
       (assoc :piecePosition npos))))

(defn run-step [track physic ergs step-in]
  (cons (next-data-entry track physic (second ergs) (first ergs) step-in)
        ergs))

(defn run-forward
  "Runs a Racesimulation on track. You can provide 2 start data entries
  inputs is a seq of {:throttle 0.0 :switch false :turbo 1.0} maps"
  ([track physic inputs]
   (run-forward track physic inputs (u/start-data "Test" "red" 0)))
  ([track physic inputs d-1]
   (run-forward track physic inputs d-1 d-1))
  ([track physic inputs d-2 d-1]
   (reverse (reduce (partial run-step track physic) (list d-1 d-2) inputs))))

;Other stuff

(defn crash? [track d]
  (let [eps 0.0005]
    (if (not (< (+ -60 eps) (:angle d) (- 60 eps)))
      d
      nil)))
