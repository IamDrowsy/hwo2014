(ns hwo2014bot.augment
  (:require [hwo2014bot.predict-stream :as ps]
            [hwo2014bot.plan :as pl]
            [hwo2014bot.turbo :as turbo]
            [hwo2014bot.switch :as sw]
            [hwo2014bot.cars :as cars]
            [hwo2014bot.position :as pos]))

(defn turbo-request [m max-steps min-eff]
  (if (turbo/check-turbo? m)
    (-> m
      (turbo/turbo-efficiency max-steps)
      (turbo/request-turbo min-eff))
    (assoc-in m [:turbo :efficiency] 0)))

(defn switch-plans [m predict-size step-predict-size max-steps]
  (cond-> m
          (sw/check-switch? m "Right" step-predict-size)
          (assoc-in [:plans :right] (pl/switch-plan m predict-size step-predict-size max-steps "Right"))
          (sw/check-switch? m "Left" step-predict-size)
          (assoc-in [:plans :left] (pl/switch-plan m predict-size step-predict-size max-steps "Left"))))

(defn car-ahead [m needed-space]
  (let [cars (cars/other-cars-in-race m)
        ahead-ticks (pos/free-ticks-to-cars cars needed-space (get-in m [:race :my-plan]))]
    (if (< ahead-ticks needed-space)
      (assoc-in m [:car-ahead] ahead-ticks)
      m)))
