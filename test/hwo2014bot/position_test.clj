(ns hwo2014bot.position-test  
  (:require [clojure.test :refer :all]
            [hwo2014bot.position :refer :all]))


(deftest pos>=-test
  (is (pos> {:lap 1} {:lap 0}))
  (is (pos> {:pieceIndex 1} {:pieceIndex 0}))
  (is (pos> {:lap 1 :pieceIndex 1 :inPieceDistance 2.0}
             {:lap 1 :pieceIndex 1 :inPieceDistance 1.0}))
  (is (pos> {:lap 1 :inPieceDistance 2.0}
             {:lap 0 :inPieceDistance 5.0})))

(def ways [[{} {} {} {} {:piecePosition {:lap 1}}]
           [{} {} {} {:piecePosition {:lap 1}} {:piecePosition {:lap 0}}]
           [{} {} {:piecePosition {:lap 1}}]])

(deftest fast-way-test
  (is (= (ways 2) (fast-way ways)))
  (is (= (ways 0) (fast-way 4 ways)))
  (is (= (ways 1) (fast-way 3 ways)))
  (is (= (ways 2) (fast-way 2 ways))))
