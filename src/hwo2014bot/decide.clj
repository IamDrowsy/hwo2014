(ns hwo2014bot.decide
  (:require [hwo2014bot.plan :as pl]
            [hwo2014bot.position :as pos]
            [hwo2014bot.cars :as cars]))

(defn choose-best-way* [m needed-space]
  (let [plans (vals (:plans m))
        ways (map (partial pl/execute-plan m) plans)]
    (pos/best-way ways (cars/other-cars-in-race m) needed-space (get-in m [:car-ahead]))))

(defn assoc-new-way* [m way expire-ticks]
  (-> m
    (assoc-in [:race :my-plan] (drop 2 way)) ;drop d-2 d-1
    (assoc-in [:race :plan :valid-ticks] expire-ticks)))

(defn choose-plan [m expire-ticks needed-space]
  (let [plans (:plans m {})]
    (cond (empty? plans) m
          (= 1 (count plans)) ;if on just take it
          (assoc-new-way* m (pl/execute-plan m (first (vals plans))) expire-ticks)
          :true
          (assoc-new-way* m (choose-best-way* m needed-space) expire-ticks))))

;for now we just say turbo and let the normal throttle recalc. we can't be that bad ..
(defn choose-turbo-plan [m expire-ticks]
  (if (get-in m [:request :turbo])
    (let [inputs [{:turbo "pewpew!"}]]
      (-> m
        (assoc-in [:race :my-plan] (drop 2 (pl/execute-plan m inputs)))
        (assoc-in [:race :plan :valid-ticks] 1)))
    m))