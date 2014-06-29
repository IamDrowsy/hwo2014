(ns hwo2014bot.core
  (:require [clojure.data.json :as json]
            [clojure.pprint :as pp]
            [hwo2014bot.handle :refer [handle-msg]])
  (:use [aleph.tcp :only [tcp-client]]
        [lamina.core :only [enqueue wait-for-result wait-for-message close closed?]]
        [gloss.core :only [string]])
  (:gen-class))

(defn- json->clj [string]
  (json/read-str string :key-fn keyword))

(defn send-message [channel message]
  (enqueue channel (json/write-str message)))

(defn read-message [channel]
  (json->clj
    (try
      (let [result (wait-for-message channel)]
        (if (= result "{}")
          (do (close channel)
            result)
          result))
      (catch Exception e
        (println (str "ERROR: " (.getMessage e)))
        #_(System/exit 1)))))

(defn connect-client-channel [host port]
  (wait-for-result
   (tcp-client {:host host,
                :port port,
                :frame (string :utf-8 :delimiters ["\n"])})))

(defn log-msg [msg]
  (case (:msgType msg)
    "join" (println "Joined")
    "gameStart" (println "Race started")
    "gameEnd" (println "Race ended")
    "error" (println (str "ERROR: " (:data msg)))
    :noop))

(def all-data (atom {}))

(defn game-loop [channel race-info cars-info version data-atom]
  (if (closed? channel)
    (println "Channel closed")
    (let [msg (try (read-message channel)
                (catch Exception e
                  {:msgType "empty"}))
          m {:race race-info :input msg :cars cars-info}
          id (:id race-info)
          result (try (handle-msg m)
                   (catch Exception e
                     #_(write-data (str "error-" id (if version "-") version ".edn")
                                  m)
                     (swap! data-atom conj m)
                     (throw e)
                     {:race  race-info
                      :cars  cars-info}))]
      (log-msg msg)
      (swap! data-atom conj result)
      (if (:answer result) (send-message channel (:answer result)))
      (recur channel (:race result) (:cars result) version data-atom))))


(defn -main[& [host port botname botkey trackname]]
  (let [channel (connect-client-channel host (Integer/parseInt port))]
    (reset! all-data [])
    (if trackname
      (send-message channel {:msgType "createRace" :data {:botId {:name botname :key botkey}
                                                          :trackName trackname
                                                          :carCount 1}})
      (send-message channel {:msgType "join" :data {:name botname :key botkey}}))
    (game-loop channel {} {}  nil all-data)))
