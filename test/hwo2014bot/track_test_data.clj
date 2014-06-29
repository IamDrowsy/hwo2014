(ns hwo2014bot.track-test-data)

(def keimola {:id "keimola",
                 :name "Keimola",
                 :pieces
                 [{:length 100.0}
                  {:length 100.0}
                  {:length 100.0}
                  {:length 100.0, :switch true}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 200, :angle 22.5, :switch true}
                  {:length 100.0}
                  {:length 100.0}
                  {:radius 200, :angle -22.5}
                  {:length 100.0}
                  {:length 100.0, :switch true}
                  {:radius 100, :angle -45.0}
                  {:radius 100, :angle -45.0}
                  {:radius 100, :angle -45.0}
                  {:radius 100, :angle -45.0}
                  {:length 100.0, :switch true}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 200, :angle 22.5}
                  {:radius 200, :angle -22.5}
                  {:length 100.0, :switch true}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:length 62.0}
                  {:radius 100, :angle -45.0, :switch true}
                  {:radius 100, :angle -45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:radius 100, :angle 45.0}
                  {:length 100.0, :switch true}
                  {:length 100.0}
                  {:length 100.0}
                  {:length 100.0}
                  {:length 90.0}],
                 :lanes
                 [{:distanceFromCenter -10, :index 0}
                  {:distanceFromCenter 10, :index 1}],
                 :startingPoint {:position {:x -300.0, :y -44.0}, :angle 90.0}})