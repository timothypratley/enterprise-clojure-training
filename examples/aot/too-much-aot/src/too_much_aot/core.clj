(ns too-much-aot.core
  (:require [too-much-aot.dont-aot-me])
  (:gen-class))

(defn -main [& _args]
  (println "Hello, World!"))
