(defproject bowlorama-calculator "0.1.0-SNAPSHOT"
  :description "Part of the bowlorama demo application that calculates bowling scores based on ball history."
  :url "https://github.com/pdxdan/bowlorama-calculator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [uswitch/lambada "0.1.0"]]
  :profiles {:uberjar {:aot :all}}
  :uberjar-name "bowlorama-calculator.jar")

