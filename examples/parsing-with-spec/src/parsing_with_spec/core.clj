(ns parsing-with-spec.core
  (:gen-class)
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.spec.alpha :as s]
            [expound.alpha :as e]))

;; Specs

;; Insurance policy shared fields

(s/def :mega-corp/name string?)
(s/def :mega-corp/policy-count int?)


;; The "Corgi Cover" policy is only available in certain USA states

(s/def :corgi-cover/state #{"IL" "WA" "NY" "CO"})
(s/def :corgi-cover/corgi-count pos-int?)

(s/def :mega-corp/corgi-cover
  (s/keys :req-un [:mega-corp/name
                   :corgi-cover/state
                   :corgi-cover/corgi-count]))

;; The "Poodle Protection" policy is only available in some other set of USA states

(s/def :poodle-protection/state #{"CA" "FL" "WY" "HI"})
(s/def :poodle-protection/poodle-count pos-int?)

(s/def :mega-corp/poodle-protection
  (s/keys :req-un [:mega-corp/name
                   :poodle-protection/state
                   :poodle-protection/poodle-count]))


;; "Poodle Protection Platinum" is available to people with 3 or more Poodles.

(s/def :poodle-protection-platinum/poodle-count (s/and pos-int? #(<= 3 %)))

(s/def :mega-corp/poodle-protection-platinum
  (s/keys :req-un [:mega-corp/name
                   :poodle-protection/state
                   :poodle-protection-platinum/poodle-count]))


;; All insurance policies

(s/def :mega-corp/insurance-policy
  (s/or :mega-corp/corgi-cover :mega-corp/corgi-cover
        :mega-corp/poodle-protection :mega-corp/poodle-protection
        :mega-corp/poodle-protection-platinum :mega-corp/poodle-protection-platinum))


(defn process-applications [valid invalid policy-applications]
  (doseq [a policy-applications]
    (if (s/valid? :mega-corp/insurance-policy a)
      (valid a)
      (invalid a))))


;; Data shaping

(defn parse-int [s]
  (try
    (Integer/parseInt s)
    (catch Exception _ex 0)))

(defn mapize [csv-rows]
  (let [[header & lines] csv-rows
        ks (for [field header]
             (keyword field))]
    (for [line lines]
      (-> (zipmap ks line)
          (update :corgi-count parse-int)
          (update :policy-count parse-int)
          (update :poodle-count parse-int)))))

(defn process-applications-file [valid invalid filename]
  (with-open [r (io/reader filename)]
    (->> (csv/read-csv r)
         (mapize)
         (process-applications valid invalid))))

(defn valid [application]
  (let [[policy-type {:keys [poodle-protection/poodle-count
                             policy-count]}]
        (s/conform :mega-corp/insurance-policy application)]
    ;; this passes unit tests, but what if the the policy-count was 0?
    ;; see generative tests
    (when (= policy-type :mega-corp/poodle-protection)
      (println "Poodle to policy ratio:" (/ poodle-count policy-count)))

    ;; write to accepted log
    (println "VALID:" policy-type application)))

(s/fdef valid :args (s/cat :application :mega-corp/insurance-policy))

(defn invalid [application]
  ;; write to discard log
  (println "INVALID:" application)
  ;;(s/explain ::policy-application application)
  (e/expound :mega-corp/insurance-policy application))

(defn -main [& args]
  (println "Starting file processing...")
  (let [filename (or (first args) "data/insurance_policy_applications.txt")]
    (process-applications-file valid invalid filename))
  (println "Done."))
