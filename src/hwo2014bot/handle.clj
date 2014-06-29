(ns hwo2014bot.handle
  (:require [hwo2014bot.cars :as c]
            [hwo2014bot.augment :as au]
            [hwo2014bot.answer :as a]
            [hwo2014bot.prepare-race :as pr]
            [hwo2014bot.check-physic :as cp]
            [hwo2014bot.util :as u]
            [hwo2014bot.replan :as rp]
            [hwo2014bot.turbo :as turbo]
            [hwo2014bot.decide :as d]))

(defmulti handle-msg (fn [m] (get-in m [:input :msgType])))

(defmethod handle-msg :default [m]
  m)

(defmethod handle-msg "crash" [m]
  (println (get-in m [:input :data]) "crashed")
  (c/crash m))

(defmethod handle-msg "finish" [m]
  (c/finish m))

(defmethod handle-msg "spawn" [m]
  (c/spawn m))

(defmethod handle-msg "turboAvailable" [m]
  (println "got Turbo!")
  (-> m
    c/turbo-available))

(defmethod handle-msg "turboStart" [m]
  (c/turbo-start m))

(defmethod handle-msg "yourCar" [m]
  (assoc-in m [:race :me] (get-in m [:input :data])))

(defmethod handle-msg "gameInit" [m]
  (pr/prepare-race m))

(defmethod handle-msg "gameStart" [m]
  (-> m
    pr/remove-physic
    pr/remove-my-history))

(defn not-crashed-part [m]
  (-> m
    c/turbo-step
    (au/car-ahead 30)
    c/update-switch-request
    (c/update-my-history 4)
    (rp/plan-good? 0.00001)
    (cp/recalc-physic-on-bad-plans 10)
    rp/plan-tick
    ;other-stuff
    cp/check-physic-once
    (au/switch-plans 120 64 5)
    (rp/replan 120 64 5)
    (au/turbo-request 120 0.8) ;min steps, turbo-efficency
    (d/choose-plan 10 30) ;expire , needed-space
    (d/choose-turbo-plan 10)
    a/answer-plan
    (a/be-carful-if-ahead 10)
    a/add-game-tick
    (c/update-on-answer)))

(defn not-crashed-and-finished [m]
  (and (not (get-in (u/get-me m) [:crashed]))
       (not (get-in (u/get-me m) [:finished]))))

(defn ping-if-no-answer [m]
  (if (get-in m [:answer])
    m
    (assoc-in m [:answer] {:msgType "ping"})))

(defmethod handle-msg "carPositions" [m]
  (cond-> m
          true (c/update-positions 1)
          (not-crashed-and-finished m) (not-crashed-part)
          true ping-if-no-answer))

