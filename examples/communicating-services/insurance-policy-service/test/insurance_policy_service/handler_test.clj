(ns insurance-policy-service.handler-test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.mock.request :as mock]
            [insurance-policy-service.handler :as h]))

(deftest test-app
  (testing "main route"
    (let [response (h/app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (h/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
