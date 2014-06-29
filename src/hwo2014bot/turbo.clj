(ns hwo2014bot.turbo
  (:require [hwo2014bot.predict-stream :as ps]
            [hwo2014bot.plan :as pl]))

(defn turbo-just-got-available [m]
  (let [avail? (get-in m [:cars :me 0 :turbo :available :turboDurationTicks])]
    (and avail? (not= avail? (get-in m [:cars :me 1 :turbo :available :turboDurationTicks])))))

(defn throttle-changed-to-one [m]
  (and (= 1.0 (get-in m [:cars :me 0 :throttle]))
       (= 0.0 (get-in m [:cars :me 1 :throttle]))))

(defn turbo-is-available [m]
  (get-in m [:cars :me 0 :turbo :available :turboDurationTicks]))

(defn throttle-is-one [m]
  (= 1.0 (get-in m [:cars :me 0 :throttle])))

(defn check-turbo? [m]
  (and (throttle-is-one m)
       (turbo-is-available m)))

(defn turbo-efficiency [m max-steps]
  (let [turbo-ticks (get-in m [:cars :me 0 :turbo :available :turboDurationTicks])
        one-count (count (take-while #(= % {:throttle 1.0})
                                     (drop 1 (pl/turbo-plan m max-steps max-steps 1))))]
    (if (and turbo-ticks (not (zero? turbo-ticks)))
      (assoc-in m [:turbo :efficiency]
                (/ one-count turbo-ticks))
      (assoc-in m [:turbo :efficiency] 0))))

(defn request-turbo [m min-eff]
   (let [e (get-in m [:turbo :efficiency] 0)]
     (if (<= min-eff e)
       (assoc-in m [:request :turbo] true)
       m)))
