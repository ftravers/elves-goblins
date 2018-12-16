(ns elves-goblins.core
  (:require [clojure.pprint :as p])) 

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def un-ordered-game-state
  {:game-size 3
   :creatures [{:type :elf
                :coord [1 2]}
               {:type :goblin
                :coord [2 2]}
               {:type :goblin
                :coord [0 0]}]})

(def game-state-ordered
  {:game-size 3
   :creatures [{:type :goblin
                :coord [0 0]}
               {:type :elf
                :coord [1 2]}
               {:type :goblin
                :coord [2 2]}]})

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
  (def creat-coords (map :coord (un-ordered-game-state :creatures)))
  (map :coord (un-ordered-game-state :creatures))
  (def game-size 3)
  (make-row 3 "#")
  (def map3 (interior 3))
  (def ew-walls-interior (add-ew-walls map3))
  (def north-wall (make-row 5 "#"))
  (def all-walls (add-ns-walls ew-walls-interior))
  (map println all-walls)
  (def game-size 3)
  (make-row 3 "#")
  (def map3 (interior 3))
  (def ew-walls-interior (add-ew-walls map3))
  (def north-wall (make-row 5 "#"))
  (def all-walls (add-ns-walls ew-walls-interior))
  (map println all-walls))

(comment
  (def r1 1)
  (def c1 1)
  (def r2 2)
  (def c2 2)
  (sort-by
   identity
   (fn [[r1 c1] [r2 c2]]
     (let [row-diff (- r1 r2)
           col-diff (- c1 c2)]
       (cond
         (> row-diff 0) -1
         (< row-diff 0) 1
         (= row-diff)
         (cond
           (> col-diff 0) -1
           (< col-diff 0) 1)))
     '([1 2] [2 2] [0 0]))))

;; => ([1 2] [2 2] [0 0]) 
  ;; (sort-by
  ;;  identity
  ;;  (fn [[r1 c1] [r2 c2]]
  ;;    (let [row-diff (- r1 r2)
  ;;          col-diff (- c1 c2)]
  ;;      (cond
  ;;        (> row-diff 0) -1
  ;;        (< row-diff 0) +1 
  ;;        (= row-diff)
  ;;        (cond
  ;;          (> col-diff 0) -1
  ;;          (< col-diff 0) 1)))
  ;;    '([1 2] [2 2] [0 0])) 
