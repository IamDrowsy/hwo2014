(ns hwo2014bot.switch
  (:require [hwo2014bot.track :as t]
            [hwo2014bot.position :as pos]
            [hwo2014bot.util :as u]
            [hwo2014bot.plan :as pl]))

(defn more-lanes? [m dir]
  (let [track (u/track m)
        si (get-in m [:cars :me 0 :piecePosition :lane :startLaneIndex] 0)]
    (not= (t/lane-switch track si dir) si)))

(defn not-already-requested? [m dir]
  (not= dir (get-in m [:cars :me 0 :requested-switch])))

(defn next-switch-not-curve? [m]
  (let [i (get-in m [:cars :me 0 :piecePosition :pieceIndex] 0)]
    (t/straight? (first (drop-while  
                          #(not (t/switch? %))
                          (take 20 (drop (inc i) (cycle (get-in m [:race :track :pieces]))))))))) ;20 pieces will be enough
  

(defn throttle-dont-changes [m]
  (= (get-in m [:cars :me 0 :throttle])
     (:throttle (first (get-in m [:race :my-plan])))))

(defn decide-now? [m dir steps]
  (let [p (pl/switch-plan m steps steps 2 dir)
        tp (get-in m [:race :my-plan])]
    (and (second p) ;switchplan must not be only switch
         (or 
           (and (= 0.0 (:throttle (second p)))
                (= 1.0 (:throttle (second tp))))  ;we have to stop throttle now if we want to switch
           (pos/changed-to-switch? (u/track m) 
                                  (:piecePosition (first tp))
                                  (:piecePosition (get-in m [:cars :me 0]))))))) ;or we change to the switch piece 
(defn car-ahead? [m]
  (get-in m [:car-ahead]))

(defn check-switch? [m dir steps]
  (and (more-lanes? m dir)
       (next-switch-not-curve? m)
       (not-already-requested? m dir)
       (throttle-dont-changes m)
       (or (decide-now? m dir steps)
           (car-ahead? m))))