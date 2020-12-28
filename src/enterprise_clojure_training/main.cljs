(ns enterprise-clojure-training.main
  (:require [cljs.spec.alpha :as s]
    ;; Requiring this namespace has the side-effect of loading Klipse
            [klipse.run.plugin.plugin]
            [meander.epsilon :as m]))

(s/def ::big-integer (s/and integer?
                            #(> % 1000000)))

(println "Hello world!")

(m/match {} {} {})
