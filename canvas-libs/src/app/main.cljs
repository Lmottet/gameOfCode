(ns app.main
  (:require [graphics.grid :refer [startGame]]))

(def N_COLS 20)
(def N_ROWS 20)

(defn reload!
  []
  (println "Code updated!")
  (startGame "drawing1" N_COLS N_ROWS))

(defn main
  []
  (println "App loaded!")
  (startGame "drawing1" N_COLS N_ROWS))
