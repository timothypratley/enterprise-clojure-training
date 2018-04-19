(ns little-aot.bootstrap
  (:gen-class))

(defn -main [& args]
  (require (symbol "little-aot.core"))
  ((resolve (symbol "little-aot.core" "-main"))))
