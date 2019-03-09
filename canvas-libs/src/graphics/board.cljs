(ns graphics.board)

(def fst #(first %))
(def snd #(get % 1))

(defn column-width
  "input: number of columns and canvas width
   returns: the width of a column"
  [nCols width]
  (/ width nCols))

(defn row-height
  "input: number of rows and canvas height
   returns: the height of a row"
  [nRows height]
  (/ height nRows))

(defn get-point
  "input: list of board points, desired index, column width and row height
   returns: the pixel-valued point for the given index"
  [board index colW rowH]
  (let [[x y] (get (vec board) index)]
    [(* x colW) (* y rowH)]))

(defn get-2d
  "input: list index and number of columns
   returns: a point vector with x and y"
  [i nCols]
  [(Math/floor (mod i nCols))
   (Math/floor (/ i nCols))])

(defn get-board
  "input: generated columns and rows
   returns: a vector of cases (which is a map w/ start point, height and width) [{:start :h :w}]"
  [nCols nRows]
  (let [nCases (* nCols nRows)]
    (for [case (range nCases)]
      (get-2d case nCols))))

(defn generate-columns
  "input: ctx height, number of columns and width of a column
   returns: list of columns -> [[pointA pointB] ... ]"
  [h nCols colW]
  (reduce
    (fn [acc i]
      (let [colXPos (* colW i)]
        (conj acc [[colXPos 0] [colXPos h]])))
    [] ; empty list of col lines
    (range nCols 0 -1)))

(defn generate-rows
  "input: ctx width, number of rows and height of a row
   returns: list of rows -> [[pointA pointB] ... ]"
  [w nRows rowH]
  (reduce
    (fn [acc i]
      (let [rowYPos (* rowH i)]
        (conj acc [[0 rowYPos] [w rowYPos]])))
    [] ; empty list of col lines
    (range nRows 0 -1)))
