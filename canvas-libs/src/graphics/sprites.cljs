(ns graphics.sprites)

(def BRUTE {:size 27})

(defn load-img
  [src]
  (let [img (js/Image. src)]
    (set! (.-src img) src)
    img))

(defn hit
  [left?]
  (if left?
    (load-img "sprites/character/hit/left.png")
    (load-img "sprites/character/hit/right.png")))
