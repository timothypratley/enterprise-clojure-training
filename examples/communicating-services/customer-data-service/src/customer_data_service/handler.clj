(ns customer-data-service.handler
  (:require [compojure.core :as c]
            [compojure.route :as route]
            [ring.middleware.defaults :as d]
            [customer-data-service.db :as db]
            [ring.util.response :as response]))

(c/defroutes app-routes
  (c/GET "/status" []
    (response/response "Customer data service is running"))
  (c/GET "/customer/:id" [id]
    (response/response (db/find-by-id id)))
  (c/GET "/find-by-name/:customer-name" [customer-name]
    (response/response (db/find-by-name customer-name)))
  (route/not-found "Not Found"))

(def app
  (d/wrap-defaults app-routes d/site-defaults))
