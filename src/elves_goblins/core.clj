(ns elves-goblins.core
  (:require [clojure.pprint :as p]))

{:game-size 3
 :creatures [{:type :elf
              :coord [1 2]}
             {:type :goblin
              :coord [2 2]}
             {:type :goblin
              :coord [0 0]}]}

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
  (map println all-walls))

'(draw-game level1)

(defn draw-game [state]
  (reduce assoc-creature
          (:level state)
          (:creatures state)))

(def level1 {:game-size 3
             :creatures [{:type :elf
                          :coord [1 2]}
                         {:type :goblin
                          :coord [2 2]}
                         {:type :goblin
                          :coord [3 3]}]
             :level [["#" "#" "#" "#" "#"]
                     ["#" "." "." "." "#"]
                     ["#" "." "." "." "#"]
                     ["#" "." "." "." "#"]
                     ["#" "#" "#" "#" "#"]]})

(defn assoc-creature [l c]
  (update l (-> c
                :coord
                first)
          #(assoc % (-> c
                        :coord
                        second)
                  (condp = (:type c)
                    :goblin "G"
                    :elf "E"))))

#_(assoc-creature (:level level1)
                 (-> level1
                     :creatures
                     first))

#_(update (:level level1) 1 (fn [v] ["#" "#" "#" "#" "#"]))
