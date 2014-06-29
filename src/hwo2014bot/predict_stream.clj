(ns hwo2014bot.predict-stream
  (:require [hwo2014bot.physic :as p]
            [hwo2014bot.util :as u]))

(defn prediction-step [track physic [d-2 d-1] input]
  (p/next-data-entry track physic d-2 d-1 input))

(defn predictions [track physic d-2 d-1 inputs]
 (let [p (partial prediction-step track physic)]
   (persistent! (reduce (fn [result input]
                          (let [n (count result)]
                            (conj! result (p [(nth result (- n 2))
                                              (nth result (- n 1))] input))))
                        (transient [d-2 d-1])
                        inputs))))

(defn one-predict
  ([track physic d-2 d-1 n init-input]
    (predictions track physic d-2 d-1 (cons init-input (repeat n {:throttle 1.0}))))
  ([track physic d-2 d-1 n]
    (one-predict track physic d-2 d-1 n {:throttle 1.0})))


(defn continue
  "Continues a prediction with new input
   n has to be greater than 1! Because it will take the to preds before n and continues"
  [track physic preds n inputs]
  (into [] (concat (subvec preds 0 (- n 2))
                   (predictions track physic
                                (preds (- n 2)) (preds (- n 1))
                                inputs))))

(defn first-crash
  [track preds]
  (some (partial p/crash? track) preds))

(defn first-crash-point
  [preds crash]
  (.indexOf preds crash))

(defn no-crash [track preds]
  (not (first-crash track preds)))


(defn valid-preds? [track physic throttle preds start]
  (let [n (count preds)
        inputs (repeat (- n start) {:throttle throttle})
        p (continue track physic preds start inputs)]
    (no-crash track p)))

(defn max-valid-one-linear
 ([track physic preds]
   (max-valid-one-linear track physic preds 2 (count preds)))
 ([track physic preds start end]
   #_(println start end)
     (+ start -1 (count (drop-while not (map (partial valid-preds? track physic 0.0 preds)
                                           (range (dec end) (dec start) -1))))))) ;count is +1 index .. so -1

(defn min-valid-zero-linear
  ([track physic preds]
    (min-valid-zero-linear track physic preds 2 (count preds)))
  ([track physic preds start end]
    (+ start (count (take-while not (map (partial valid-preds? track physic 1.0 preds)
                                         (range start end))))))) ;count is +1 index so not +1

(defn sq [d]
  (quot d 2))

(defn max-valid-one-log-step-fn [track physic preds]
  (fn step [start last-start]
    #_(println "max: "start last-start)
    (let [d (Math/abs (- start last-start))]
      (if (<= d 3)
        (max-valid-one-linear track physic preds (- start d) (+ start d)) ;fix because we might miss the first
        (if (valid-preds? track physic 0.0 preds start)
          (step (+ start (sq d)) start)
          (step (- start (sq d)) start))))))

(defn min-valid-zero-log-step-fn [track physic preds]
  (fn step [start last-start]
    #_(println "min:" start last-start)
    (let [d (Math/abs (- start last-start))]
      (if (<= d 3)
        (min-valid-zero-linear track physic preds (- start d) (+ start d)) ;fix because we might miss the first
        (if (valid-preds? track physic 1.0 preds start)
          (step (- start (sq d)) start)
          (step (+ start (sq d)) start))))))


(defn max-valid-one-log [track physic preds]
  (if (no-crash track preds)
    (- (count preds) 2)
    (- ((max-valid-one-log-step-fn track physic preds) (inc (sq (count preds))) (count preds)) 2)))

(defn min-valid-zero-log [track physic preds]
  (if (no-crash track preds)
    (- ((min-valid-zero-log-step-fn track physic preds) (inc (sq (count preds))) (count preds)) 2)
        (- (count preds) 2)))
