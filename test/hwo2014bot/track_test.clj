(ns hwo2014bot.track-test
  (:require [clojure.test :refer :all]
            [hwo2014bot.track :refer :all]
            [hwo2014bot.track-test-data :refer :all]))

(deftest lane-test
  (is (= (lane keimola 0) {:distanceFromCenter -10, :index 0}))
  (is (= (lane keimola 1) {:distanceFromCenter 10, :index 1})))

(deftest piece-test
  (is (= (piece keimola 9) {:length 100.0}))
  (is (= (piece keimola 8) {:radius 200, :angle 22.5, :switch true})))

(deftest lane-diff-test
  (is (= (lane-diff keimola 0 0) 0))
  (is (= (lane-diff keimola 0 1) 20))
  (is (= (lane-diff keimola 1 0) 20)))

(deftest lane-switch-test
  (is (= (lane-switch keimola 0 "Left") 0))
  (is (= (lane-switch keimola 1 "Left") 0))
  (is (= (lane-switch keimola 0 "Right") 1))
  (is (= (lane-switch keimola 1 "Right") 1)))

(deftest switch-curve-test
  (is (switch? keimola 3))
  (is (not (switch? keimola 0)))
  (is (curve? keimola 4))
  (is (not (curve? keimola 0)))
  (is (straight? keimola 0))
  (is (not (straight? keimola 4))))

(deftest effectiv-radius-test
  (is (= (effectiv-radius keimola 0 -10 0) 100000))
  (is (= (effectiv-radius keimola 4 -10 0) 110))
  (is (= (effectiv-radius keimola 4 10 0) 90)))

(deftest effectiv-length-test 
  (is (= (effectiv-length keimola 0 0 0) 100.0))
  (is (= (effectiv-length keimola 0 10 20) 102.06027499293376))
  (is (= (effectiv-length keimola 4 10 0) 70.68583470577035)))