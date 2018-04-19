(ns insurance-policy-service.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response]
            [insurance-policy-service.db :as db]))

(defroutes app-routes
  (GET "/status" []
    (response/response "Insurance policy service is running"))
  (GET "/policies" []
    (response/response (db/find-all)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
