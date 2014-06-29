(ns hwo2014bot.switch-test
    (:require [clojure.test :refer :all]
            [hwo2014bot.switch :refer :all]
            [hwo2014bot.track-test-data :refer :all]))

(def m {:race {:track keimola
               :my-plan [{:throttle 1.0}]}
        :cars {:me [{:piecePosition {:pieceIndex 0}
                     :lane {:startLaneIndex 0}
                     :throttle 1.0}]}})

(defn with-pi [m pi]
  (assoc-in m [:cars :me 0 :piecePosition :pieceIndex] pi))

(defn with-start-lane [m li]
  (assoc-in m [:cars :me 0 :piecePosition :lane :startLaneIndex] li))

(deftest more-lanes-test
  (is (more-lanes? m "Right"))
  (is (not (more-lanes? m "Left")))
  (is (not (more-lanes? (with-start-lane m 1) "Right")))
  (is (more-lanes? (with-start-lane m 1) "Left")))

(deftest not-requested-test
  (is (not-already-requested? m "Left"))
  (is (not-already-requested? (assoc-in m [:cars :me 0 :requested-switch] "Left") "Right"))
  (is (not (not-already-requested? (assoc-in m [:cars :me 0 :requested-switch] "Left") "Left"))))

(deftest next-switch-not-curve-test
  (is (next-switch-not-curve? m))
  (is (not (next-switch-not-curve? (with-pi m 3))))
  (is (not (next-switch-not-curve? (with-pi m 4))))
  (is (next-switch-not-curve? (with-pi m 12))))

(deftest throttle-dont-changes-test
  (is (throttle-dont-changes m))
  (is (not (throttle-dont-changes (assoc-in m [:cars :me 0 :throttle] 0.0))))
  (is (not (throttle-dont-changes (assoc-in m [:race :my-plan 0 :throttle] 0.0)))))