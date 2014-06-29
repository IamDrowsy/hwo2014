(ns hwo2014bot.util)

(defn sgn [n]
  (cond
     (< n 0) -1
     (zero? n)  0
     :default 1))

(defn- warn-get [m ks msg default]
  (let [d (get-in m ks)]
    (if d d
      (do (println "Warning! Got nil from: " msg ", Returning" default) default))))

(defn input-data [m]
  (warn-get m [:input :data] "Input-data" {}))

(defn check-msg [m type msg]
  (if (not= (get-in m [:input :msgType]) type)
    (println "Warning! Got wrong msg-type " (get-in m [:input :msgType]) " in " msg)))

(defn me [m]
  (warn-get m [:race :me] "me" {}))

(defn get-me [m]
  (get-in m [:cars (me m) 0]))

(defn assoc-in-me [m ks v]
  (assoc-in m (concat [:cars (me m) 0] ks) v))

(defn update-me [m f & args]
  (apply (partial update-in m [:cars (me m) 0] f) args))

(defn start-data [name color start-lane]
  {:id {:name name :color color}
   :angle 0.0
   :piecePosition {:pieceIndex 0
                   :inPieceDistance 0.0
                   :lane {:startLaneIndex start-lane
                          :endLaneIndex start-lane}
                   :lap 0}})

(defn request-replan [m]
  (assoc-in m [:race :plan :request-replan] true))

(defn track [m]
  (get-in m [:race :track]))