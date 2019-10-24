(defproject sample-api "0.0.1"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.stuartsierra/component "0.4.0"]

                  ;; Pedestal
                 [io.pedestal/pedestal.service "0.5.7"]
                 [io.pedestal/pedestal.route "0.5.7"]
                 [io.pedestal/pedestal.jetty "0.5.7"]
                 [org.clojure/data.json   "0.2.6"]
                 [org.slf4j/slf4j-simple "1.7.28"]

                 [environ "1.1.0"]
                 [org.clojure/java.jdbc "0.7.10"]
                 [com.microsoft.sqlserver/mssql-jdbc "7.4.1.jre8"]]
  :main sample-api.main
  :aot [sample-api.main])