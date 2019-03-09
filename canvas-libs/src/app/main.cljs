(ns app.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [graphics.grid :refer [start]]
            [graphics.canvas :refer [get-canvas get-ctx]]
            [graphics.board :as b]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(def N_COLS 20)
(def N_ROWS 20)

(def world (atom {:actions []
                  :shown-actions nil
                  :col-lines []
                  :row-lines []
                  :col-w 0
                  :row-h 0
                  :c-h 0
                  :c-w 0
                  :n-cols N_COLS
                  :n-rows N_ROWS
                  :board []}))

(defn get-actions
  [cb]
  (go
    (let [response (<! (http/get "http://localhost:3000/Rounds"))]
      (cb response))))

(defn run
  [msg]

  (println msg)

  (get-actions (fn [resp]
                   (swap! world assoc :actions (:body resp))
                   (doall (for [i (range 0 (count (:actions @world)))] ; number of iterations needed
                            (js/setTimeout
                              (fn []
                                  (swap! world assoc :shown-actions (:RoundActions (get (:actions @world) i))))
                              (* i 2000))))))

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

(defn reload! [] (run "Code updated!"))
(defn main [] (run "App loaded!"))
