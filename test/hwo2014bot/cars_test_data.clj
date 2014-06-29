(ns hwo2014bot.cars-test-data)

(def position-state 
    {:input
     {:msgType "carPositions",
      :data
      [{:id {:name "Drowsy", :color "red"},
        :angle -1,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 4,
         :lane {:startLaneIndex 1, :endLaneIndex 0},
         :lap 1}}
       {:id {:name "Drowsy2", :color "blue"},
        :angle -2,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 5,
         :lane {:startLaneIndex 0, :endLaneIndex 0},
         :lap 1}}],
      :gameId "9ab95219-f5b1-463a-a031-4fffcbeb9d4b",
      :gameTick 565}
     :cars {{:name "Drowsy", :color "red"}  
            [{:id {:name "Drowsy", :color "red"},
              :angle -4,
              :piecePosition
              {:pieceIndex 1,
               :inPieceDistance 10,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 1}
              :throttle 1.0}]
            {:name "Drowsy2", :color "blue"}
            [{:id {:name "Drowsy2", :color "blue"},
             :angle -10,
             :piecePosition
             {:pieceIndex 2,
              :inPieceDistance 15,
              :lane {:startLaneIndex 1, :endLaneIndex 1},
              :lap 1}
             :throttle 1.0}
             {:id {:name "Drowsy2", :color "blue"},
             :angle -8,
             :piecePosition
             {:pieceIndex 1,
              :inPieceDistance 10,
              :lane {:startLaneIndex 1, :endLaneIndex 1},
              :lap 1}
             :throttle 1.0}]}})

(def position-result
    {:input
     {:msgType "carPositions",
      :data
      [{:id {:name "Drowsy", :color "red"},
        :angle -1,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 4,
         :lane {:startLaneIndex 1, :endLaneIndex 0},
         :lap 1}}
       {:id {:name "Drowsy2", :color "blue"},
        :angle -2,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 5,
         :lane {:startLaneIndex 0, :endLaneIndex 0},
         :lap 1}}],
      :gameId "9ab95219-f5b1-463a-a031-4fffcbeb9d4b",
      :gameTick 565}
     :cars {{:name "Drowsy", :color "red"}  
            [{:id {:name "Drowsy", :color "red"},
              :angle -1,
              :piecePosition
              {:pieceIndex 1,
               :inPieceDistance 4,
               :lane {:startLaneIndex 1, :endLaneIndex 0},
               :lap 1}
              :throttle 1.0}
             {:id {:name "Drowsy", :color "red"},
              :angle -4,
              :piecePosition
              {:pieceIndex 1,
               :inPieceDistance 10,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 1}
              :throttle 1.0}]
            {:name "Drowsy2", :color "blue"}
            [{:id {:name "Drowsy2", :color "blue"},
             :angle -2,
             :piecePosition
             {:pieceIndex 1,
              :inPieceDistance 5,
              :lane {:startLaneIndex 0, :endLaneIndex 0},
              :lap 1}
             :throttle 1.0}
             {:id {:name "Drowsy2", :color "blue"},
             :angle -10,
             :piecePosition
             {:pieceIndex 2,
              :inPieceDistance 15,
              :lane {:startLaneIndex 1, :endLaneIndex 1},
              :lap 1}
             :throttle 1.0}]}})


(def turbo-available-state
  {:input
   {:msgType "turboAvailable",
    :data
    {:turboDurationMilliseconds 500.0,
     :turboDurationTicks 30,
     :turboFactor 3.0},
    :gameId "4d78e251-5128-49fa-8be9-a085d64bd59b",
    :gameTick 601},
   :race {:car-ids [{:name "Drowsy", :color "red"} {:name "Drowsy2", :color "red"} {:name "Drowsy3", :color "red"}]}
   :cars {:me :stuff
          {:name "Drowsy", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 5.0}}
            :other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:turbo {}
            :other :stuff}
           {:other :stuff2}]
          {:name "Drowsy3", :color "red"}  
          [{:turbo {}
            :crashed true
            :other :stuff}]}})

