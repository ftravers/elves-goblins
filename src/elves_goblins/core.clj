(ns elves-goblins.core)

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

