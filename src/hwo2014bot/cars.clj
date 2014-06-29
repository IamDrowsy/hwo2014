(ns hwo2014bot.cars
  (:require [hwo2014bot.util :as u]
            [hwo2014bot.position :as pos]))

(defn car-ids [m]
  (get-in m [:race :car-ids] []))

(defn other-car-ids [m]
  (disj (into #{} (car-ids m)) (u/me m)))

(defn other-cars [m]
  (map first (vals (select-keys (get-in m [:cars])
                                  (other-car-ids m)))))

(defn relevant [d]
  (and (not (:finished d))
       (not ( :crashed d))))

(defn other-cars-in-race [m]
  (filter relevant (other-cars m)))

;Update Positions on car-position-msg

(defn update-position [old-positions new-position history-size]
  (let [last-pos (first old-positions)
        new-pos (merge last-pos new-position)]
    (into [](take history-size (cons new-pos old-positions)))))

(defn update-positions* 
  [car-positions car-position-msg-data history-size]
  (reduce (fn [positions new-car-position]
             (update-in positions [(:id new-car-position)] 
                        update-position new-car-position history-size))
           car-positions car-position-msg-data))

(defn update-positions 
  [m history-size]
  (u/check-msg m "carPositions" "update-positions")
  (update-in m [:cars] update-positions* (u/input-data m) history-size))

;Update turbo info on turbo available msg
(defn turbo-available* 
  [car-data turbo-data car-id]
  (if (or (:crashed car-data)
          (get-in car-data [:turbo :available :turboDurationTicks]))
    car-data
    (assoc-in car-data [:turbo :available] turbo-data)))

(defn turbo-available 
  [m]
  (u/check-msg m "turboAvailable" "turbo-available")
  (let [turbo-data (u/input-data m)]
    (reduce (fn [m car-id]
              (update-in m [:cars car-id 0] turbo-available* turbo-data car-id))
            m (car-ids m))))

;Update Info on Turbo is startet on turbo-start-msg
(defn turbo-start* 
  [car-data]
  (let [ticks  (inc (inc (get-in car-data [:turbo :available :turboDurationTicks] -2)))
        factor (get-in car-data [:turbo :available :turboFactor] 1.0)]
    (-> car-data
      (assoc-in [:turbo :ticks] ticks)
      (assoc-in [:turbo :factor] factor)
      (assoc-in [:turbo :available] nil))))

(defn turbo-start
  [m]
  (u/check-msg m "turboStart" "turbo-start")
  (let [car-id (u/input-data m)]
    (update-in m [:cars car-id 0] turbo-start*)))

;Update turbo-info on step
(defn turbo-dec [n]
  (if n 
    (max (dec n) 0)
    0))

(defn turbo-step*
  [car-data]
  (let [dec-ticks (update-in car-data [:turbo :ticks] turbo-dec)]
    (if (zero? (get-in dec-ticks [:turbo :ticks] 0))
      (assoc-in dec-ticks [:turbo :factor] 1.0)
      dec-ticks)))

(defn turbo-step
  [m]
  (reduce (fn [m car-id]
            (update-in m [:cars car-id 0] turbo-step*))
          m (car-ids m)))

;Update on crash and spawn msg
(defn update-crashed [m msg-type new-status]
  (u/check-msg m msg-type "spawn/crash")
  (let [car-id (u/input-data m)]
    (assoc-in m [:cars car-id 0 :crashed] new-status)))

(defn crash
  [m]
  (update-crashed m "crash" true))

(defn spawn
  [m]
  (update-crashed m "spawn" false))

(defn update-finish [m msg-type new-status]
  (u/check-msg m msg-type "finish")
  (let [car-id (u/input-data m)]
    (assoc-in m [:cars car-id 0 :finished] new-status)))

(defn finish
  [m]
  (update-finish m "finish" true))

;Updates me on given answer
(defn update-on-answer
  [m]
  (let [answer-data (get-in m [:answer :data])]
    (condp = (get-in m [:answer :msgType])
      "throttle" (u/assoc-in-me m [:throttle] answer-data)
      "switchLane" (u/assoc-in-me m [:requested-switch] answer-data)
      m)))

(defn update-switch-request [m]
  (let [[pos pos-1] (map :piecePosition (get-in m [:cars :me]))]
    (if (pos/started-switch? pos pos-1)
      (or #_(println "changed-switch-request at part" (get-in pos [:pieceIndex])) (u/assoc-in-me m [:requested-switch] false))
      m)))

;Stores my history
(defn update-my-history* [old-mes new-me history-size]
  (into [] (take history-size (cons (merge (first old-mes) new-me) old-mes))))

(defn update-my-history [m history-size]
  (update-in m [:cars :me] update-my-history* (u/get-me m) history-size))
