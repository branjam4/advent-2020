(ns advent-2020.advent3)

;; 1
;; For each line l (starting from 0) with length N, take the position
;; modN(3l) in that line and check for a "#"
(defn check-tree [row]
  (let [[line-num line] row
        position (mod (* line-num 3) (count line))]
    (= (nth line position) \#)))
  
(comment
  (mod (* 11 3) 11)
  (count "#.##.......")
  (= (nth  "#.##......." 0) \#)
  (check-tree [10 "..##......."])
  (count
   (filter
    check-tree
    (map-indexed
     vector
     (line-seq (clojure.java.io/reader "/tmp/advent-3.txt"))))) ; 244
  (println "Hello"))

;; 2
;; Generalize the first function so that instead of modN(3l),
;; it works for modN(u*l)
;; Note that one of my tree checks must skip odd rows.
;; Multiply the results together. Test answer is 336.
(defn check-tree-2 [row n]
  (let [[line-num line] row
        position (mod (* line-num n) (count line))]
    (= (nth line position) \#)))
  
(def tree-map
  (map-indexed
   vector
   (line-seq (clojure.java.io/reader "/tmp/advent-3.txt"))))

(defn filter-rows [row n]
  (= (mod (nth row 0) n) 0))

(defn trees-for-rule [rule]
  (let [[go-right go-down] rule]
    (filter
     #(check-tree-2 % go-right)
     (map-indexed
      vector
      (map second
           (filter #(filter-rows % go-down) tree-map))))))

(def rules
  [[1 1] [3 1] [5 1] [7 1] [1 2]])

(comment
  (map-indexed vector (map second (filter #(filter-rows % 2) tree-map)))
  (count (trees-for-rule [1 2]))
  (reduce * (map #(count (trees-for-rule %)) rules)) ; 9406609920
  (println "Hello"))
