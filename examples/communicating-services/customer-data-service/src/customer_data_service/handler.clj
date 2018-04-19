(ns customer-data-service.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [customer-data-service.db :as db]
            [ring.util.response :as response]))

(defroutes app-routes
  (GET "/status" []
    (response/response "Customer data service is running"))
  (GET "/customer/:id" [id]
    (response/response (db/find-by-id id)))
  (GET "/find-by-name/:customer-name" [customer-name]
    (response/response (db/find-by-name customer-name)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
