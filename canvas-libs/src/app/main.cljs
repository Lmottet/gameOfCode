(ns app.main
  (:require [graphics.grid :refer [start]]
            [graphics.canvas :refer [get-canvas get-ctx]]
            [graphics.board :as b]))

(def N_COLS 20)
(def N_ROWS 20)

(def world (atom {:gamers [[2 7] [4 1]]
                  :col-lines []
                  :row-lines []
                  :col-w 0
                  :row-h 0
                  :c-h 0
                  :c-w 0
                  :n-cols N_COLS
                  :n-rows N_ROWS
                  :board []}))

(defn restart
  [msg]

  (println msg)

  (let [canvas (get-canvas "drawing1")
        c (get-ctx canvas)
        cw (.-width canvas)
        ch (.-height canvas)
        colW (b/column-width N_COLS cw)
        rowH (b/row-height N_ROWS ch)
        colLines (b/generate-columns cw N_COLS colW)
        rowLines (b/generate-rows ch N_ROWS rowH)
        board (b/get-board N_COLS N_ROWS)]

    (swap! world assoc :c-w cw)
    (swap! world assoc :c-h ch)
    (swap! world assoc :col-w colW)
    (swap! world assoc :row-h rowH)
    (swap! world assoc :col-lines colLines)
    (swap! world assoc :row-lines rowLines)
    (swap! world assoc :board board)

    (start c world)))

(defn reload! [] (restart "Code updated!"))
(defn main [] (restart "App loaded!"))
