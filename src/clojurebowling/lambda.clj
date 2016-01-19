(ns clojurebowling.lambda
  (:require [uswitch.lambada.core :refer [deflambdafn]]
            [clojure.data.json :as json]
            [clojurebowling.bowling-scorer :as bowling-scorer]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-int
  "More robust conversion of strings to integers"
  [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))

(defn handle-event
  [event]
  (let [score (bowling-scorer/score (map parse-int (seq (str/split (get event "rolls") #","))))]
    (println "The bowling score is: " score)

    {:status "HUMBABY",
     :score  score}
    ))

(deflambdafn clojurebowling.lambda.MyLambdaFn
             [in out ctx]
             (let [event (json/read (io/reader in))
                   res (handle-event event)]
               (with-open [w (io/writer out)]
                 (json/write res w))))


