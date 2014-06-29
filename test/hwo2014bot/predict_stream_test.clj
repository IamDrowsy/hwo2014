(ns hwo2014bot.predict-stream-test
  (:require [clojure.test :refer :all]
            [hwo2014bot.predict-stream :refer :all]
            [hwo2014bot.physic :as p]
            [hwo2014bot.util :as u]
            [hwo2014bot.track-test-data :refer [keimola]]))

(defn predict 
  ([]
    (predict 500 (repeat 500 {:throttle 1.0})))
  ([n]
    (predict n (repeat n {:throttle 1.0})))
  ([n inputs]
    (predictions keimola {} (u/start-data "Drowsy" "red" 0) (u/start-data "Drowsy" "red" 0) inputs)))
