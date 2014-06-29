(ns hwo2014bot.physic-test
  (:require [clojure.test :refer :all]
            [hwo2014bot.physic :refer :all]
            [hwo2014bot.physic-test-data :refer :all]
            [hwo2014bot.track-test-data :refer :all]))

(def physic {:friction-factor 0.98 :throttle-factor 0.2 :a-x5 1.0 :a-x3 1.0})

(defn test-physic* [data]
  (is (= (nth data 3) (next-data-entry keimola physic (first data) (second data) (nth data 2)))))

(deftest test-physic
  (test-physic* d1)
  (test-physic* d2)
  (test-physic* d3)
  (test-physic* d4)
  (test-physic* d5)
  (test-physic* d6)
  (test-physic* d7)
  (test-physic* d8)
  (test-physic* d9)
  (test-physic* d10)
  (test-physic* d11)
  #_(test-physic* d12))


