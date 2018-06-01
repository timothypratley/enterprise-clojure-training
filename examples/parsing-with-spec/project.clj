(defproject parsing-with-spec "0.1.0-SNAPSHOT"
  :description "Example of parsing with spec"
  :url "https://github.com/timothypratley/enterprise-clojure-training/examples/parsing-with-spec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.csv "0.1.4"]
                 [org.clojure/test.check "0.9.0"]
                 [expound "0.5.0"]]
  :aot [parsing-with-spec.core]
  :main parsing-with-spec.core)
