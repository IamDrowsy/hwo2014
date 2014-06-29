(ns hwo2014bot.plan
  (:require [hwo2014bot.predict-stream :as ps]
            [hwo2014bot.physic :as p]))

(defn alter-search [s]
  (if (= s :ones)
    :zeros :ones))

(defn create-plan*
  ([track physic predict-size step-predict-size max-alter-steps d-2 d-1]
    (create-plan* track physic predict-size step-predict-size max-alter-steps d-2 d-1 nil))
  ([track physic predict-size step-predict-size max-alter-steps d-2 d-1 init-input]
    (loop [result (if init-input (list init-input) ())
           [d-2 d-1] (if init-input 
                       [d-1 (p/next-data-entry track physic d-2 d-1 init-input)]
                       [d-2 d-1])
           taken-steps 0
           next-search :ones]
      (if (or (<= max-alter-steps taken-steps) ;more steps taken
              (<= predict-size (count result))) ;more ticks predicted
        result
        (let [input (if (= :ones next-search) {:throttle 1.0} {:throttle 0.0})
              f (if (= :ones next-search) ps/max-valid-one-log ps/min-valid-zero-log)
              inputs (repeat step-predict-size input)
              preds (ps/predictions track physic d-2 d-1 inputs)
              result-size (f track physic preds)
              next-result (concat result (repeat result-size input))]
          (recur next-result (take 2 (drop result-size preds)) (inc taken-steps) (alter-search next-search)))))))

(defn create-plan [m predict-size step-predict-size max-alter-steps]
  (let [track (get-in m [:race :track])
        physic (get-in m [:race :physic])
        d-2 (second (get-in m [:cars :me]))
        d-1 (first (get-in m [:cars :me]))]
    (if (and d-2 d-1)
      (create-plan* track physic predict-size step-predict-size max-alter-steps d-2 d-1 #_{:throttle 1.0})
      [{:throttle 1.0}])))

(defn turbo-plan [m predict-size step-predict-size max-alter-steps]
  (let [track (get-in m [:race :track])
        physic (get-in m [:race :physic])
        d-2 (second (get-in m [:cars :me]))
        d-1 (first (get-in m [:cars :me]))]
    (if (and d-2 d-1)
      (create-plan* track physic predict-size step-predict-size max-alter-steps d-2 d-1 {:turbo "pewpew!"})
      [{:turbo "pewpew"}])))

(defn switch-plan [m predict-size step-predict-size max-alter-steps dir]
  (let [track (get-in m [:race :track])
        physic (get-in m [:race :physic])
        d-2 (second (get-in m [:cars :me]))
        d-1 (first (get-in m [:cars :me]))]
    (if (and d-2 d-1)
      (create-plan* track physic predict-size step-predict-size max-alter-steps d-2 d-1 {:switch dir})
      [{:switch dir}])))

(defn execute-plan
  ([m plan]
    (let [track (get-in m [:race :track])
          physic (get-in m [:race :physic])
          d-2 (second  (get-in m [:cars :me]))
          d-1 (first (get-in m [:cars :me]))]
      (execute-plan track physic d-2 d-1 plan)))
  ([track physic d-2 d-1 plan]
  (ps/predictions track physic d-2 d-1 plan)))

(defn check-plan [track executed-plan]
  (ps/no-crash track executed-plan))
