(ns graphics.grid
  (:require [graphics.board :refer [to-pixels-point]]))

(def fst #(get % 0))
(def snd #(get % 1))

(defn draw-line
  "input: ctx, start and end point [x y]
   effect: draws the line"
  [c start end]
  (.moveTo c (fst start) (snd start))
  (.lineTo c (fst end) (snd end))
  (.stroke c))

(defn bold-line-width [c] (set! (.-lineWidth c) 2))
(defn medium-line-width [c] (set! (.-lineWidth c) 1))
(defn black [c] (set! (.-fillStyle c) "#000"))
(defn white [c] (set! (.-fillStyle c) "#FFF"))

(defn draw-lines
  "input: ctx, start iteration and lines vector
   effect: draws all given lines"
  [c lines]
  (doall (for [[a b] lines] (draw-line c a b))))

(defn color-case
  "input: ctx, two points with real pixels
   effect: fills the rectangle from this two points"
  [c [xa ya] w h]
  (black c)
  (.fillRect c xa ya w h))

(defn draw-gamers
  "input: ctx and the world
   effect: draws gamers on their board case"
  [c world]
  (let [wld @world
        board (:board wld)
        colW (:col-w wld)
        rowH (:row-h wld)
        gamers (:gamers wld)]
    (doall (for [pos gamers]
                (let [A (to-pixels-point pos colW rowH)]
                  (color-case c A colW rowH))))))

(defn draw-grid
  "input: ctx and the world
   effect: draws the grid on the context"
  [c world]
  (let [wld @world
        cw (:c-w wld)
        ch (:c-h wld)
        nCols (:n-cols wld)
        nRows (:n-rows wld)
        colW (:col-w wld)
        rowH (:row-h wld)
        rowLines (:row-lines wld)
        colLines (:col-lines wld)]

    ; draw axis lines
    (bold-line-width c)
    (black c)
    (draw-line c [0 0] [cw 0]) ; col
    (draw-line c [0 0] [0 ch]) ; row

    ; draw cols & rows
    (medium-line-width c)
    (draw-lines c colLines)
    (draw-lines c rowLines)))

(defn request-animation-frame [f] (js/requestAnimationFrame f))

(defn render
  "input: ctx, number of grid columns and rows"
  [c world]
  ; clear canvas
  (white c)
  (.clearRect c 0 0 (:c-w @world) (:c-h @world))
  ; draw grid
  (draw-grid c world)
  (draw-gamers c world))

(defn start
  "input: ctx, number of columns and rows
   effect: renders game animation on the canvas"
  [c world]

  (request-animation-frame
    (fn lo []
      (js/setTimeout (request-animation-frame lo) 1000)
      (render c world))))
