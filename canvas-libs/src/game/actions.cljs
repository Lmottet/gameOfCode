(ns game.actions)

(defn all-gamers
  [actions]
  (reduce conj [] actions))
