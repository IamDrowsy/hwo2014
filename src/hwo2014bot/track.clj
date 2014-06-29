(ns hwo2014bot.track
  (:require [hwo2014bot.util :as u]))

;Lanestuff
(def lane
  (fn [track id]
    (let [lanes (:lanes track)]
    ((zipmap (map :index lanes) lanes) id))))

(def lanevec
  (fn [track]
    (into [] (map :index (sort-by :distanceFromCenter (:lanes track))))))

(def lanemap
  (fn [track]
    (zipmap (lanevec track) (range))))

(defn lane-switch
  "Returns the index of the lane to switch in"
    [track start-lane-index lorr]
    (let [lm (lanemap track)
          lv (lanevec track)
          i (lm start-lane-index)
          c (dec (count lm))]
      (condp = lorr
        "Left" (lv (max 0 (dec i)))
        "Right" (lv (min c (inc i)))
        (lv i))))

(def lane-diff
    (fn 
      ([l1 l2]
        (Math/abs (- (:distanceFromCenter l1 0)
                     (:distanceFromCenter l2 0))))
      ([track i1 i2]
        (lane-diff (lane track i1) (lane track i2)))))

;Piecestuff
(def piece
  (fn [track id]
  ((:pieces track) id)))

(defn curve? 
  ([piece]
    (:angle piece))
  ([track id]
  (:angle (piece track id))))

(defn switch?
  ([piece]
    (:switch piece))
  ([track id]
    (:switch (piece track id))))

(defn straight?
  ([piece]
    (:length piece))
  ([track id]
    (:length (piece track id))))

(def piece-count 
  (fn [track]
    (count (:pieces track []))))

;effectiv stuff

(defn effectiv-radius
  [track piece-index lane-offset ipd] ;more args just for later use, change effectiv-length!
  (let [{:keys [radius angle] :or {radius 100000 angle 0}} (piece track piece-index)]
    (- radius (* (u/sgn angle) lane-offset))))

(def curve-const (/ Math/PI 180))
(defn curve-length 
  [angle radius]
  (* curve-const angle radius (u/sgn angle)))

(defn straight-switch-length 
  [org-length lane-diff]
  (condp = [org-length lane-diff]
    [102.0 20] 104.0210461810256675
    [100.0 20] 102.06027499293376
    [99.0 20] 101.0804661468
    [94.0 20] 96.1876570838152
    [90.0 20] 92.28168127243908
    [78.0 20] 80.619013563812672
    (or (println "unknown straight switch " org-length lane-diff)
        org-length
        (Math/sqrt (+ (* org-length org-length) (* lane-diff lane-diff))))))

(defn effectiv-length [track piece-index lane-offset lane-diff]
  (let [p (piece track piece-index)]
    (cond (and (zero? lane-diff) (curve? track piece-index)) (curve-length (:angle p) (effectiv-radius track piece-index
                                                                                                       lane-offset 0))
          (and (zero? lane-diff) (straight? track piece-index)) (:length p 100)
          (straight? track piece-index) (straight-switch-length (:length p 100) lane-diff)
          :true (or (println "no good effectiv length" piece-index lane-diff) 100))))