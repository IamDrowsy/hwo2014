(ns hwo2014bot.position
  (:require [hwo2014bot.track :as t]
            [hwo2014bot.util :as u]))

(defn lane-diff [track pos]
  (t/lane-diff track
               (get-in pos [:lane :startLaneIndex] 0)
               (get-in pos [:lane :endLaneIndex] 0)))

(defn start-lane [track pos]
  (t/lane track (get-in pos [:lane :startLaneIndex] 0)))

(defn end-lane [track pos]
  (t/lane track (get-in pos [:lane :endLaneIndex] 0)))

(defn piece [track pos]
  (t/piece track (:pieceIndex pos 0)))

(defn effectiv-radius
  [track pos]
  (let [lane-offset (:distanceFromCenter (start-lane track pos) 0)]
    (t/effectiv-radius track (:pieceIndex pos 0)
                       lane-offset (:inPieceDistance pos 0))))

(defn effectiv-length
  [track pos]
  (let [lane-offset (:distanceFromCenter (start-lane track pos) 0)]
  (t/effectiv-length track (:pieceIndex pos 0) 
                     lane-offset 
                     (if (not= (get-in pos [:lane :startLaneIndex])
                               (get-in pos [:lane :endLaneIndex]))
                       (lane-diff track pos)
                       0))))

(defn track-angle [track pos]
  (:angle (piece track pos) 0))

(defn on-switch? [track pos]
  (t/switch? track (:pieceIndex pos 0))) 
                  
(defn pos> [pos1 pos2]
  (or (> (:lap pos1 -1) (:lap pos2 -1))
      (and (= (:lap pos1 -1) (:lap pos2 -1))
           (>  (:pieceIndex pos1 -1) (:pieceIndex pos2 -1)))
      (and (= (:lap pos1 -1) (:lap pos2 -1))
           (= (:pieceIndex pos1 -1) (:pieceIndex pos2 -1))
           (> (:inPieceDistance pos1 -1) (:inPieceDistance pos2 -1)))))

(defn pos>-ignore-lap [pos1 pos2]
  (or (>  (:pieceIndex pos1 -1) (:pieceIndex pos2 -1))
      (and (= (:pieceIndex pos1 -1) (:pieceIndex pos2 -1))
           (> (:inPieceDistance pos1 -1) (:inPieceDistance pos2 -1)))))

(defn free-ticks-to-pos [way pos2]
  (let [poss (map :piecePosition way)
        c (count way)
        pos-before (take-while (partial pos>-ignore-lap pos2) 
                               (drop-while #(not (pos>-ignore-lap pos2 %)) poss))]
    (if (and (< (count pos-before) c) 
             (= (:lane (last pos-before)) (:lane pos2)))
      (or #_(println "Will bump in" (count pos-before)) (count pos-before)) ;will bump and a is in front
      1337)))

(defn free-ticks-to-cars [cars needed-space way]
  (apply min (cons needed-space (map (partial free-ticks-to-pos way) (map :piecePosition cars)))))
;we calc it and cap it at needet space, so that everything over this is equivalent

(defn empty-ways
  [ways cars needed-space]
  (let [f (partial free-ticks-to-cars cars needed-space)]
    (first (partition-by f (reverse  (sort-by f ways))))))

(defn way> [index w1 w2]
  (pos> (:piecePosition (nth w1 index nil)) (:piecePosition (nth w2 index nil))))

(defn fast-way 
  ([ways]
    (let [i (dec (apply min (map count ways)))]
      #_(println "Choosing Way at index " i)
      (fast-way i ways)))
  ([index ways]
    (let [sorted (sort (comparator (partial way> index))
                 ways)]
      #_(println "choosen-from " (map #(nth % index) sorted))
      (first sorted))))    

(defn choose-throttle [ways]
  (let [wm (zipmap (map #(key (first (get-in (nth % 3) [:input]))) ways)
                   ways)]
    (if (:throttle wm)
      (:throttle wm)
      nil)))

(defn best-way
  [ways cars needed-space ahead?]
  (let [ews (empty-ways ways cars needed-space)]
    (if (and ahead? (choose-throttle ews))
      (choose-throttle ews)
      (fast-way ews))))

(defn changed-to-switch? [track pos pos-1]
  (and (on-switch? track pos)
       (not (on-switch? track pos-1))))

(defn started-switch? [pos pos-1]
  (not= (get-in pos [:lane :endLaneIndex])
        (get-in pos-1 [:lane :endLaneIndex])))
