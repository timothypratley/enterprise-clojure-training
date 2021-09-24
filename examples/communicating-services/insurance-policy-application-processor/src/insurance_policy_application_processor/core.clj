(ns insurance-policy-application-processor.core
  (:require [clj-http.client :as client])
  (:gen-class))

(defn fetch-customer [id]
  (client/get (str "http://customer-data-service:3000/customer/" id)))

(defn -main [& _args]
  (println "Starting Insurance Policy Application Processor...")
  (Thread/sleep 5000)
  (prn (fetch-customer "1"))
  (println "Done."))
