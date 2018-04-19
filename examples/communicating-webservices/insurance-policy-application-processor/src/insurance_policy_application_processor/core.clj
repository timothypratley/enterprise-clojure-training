(ns insurance-policy-application-processor.core
  (:gen-class)
  (:require [clj-http.client :as client]))

(defn fetch-customer [id]
  (client/get (str "http://customer-data-service:3000/customer/" id)))

(defn -main [& args]
  (println "Starting Insurance Policy Application Processor...")
  (Thread/sleep 5000)
  (prn (fetch-customer "1"))
  (println "Done."))
