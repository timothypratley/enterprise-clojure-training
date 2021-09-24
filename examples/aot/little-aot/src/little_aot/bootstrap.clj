(ns little-aot.bootstrap
  (:gen-class))

(defn -main [& _args]
  (require (symbol "little-aot.core"))
  ((resolve (symbol "little-aot.core" "-main"))))
