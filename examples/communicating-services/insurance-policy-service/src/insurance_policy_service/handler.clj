(ns insurance-policy-service.handler
  (:require [compojure.core :as c]
            [compojure.route :as route]
            [ring.middleware.defaults :as d]
            [ring.util.response :as response]
            [insurance-policy-service.db :as db]))

(c/defroutes app-routes
  (c/GET "/status" []
    (response/response "Insurance policy service is running!!!!"))
  (c/GET "/policies" []
    (response/response
      (str (db/find-all))))
  (route/not-found "Not Found"))

(def app
  (d/wrap-defaults app-routes d/site-defaults))
