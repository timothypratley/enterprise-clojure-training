(ns insurance-policy-service.db)

(defonce backend (atom {:corgi-cover {:id :corgi-cover
                                      :name "Corgi Cover"}
                        :poodle-protection {:id :poodle-protection
                                            :name "Poodle Protection"}}))

(defn find-all []
  @backend)
