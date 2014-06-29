(ns hwo2014bot.cars-test
  (:require [clojure.test :refer :all]
            [hwo2014bot.cars :refer :all]
            [hwo2014bot.cars-test-data :refer :all]))

(deftest position-test
  (is (= (update-positions position-state 2) position-result)))

(deftest turbo-available-test
  (is (= (turbo-available turbo-available-state) turbo-available-result)))

(deftest turbo-start-test
  (is (= (turbo-start turbo-start-state) turbo-start-result)))

(deftest turbo-step-test
  (is (= (turbo-step turbo-step-state) turbo-step-result)))

(deftest crash-test
  (is (= (crash crash-state) crash-result)))

(deftest spwan-test
  (is (= (spawn spawn-state) spawn-result)))

(deftest answer-test
  (is (= (map update-on-answer answer-states) answer-results)))

(deftest my-history-test
  (is (= (update-my-history my-history-state 2) my-history-result)))
