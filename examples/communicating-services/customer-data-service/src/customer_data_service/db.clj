(ns customer-data-service.db
  (:require [clojure.string :as str]))

(defonce backend (atom {1 {:name "Chloe"}}))

(defn find-by-id [id]
  (get @backend id))

(defn find-by-name [s]
  (vec
    (for [[k v] @backend
          :when (str/includes?
                  (some-> v (:name) (str/lower-case))
                  (str/lower-case s))]
      v)))