(def turbo-available-result
  {:input
   {:msgType "turboAvailable",
    :data
    {:turboDurationMilliseconds 500.0,
     :turboDurationTicks 30,
     :turboFactor 3.0},
    :gameId "4d78e251-5128-49fa-8be9-a085d64bd59b",
    :gameTick 601},
   :race {:car-ids [{:name "Drowsy", :color "red"} {:name "Drowsy2", :color "red"} {:name "Drowsy3", :color "red"}]}
   :cars {:me :stuff
          {:name "Drowsy", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 5.0}}
            :other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 3.0}}
            :other :stuff}
           {:other :stuff2}]
          {:name "Drowsy3", :color "red"}  
          [{:turbo {}
            :crashed true
            :other :stuff}]}})

(def turbo-start-state
  {:input
   {:msgType "turboStart",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :race {:car-ids [{:name "Drowsy", :color "red"} {:name "Drowsy2", :color "red"}]}
   :cars {:me :stuff
          {:name "Drowsy", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 5.0}}
            :other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 3.0}}
            :other :stuff}
           {:other :stuff2}]}})

(def turbo-start-result
  {:input
   {:msgType "turboStart",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :race {:car-ids [{:name "Drowsy", :color "red"} {:name "Drowsy2", :color "red"}]}
   :cars {:me :stuff
          {:name "Drowsy", :color "red"}  
          [{:turbo {:available nil
                    :ticks 32
                    :factor 5.0}
            :other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 3.0}}
            :other :stuff}
           {:other :stuff2}]}})

(def turbo-step-state
  {:input
   {:msgType "turboStart",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :race {:car-ids [{:name "Drowsy", :color "red"} {:name "Drowsy2", :color "red"} {:name "Drowsy3", :color "red"}]}
   :cars {:me :stuff
          {:name "Drowsy", :color "red"}  
          [{:turbo {:available {}
                    :ticks 25
                    :factor 5.0}
            :other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 3.0}}
            :other :stuff}
           {:other :stuff2}]
          {:name "Drowsy3", :color "red"}  
          [{:turbo {:available {}
                    :ticks 1
                    :factor 3.0}
            :other :stuff}]}})

