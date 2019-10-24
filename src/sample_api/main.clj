(ns sample-api.main
  (:require [sample-api.system :refer [new-system]]
            [com.stuartsierra.component :as component]))

(defn -main []
  (let [system-map (new-system :prod)]
    (component/start system-map)))