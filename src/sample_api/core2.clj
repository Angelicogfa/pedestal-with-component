(ns sample-api.core2
  (:require [com.stuartsierra.component :as component]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as jdbc]))

(def settings {:environment :prod
               :host        "0.0.0.0"
               :port         8080
               :database {:connection-string (env :connection-string "jdbc:sqlserver://localhost:1433;databaseName=sample_database;user=sa;password=P@ssw0rd1!")}})

(defrecord Database [config]
  component/Lifecycle
  (start [this]
    (->> (:connection-string config)
         (assoc this :conn)))
  (stop [this]
    (if-let [conn (:conn this)]
      (do (.close conn)
          (assoc this :conn nil))
      this)))

(defn new-database [config]
  (->Database config))

(defprotocol Query
  (select [this query]))

(defrecord DbQuery [^Database db]
  Query
  (select [this query]
    (let [conn (:conn db)]
      (jdbc/query conn query))))

(defn new-sql []
  (map->DbQuery {}))

(defn base [setting]
  (let [database (:database setting)]
    (component/system-map
     :database (new-database database)
     :sql (component/using (new-sql) [:database]))))

(defonce system (atom nil))

(defn boostrap! [settings]
  (->> (base settings)
       (component/start)
       (reset! system)))

(defn stop-system! []
  (swap! system component/stop))

(boostrap! settings)
(stop-system!)