(def turbo-step-result
  {:input
   {:msgType "turboStart",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :race {:car-ids [{:name "Drowsy", :color "red"} {:name "Drowsy2", :color "red"} {:name "Drowsy3", :color "red"}]}
   :cars {:me :stuff
          {:name "Drowsy", :color "red"}  
          [{:turbo {:available {}
                    :ticks 24
                    :factor 5.0}
            :other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:turbo {:available {:turboDurationMilliseconds 500.0,
                                :turboDurationTicks 30,
                                :turboFactor 3.0}
                    :ticks 0
                    :factor 1.0}
            :other :stuff}
           {:other :stuff2}]
          {:name "Drowsy3", :color "red"}  
          [{:turbo {:available {}
                    :ticks 0
                    :factor 1.0}
            :other :stuff}]}})

(def crash-state 
  {:input
   {:msgType "crash",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :cars {{:name "Drowsy", :color "red"}  
          [{:other :stuff}]
          {:name "Drowsy2", :color "red"}  
          [{:other :stuff}
           {:other :stuff2}]}})

(def crash-result 
  {:input
   {:msgType "crash",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :cars {{:name "Drowsy", :color "red"}  
          [{:other :stuff
            :crashed true}]
          {:name "Drowsy2", :color "red"}  
          [{:other :stuff}
           {:other :stuff2}]}})

(def spawn-state 
  {:input
   {:msgType "spawn",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :cars {{:name "Drowsy", :color "red"}  
          [{:other :stuff
            :crashed true}]
          {:name "Drowsy2", :color "red"}  
          [{:other :stuff}
           {:other :stuff2}]}})

(def spawn-result 
  {:input
   {:msgType "spawn",
    :data {:name "Drowsy", :color "red"},
    :gameId "1a6bc7a7-75f5-423d-a225-44588d222e4f",
    :gameTick 612}
   :cars {{:name "Drowsy", :color "red"}  
          [{:other :stuff
            :crashed false}]
          {:name "Drowsy2", :color "red"}  
          [{:other :stuff}
           {:other :stuff2}]}})

(def answer-states
  [{:answer {:msgType "throttle" :data 0.5}
    :race {:me {:name "Drowsy" :color "red"}}
    :cars {{:name "Drowsy" :color "red"}
           [{:throttle 1.0
             :other :stuff}
            {:other :stuff2}]}}
   {:answer {:msgType "turbo" :data "pewpew"}
    :race {:me {:name "Drowsy" :color "red"}}
    :cars {{:name "Drowsy" :color "red"}
           [{:throttle 1.0
             :other :stuff}
            {:other :stuff2}]}}
   {:answer {:msgType "switchLane" :data "Right"}
    :race {:me {:name "Drowsy" :color "red"}}
    :cars {{:name "Drowsy" :color "red"}
           [{:throttle 1.0
             :other :stuff}
            {:other :stuff2}]}}])

(def answer-results
  [{:answer {:msgType "throttle" :data 0.5}
    :race {:me {:name "Drowsy" :color "red"}}
    :cars {{:name "Drowsy" :color "red"}
           [{:throttle 0.5
             :other :stuff}
            {:other :stuff2}]}}
   {:answer {:msgType "turbo" :data "pewpew"}
    :race {:me {:name "Drowsy" :color "red"}}
    :cars {{:name "Drowsy" :color "red"}
           [{:throttle 1.0
             :other :stuff}
            {:other :stuff2}]}}
   {:answer {:msgType "switchLane" :data "Right"}
    :race {:me {:name "Drowsy" :color "red"}}
    :cars {{:name "Drowsy" :color "red"}
           [{:throttle 1.0
             :requested-switch "Right"
             :other :stuff}
            {:other :stuff2}]}}])

(def my-history-state 
    {:input
     {:msgType "carPositions",
      :data
      [{:id {:name "Drowsy", :color "red"},
        :angle -1,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 4,
         :lane {:startLaneIndex 1, :endLaneIndex 0},
         :lap 1}}
       {:id {:name "Drowsy2", :color "blue"},
        :angle -2,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 5,
         :lane {:startLaneIndex 0, :endLaneIndex 0},
         :lap 1}}],
      :gameId "9ab95219-f5b1-463a-a031-4fffcbeb9d4b",
      :gameTick 565}
     :race {:me {:name "Drowsy", :color "red"}}
     :cars {:what :ever
            {:name "Drowsy", :color "red"}
            [{:id {:name "Drowsy", :color "red"},
               :angle -1,
               :piecePosition
               {:pieceIndex 1,
                :inPieceDistance 4,
                :lane {:startLaneIndex 1, :endLaneIndex 0},
                :lap 1}}]
            :me [{:id {:name "Drowsy", :color "red"},
                  :angle -4,
                  :piecePosition
                  {:pieceIndex 1,
                   :inPieceDistance 10,
                   :lane {:startLaneIndex 0, :endLaneIndex 0},
                   :lap 1}
                  :throttle 1.0}]}})

(def my-history-result
    {:input
     {:msgType "carPositions",
      :data
      [{:id {:name "Drowsy", :color "red"},
        :angle -1,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 4,
         :lane {:startLaneIndex 1, :endLaneIndex 0},
         :lap 1}}
       {:id {:name "Drowsy2", :color "blue"},
        :angle -2,
        :piecePosition
        {:pieceIndex 1,
         :inPieceDistance 5,
         :lane {:startLaneIndex 0, :endLaneIndex 0},
         :lap 1}}],
      :gameId "9ab95219-f5b1-463a-a031-4fffcbeb9d4b",
      :gameTick 565}
     :race {:me {:name "Drowsy", :color "red"}}
     :cars {:what :ever
            {:name "Drowsy", :color "red"}
            [{:id {:name "Drowsy", :color "red"},
               :angle -1,
               :piecePosition
               {:pieceIndex 1,
                :inPieceDistance 4,
                :lane {:startLaneIndex 1, :endLaneIndex 0},
                :lap 1}}]
            :me
            [{:id {:name "Drowsy", :color "red"},
              :angle -1,
              :piecePosition
              {:pieceIndex 1,
               :inPieceDistance 4,
               :lane {:startLaneIndex 1, :endLaneIndex 0},
               :lap 1}
              :throttle 1.0}
             {:id {:name "Drowsy", :color "red"},
              :angle -4,
              :piecePosition
              {:pieceIndex 1,
               :inPieceDistance 10,
               :lane {:startLaneIndex 0, :endLaneIndex 0},
               :lap 1}
              :throttle 1.0}]}})