(ns parsing-with-spec.generated-test
  #_(:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.gen.alpha :as gen]
            [parsing-with-spec.core :refer [valid]]))

;; get some test data
#_(prn (gen/sample (s/gen :mega-corp/insurance-policy)))

;; check that the function works for all valid inputs
;; ... finds a bug! :)
;; ... gives the shortest reproduction input
#_
(with-redefs [println (constantly nil)]
  (prn (stest/check `valid)))

