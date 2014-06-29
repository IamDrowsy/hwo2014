(ns hwo2014bot.check-physic-test
    (:require [clojure.test :refer :all]
            [hwo2014bot.check-physic :refer :all]
            [hwo2014bot.physic-check-data :refer :all]))


(defn test-physic* [data]
  (is (= (check-physic-once (first data))(second data))))

(deftest test-physic
  (test-physic* d1)
  (test-physic* d2)
  (test-physic* d3))