(ns hwo2014bot.physic-check-data
  (:require [hwo2014bot.track-test-data :refer [keimola]]))


(def d1 [{:cars
           {:me 
            [{:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0,
               :inPieceDistance 0.0,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0}]}}
         {:cars
           {:me 
            [{:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0,
               :inPieceDistance 0.0,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0}]}}])

(def d2 [{:cars
           {:me 
            [{:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0,
               :inPieceDistance 1.1840800000000002,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0}
             {:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0, 
               :inPieceDistance 0.5960000000000001,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0}
             {:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0,
               :inPieceDistance 0.2,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0}
             {:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0,
               :inPieceDistance 0.0,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0}]}
           :race {:track keimola}}
          {:cars
            {:me 
             [{:id {:name "Drowsy", :color "red"},
               :angle 0.0,
               :piecePosition
               {:pieceIndex 0,
                :inPieceDistance 1.1840800000000002,
                :lane {:startLaneIndex 0, :endLaneIndex 0},
                :lap 0}
               :throttle 1.0}
               {:id {:name "Drowsy", :color "red"},
               :angle 0.0,
               :piecePosition
               {:pieceIndex 0, 
                :inPieceDistance 0.5960000000000001,
                :lane {:startLaneIndex 0, :endLaneIndex 0},
                :lap 0}
               :throttle 1.0}
               {:id {:name "Drowsy", :color "red"},
               :angle 0.0,
               :piecePosition
               {:pieceIndex 0,
                :inPieceDistance 0.2,
                :lane {:startLaneIndex 0, :endLaneIndex 0},
                :lap 0}
               :throttle 1.0}
              {:id {:name "Drowsy", :color "red"},
               :angle 0.0,
               :piecePosition
               {:pieceIndex 0,
                :inPieceDistance 0.0,
                :lane {:startLaneIndex 0, :endLaneIndex 0},
                :lap 0}
               :throttle 1.0}]}
            :race {:physic {:checked-d true
                            :friction-factor 0.9800000000000001
                            :throttle-factor 0.20000000000000007}
                   :track keimola}}])

(def d3
  [{:cars {:me [{:throttle 0.0, 
                 :piecePosition 
                 {:pieceIndex 4, 
                  :inPieceDistance 33.65837180848723,
                  :lane {:endLaneIndex 1, :startLaneIndex 1}, :lap 0},
                 :turbo {:factor 1.0, :ticks 0}, 
                 :angle 7.791112745226145, 
                 :input {:throttle 0.0}, 
                 :id {:name "Drowsy", :color "red"}}  
                {:id {:name "Drowsy", :color "red"},
                 :angle 5.017205140339623,
                 :piecePosition
                 {:pieceIndex 4,
                  :inPieceDistance 26.632221743210106,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}
                {:id {:name "Drowsy", :color "red"},
                 :angle 2.6880308673748683,
                 :piecePosition    
                 {:pieceIndex 4,
                  :inPieceDistance 19.46268086027427,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}
                {:id {:name "Drowsy", :color "red"},
                 :angle 0.9585254751278218,
                 :piecePosition
                 {:pieceIndex 4,
                  :inPieceDistance 12.146822816462187,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}
                {:id {:name "Drowsy", :color "red"},
                 :angle 0.0,
                 :piecePosition
                 {:pieceIndex 4,
                  :inPieceDistance 4.681661547266188,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}]}
    :race {:track keimola}}
   {:cars {:me [{:throttle 0.0, 
                 :piecePosition 
                 {:pieceIndex 4, 
                  :inPieceDistance 33.65837180848723,
                  :lane {:endLaneIndex 1, :startLaneIndex 1}, :lap 0},
                 :turbo {:factor 1.0, :ticks 0}, 
                 :angle 7.791112745226145, 
                 :input {:throttle 0.0}, 
                 :id {:name "Drowsy", :color "red"}}                
                {:id {:name "Drowsy", :color "red"},
                 :angle 5.017205140339623,
                 :piecePosition
                 {:pieceIndex 4,
                  :inPieceDistance 26.632221743210106,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}
                {:id {:name "Drowsy", :color "red"},
                 :angle 2.6880308673748683,
                 :piecePosition    
                 {:pieceIndex 4,
                  :inPieceDistance 19.46268086027427,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}
                {:id {:name "Drowsy", :color "red"},
                 :angle 0.9585254751278218,
                 :piecePosition
                 {:pieceIndex 4,
                  :inPieceDistance 12.146822816462187,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}
                {:id {:name "Drowsy", :color "red"},
                 :angle 0.0,
                 :piecePosition
                 {:pieceIndex 4,
                  :inPieceDistance 4.681661547266188,
                  :lane {:startLaneIndex 1, :endLaneIndex 1},
                  :lap 0}
                 :throttle 0.0}]}
    :race {:physic {:checked-a true
                    :a-x5 1.0000000000000009
                    :a-x3 1.0000000000000027}
           :track keimola}}])