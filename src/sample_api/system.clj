(ns sample-api.system
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [sample-api.pedestal :refer :all]
            [sample-api.routes :refer :all]))

(defn new-system [env]
  (component/system-map
   :service-map {:env env
                 ::http/routes routes
                 ::http/type :jetty
                 ::http/port 8890
                 ::http/join? false}
   :pedestal (component/using (new-pedestal) [:service-map])))