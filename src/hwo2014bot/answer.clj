(ns hwo2014bot.answer)

(defn throttle [m n]
  (assoc m :answer {:msgType "throttle" :data n}))

(defn turbo [m]
  (println "turbo started with:" (get-in m [:turbo :efficiency]))
  (assoc m :answer {:msgType "turbo" :data "pewpew!!"}))

(defn switch [m dir]
  (println "request switching at part" (get-in m [:cars :me 0 :piecePosition :pieceIndex]))
  (assoc m :answer {:msgType "switchLane" :data dir}))

(defn ping-back [m]
  (assoc m :answer {:msgType "ping" :data "ping"}))

(defn add-game-tick [m]
  (assoc-in m [:answer :gameTick] (get-in m [:input :gameTick])))

(defn answer-plan
  [m]
  (let [in (or (first (:input (first (get-in m [:race :my-plan])))) (first {:throttle 1.0}))]
    (condp = (key in)
      :throttle (throttle m (val in))
      :turbo (turbo m)
      :switch  (switch m (val in))
      (throttle m 1.0))))

(defn turbo-if-efficient [m eff]
  (let [toc (get-in m [:turbo-one-count])]
    (if (and toc (< 0 toc))
      (let [ticks (get-in m [:cars :me 0 :turbo :available :turboDurationTicks])]
        (if (< eff (/ toc ticks))
          (or (println "turbo with:" (/ toc ticks)) (turbo m))
          m))
      m)))

(defn dont-turbo [m]
  (if (= "turbo" (get-in m [:answer :msgType]))
    (ping-back m)
    m))

(defn be-carful-if-ahead [m n]
  (if (<= (get-in m [:car-ahead] (inc n)) n)
    (-> m
      dont-turbo)
    m))

