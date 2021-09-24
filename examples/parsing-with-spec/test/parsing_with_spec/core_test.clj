(ns parsing-with-spec.core-test
  (:require [clojure.test :refer [deftest is]]
            [parsing-with-spec.core :as p]
            [clojure.spec.alpha :as s]
            [expound.alpha :as e]))

(def good
  {:name "Chloe"
   :state "WA"
   :corgi-count 1
   :policy-count 1})

(def bad
  {:name "Chloe"
   :state "TAS"
   :corgi-count 1
   :policy-count 1})

(deftest test-validation
  (is (not (s/valid? :mega-corp/corgi-cover bad))
      "TAS is not be a valid state for corgi-cover")
  (is (s/valid? :mega-corp/insurance-policy good)
      (e/expound-str :mega-corp/insurance-policy good))
  (is (not (s/valid? :mega-corp/insurance-policy bad))
      "TAS is not a valid state for any policy"))

(deftest test-validation-handling
  (let [insurance-policy-applications [good bad]
        valid-count (atom 0)
        invalid-count (atom 0)
        valid (fn [_x]
                (swap! valid-count inc))
        invalid (fn [_x]
                  (swap! invalid-count inc))]
    (p/process-applications valid invalid insurance-policy-applications)
    (is (= 1 @valid-count))
    (is (= 1 @invalid-count))))

(deftest a-test
  (let [valid-names (atom #{})
        invalid-names (atom #{})
        valid (fn [x]
                (swap! valid-names conj (:name x)))
        invalid (fn [x]
                  ;;(e/expound :mega-corp/insurance-policy x)
                  (swap! invalid-names conj (:name x)))]
    (p/process-applications-file valid invalid "data/insurance_policy_applications.txt")
    (is (= #{"Tiffany" "Annabelle"}
           @invalid-names))
    (is (= #{"Wendy" "Ethan" "Chloe" "Logan"}
           @valid-names))))

