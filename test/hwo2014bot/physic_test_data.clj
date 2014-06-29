(ns hwo2014bot.physic-test-data)

(def d1 [{:id {:name "Drowsy", :color "red"},
          :angle 0.0,
          :piecePosition
          {:pieceIndex 0,
           :inPieceDistance 0.0,
           :lane {:startLaneIndex 0, :endLaneIndex 0},
           :lap 0}}
         {:id {:name "Drowsy", :color "red"},
            :angle 0.0,
            :piecePosition
            {:pieceIndex 0,
             :inPieceDistance 0.2,
             :lane {:startLaneIndex 0, :endLaneIndex 0},
             :lap 0}}
         {:throttle 1.0}
         {:id {:name "Drowsy", :color "red"},
              :angle 0.0,
              :piecePosition
              {:pieceIndex 0,
               :inPieceDistance 0.5960000000000001,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 0}
              :throttle 1.0
              :input {:throttle 1.0}
              :turbo {:factor 1.0 :ticks 0}}])
(def d2
  [{:id {:name "Drowsy", :color "red"}
    :angle -15.776655631716256,
    :piecePosition
    {:pieceIndex 13,
     :inPieceDistance 21.842907954814784,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}}
   {:id {:name "Drowsy", :color "red"}
            :angle -14.445212533297386
            :piecePosition
            {:pieceIndex 13,
             :inPieceDistance 31.161455495204265,
             :lane {:startLaneIndex 0, :endLaneIndex 0},
             :lap 2}}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle -13.078653245067201,
    :piecePosition
    {:pieceIndex 13,
     :inPieceDistance 40.49363208478596,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}
    :throttle 1.0
    :input {:throttle 1.0}
    :turbo {:factor 1.0 :ticks 0}}])

(def d3
  [{:id {:name "Drowsy", :color "red"},
    :angle 59.235883200258336,
    :piecePosition
    {:pieceIndex 34,
     :inPieceDistance 16.995079899769237,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}}
   {:id {:name "Drowsy", :color "red"},
            :angle 58.50652019581598,
            :piecePosition
            {:pieceIndex 34,
             :inPieceDistance 23.59155645222137,
             :lane {:startLaneIndex 0, :endLaneIndex 0},
             :lap 1}
            :turbo {:available {:turboFactor 3.0 :turboDurationTicks 30}}
            :throttle 1.0}
   {:turbo "Wohoo!"}
   {:id {:name "Drowsy", :color "red"},
    :angle 57.588989240927695,
    :piecePosition
    {:pieceIndex 34,
     :inPieceDistance 30.256103473624457,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :throttle 1.0
    :input {:turbo "Wohoo!"}
    :turbo {:factor 3.0 :ticks 31 :available nil}}])

(def d4
  [{:id {:name "Drowsy", :color "red"},
    :angle -6.903525661117525,
    :piecePosition
    {:pieceIndex 37,
     :inPieceDistance 89.87019554410553,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}}
   {:id {:name "Drowsy", :color "red"},
    :angle -9.0964724093144,
    :piecePosition
    {:pieceIndex 38,
     :inPieceDistance 6.881293824196547,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :turbo {:factor 3.0 :ticks 2}}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle -10.876698249995357,
    :piecePosition
    {:pieceIndex 38,
     :inPieceDistance 24.152170138685747,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :turbo {:factor 3.0 :ticks 1}
    :throttle 1.0
    :input {:throttle 1.0}}])

(def d5
  [{:id {:name "Drowsy", :color "red"},
    :angle -9.0964724093144,
    :piecePosition
    {:pieceIndex 38,
     :inPieceDistance 6.881293824196547,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :turbo {:factor 3.0 :ticks 2}}
   {:id {:name "Drowsy", :color "red"},
    :angle -10.876698249995357,
    :piecePosition
    {:pieceIndex 38,
     :inPieceDistance 24.152170138685747,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :turbo {:factor 3.0 :ticks 1}
    :throttle 1.0
    :input {:throttle 1.0}}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle -12.244088868876105,
    :piecePosition
    {:pieceIndex 38,
     :inPieceDistance 41.67762892688517,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :turbo {:factor 1.0 :ticks 0}
    :input {:throttle 1.0}
    :throttle 1.0}])

(def d6 
  [{:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 0,
     :inPieceDistance 0.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 0}}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 0,
     :inPieceDistance 0.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 0}
    :throttle 0.0}
   {:switch "Right"}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 0,
     :inPieceDistance 0.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 0}
    :throttle 0.0
    :requested-switch "Right"
    :turbo {:factor 1.0 :ticks 0}
    :input {:switch "Right"}}])

