(ns graphics.canvas)

(defn request-animation-frame [f] (js/requestAnimationFrame f))

(defn get-canvas
  "input: canvas html id (string)
   returns: canvas DOM element"
  [canvasId]
  (.getElementById js/document canvasId))

(defn get-ctx
  "input: canvas DOM element
   returns: canvas 2D context"
  [canvas]
  (.getContext canvas "2d"))
