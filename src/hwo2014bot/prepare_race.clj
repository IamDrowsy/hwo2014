(ns hwo2014bot.prepare-race)

(defn add-track [m]
  (let [track (get-in m [:input :data :race :track])]
    (-> m
        (assoc-in [:race :track] track)
        (assoc-in [:race :id] (get-in m [:input :gameId])))))

(defn add-cars [m]
  (let [cars (get-in m [:input :data :race :cars])
        ids (map :id cars)]
    (-> m
      (assoc-in [:cars] (zipmap (map :id cars) cars))
      (assoc-in [:race :car-ids] ids))))

(defn add-car-ids [m]
    (let [cars (get-in m [:input :data :race :cars])
          ids (map :id cars)]
      (assoc-in m [:race :car-ids] ids)))


(defn add-session [m]
  (assoc-in m [:race :raceSession] (get-in m [:input :data :race :raceSession])))

(defn remove-physic [m]
  (assoc-in m [:race :physic] {}))

(defn remove-my-history [m]
  (assoc-in m [:cars :me] nil))

(defn prepare-race [m]
  (-> m
   #_add-cars
   add-car-ids
   add-session
   add-track
   remove-physic
   remove-my-history))
