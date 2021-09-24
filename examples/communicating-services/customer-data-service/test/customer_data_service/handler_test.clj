(ns customer-data-service.handler-test
  (:require [clojure.test :refer [deftest testing is]]
            [ring.mock.request :as mock]
            [customer-data-service.handler :as h]))

(deftest test-app
  (testing "main route"
    (let [response (h/app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (h/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