(def d7
  [{:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 2,
     :inPieceDistance 91.79259217797681,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 0}
    :requested-switch "Right"}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 2,
     :inPieceDistance 99.15674033441728,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 0}
    :requested-switch "Right"}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 3,
     :inPieceDistance 6.57360552772893,
     :lane {:startLaneIndex 0, :endLaneIndex 1},
     :lap 0}
    :requested-switch false
    :input {:throttle 1.0}
    :turbo {:factor 1.0 :ticks 0}
    :throttle 1.0}])

(def d8
  [{:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 3,
     :inPieceDistance 91.35145412349019,
     :lane {:startLaneIndex 0, :endLaneIndex 1},
     :lap 0}}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 3,
     :inPieceDistance 99.12442504102037,
     :lane {:startLaneIndex 0, :endLaneIndex 1},
     :lap 0}}
   {:throttle 0.0}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 4,
     :inPieceDistance 4.681661547266202,
     :lane {:startLaneIndex 1, :endLaneIndex 1},
     :lap 0}
    :throttle 0.0
    :input {:throttle 0.0}
    :turbo {:factor 1.0 :ticks 0}}])

(def d9
  [{:id {:name "Drowsy", :color "red"}
    :angle -15.776655631716256,
    :piecePosition
    {:pieceIndex 13,
     :inPieceDistance 21.842907954814784,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}}
   {:id {:name "Drowsy", :color "red"}
    :angle -14.445212533297386
    :piecePosition
    {:pieceIndex 13,
     :inPieceDistance 31.161455495204265,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}
    :requested-switch "Left"}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle -13.078653245067201,
    :piecePosition
    {:pieceIndex 13,
     :inPieceDistance 40.49363208478596,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}
    :requested-switch "Left"
    :throttle 1.0
    :input {:throttle 1.0}
    :turbo {:factor 1.0 :ticks 0}}])

(def d10
  [{:id {:name "Drowsy", :color "red"}
    :angle 0.0,
    :piecePosition
    {:pieceIndex 0,
     :inPieceDistance 0.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}}
   {:id {:name "Drowsy", :color "red"}
    :angle 0.0
    :piecePosition
    {:pieceIndex 1,
     :inPieceDistance 1.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :requested-switch "Left"}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 2,
     :inPieceDistance 0.18000000000000682,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :requested-switch "Left"
    :throttle 1.0
    :input {:throttle 1.0}
    :turbo {:factor 1.0 :ticks 0}}])

(def d11
  [{:id {:name "Drowsy", :color "red"}
    :angle 0.0,
    :piecePosition
    {:pieceIndex 28,
     :inPieceDistance 10.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}}
   {:id {:name "Drowsy", :color "red"}
    :angle 0.0
    :piecePosition
    {:pieceIndex 29,
     :inPieceDistance 20,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :requested-switch "Left"}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle -268.19440988397275,
    :piecePosition
    {:pieceIndex 30,
     :inPieceDistance 20.07416529422966,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 1}
    :requested-switch "Left"
    :throttle 1.0
    :input {:throttle 1.0}
    :turbo {:factor 1.0 :ticks 0}}])

(def d12
  [{:id {:name "Drowsy", :color "red"}
    :angle 0.0,
    :piecePosition
    {:pieceIndex 0,
     :inPieceDistance 0.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}}
   {:id {:name "Drowsy", :color "red"}
    :angle 0.0
    :piecePosition
    {:pieceIndex 1,
     :inPieceDistance 50.0,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}
    :requested-switch "Left"}
   {:throttle 1.0}
   {:id {:name "Drowsy", :color "red"},
    :angle 0.0,
    :piecePosition
    {:pieceIndex 3,
     :inPieceDistance 0.18000000000000682,
     :lane {:startLaneIndex 0, :endLaneIndex 0},
     :lap 2}
    :requested-switch "Left"
    :throttle 1.0
    :input {:throttle 1.0}
    :turbo {:factor 1.0 :ticks 0}}])