(ns elves-goblins.core
  (:require [clojure.pprint :as p])) 

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

{:game-size 3
 :creatures [{:type :elf
              :coord [1 2]}
             {:type :goblin
              :coord [2 2]}
             {:type :goblin
              :coord [0 0]}]}

[["#" "#" "#" "#" "#"]
 ["#" "G" "." "." "#"]
 ["#" "." "E" "." "#"]
 ["#" "." "." "G" "#"]
 ["#" "#" "#" "#" "#"]]


(defn make-row [size char]
  (mapv (fn [x] char) (range size)))

(defn interior [game-size]
  (mapv
   (fn [x] (make-row game-size "."))
   (range game-size)))

(defn add-ew-walls [interior]
  (map
   (fn [row] (into [] (cons "#" (conj row "#"))))
   interior))

(defn add-ns-walls [ew-walls-interior]
  (let [game-size (count (first ew-walls-interior))
        north-wall (make-row game-size "#")]
    (into [] (cons north-wall (reverse (cons north-wall ew-walls-interior))))))

(comment
  (def game-size 3)
  (make-row 3 "#")
  (def map3 (interior 3))
  (def ew-walls-interior (add-ew-walls map3))
  (def north-wall (make-row 5 "#"))
  (def all-walls (add-ns-walls ew-walls-interior))
  (map println all-walls)
