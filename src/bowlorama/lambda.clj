(ns bowlorama.lambda
  (:require [uswitch.lambada.core :refer [deflambdafn]]
            [clojure.data.json :as json]
            [bowlorama.calculator :as bcalc]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-int
  "More robust conversion of strings to integers"
  [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))


(defn handle-score-event
  [event]
  (let [score (bcalc/score (map parse-int (seq (str/split (get event "rolls") #","))))]
    {:status "HUMBABY",
     :score  score}))

(deflambdafn bowlorama.lambda.score
             [in out ctx]
             (let [event (json/read (io/reader in))
                   res (handle-score-event event)]
               (with-open [w (io/writer out)]
                 (json/write res w))))



(defn handle-to-frames-event
  [event]
  (let [framescores (bcalc/to-frames (map parse-int (seq (str/split (get event "rolls") #","))))]
    {:status "BOOYAH",
     :framescores  framescores}))

(deflambdafn bowlorama.lambda.to-frames
             [in out ctx]
             (let [event (json/read (io/reader in))
                   res (handle-to-frames-event event)]
               (with-open [w (io/writer out)]
                 (json/write res w))))


