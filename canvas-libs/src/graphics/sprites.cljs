(ns graphics.sprites)

(def BRUTE {:size 27})
(def DWARF {:w 32 :h 19})
(def ADVENTURER {:size 24})

(defn load-img
  [src]
  (let [img (js/Image. src)]
    (set! (.-src img) src)
    img))

"brute"
(defn brutehit
  [left?]
  (if left?
    (load-img "sprites/brute/hit/left.png")
    (load-img "sprites/brute/hit/right.png")))

(defn brutestand
      [left?]
      (if left?
        (load-img "sprites/brute/stand/left.png")
        (load-img "sprites/brute/stand/right.png")))

(defn bruterun
      [left?]
      (if left?
        (load-img "sprites/brute/run/left.png")
        (load-img "sprites/brute/run/right.png")))

"dwarf"
(defn dwarfhit
      [left?]
      (if left?
        (load-img "sprites/dwarf/hit/left.png")
        (load-img "sprites/dwarf/hit/right.png")))

(defn dwarfstand
      [left?]
      (if left?
        (load-img "sprites/dwarf/stand/left.png")
        (load-img "sprites/dwarf/stand/right.png")))

(defn dwarferun
      [left?]
      (if left?
        (load-img "sprites/dwarf/run/left.png")
        (load-img "sprites/dwarf/run/right.png")))


"adventurer"
(defn adventurerhit
      [left?]
      (if left?
        (load-img "sprites/adventurer/hit/left.png")
        (load-img "sprites/adventurer/hit/right.png")))

(defn adventurerstand
      [left?]
      (if left?
        (load-img "sprites/adventurer/stand/left.png")
        (load-img "sprites/adventurer/stand/right.png")))

(defn adventurerrun
      [left?]
      (if left?
        (load-img "sprites/adventurer/run/left.png")
        (load-img "sprites/adventurer/run/right.png")))
