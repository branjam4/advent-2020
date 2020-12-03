(ns advent-2020.advent1
  (:require [clojure.math.combinatorics :as comb]))

;; 1
(comment
  (reduce *
          (first (filter #(= (reduce + %) 2020)
                         (comb/selections
                          (map #(read-string %)
                               (line-seq
                                (clojure.java.io/reader "/tmp/advent-1.txt")))
                          2))))) ; 494475
          
;; 2
(comment 
  (reduce *
          (first (filter #(= (reduce + %) 2020)
                         (comb/selections
                          (map #(read-string %)
                               (line-seq
                                (clojure.java.io/reader "/tmp/advent-1.txt")))
                          3)))))  ; 267520550
