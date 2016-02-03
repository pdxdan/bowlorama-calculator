(defproject serverlessclojurebowling "0.1.0-SNAPSHOT"
  :description "My first attempt at using Clojure with AWS Lambda"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [uswitch/lambada "0.1.0"]]
  :profiles {:uberjar {:aot :all}}
  :uberjar-name "bowling-scorer.jar")

