(ns advent-2020.advent2
  (:require [instaparse.core :as insta]))

;; 1
(def parse-validate
  (insta/parser
   "S = A<#'-'>A<#'\\s'>B<#':\\s'>C
<A> = #'\\d*'
<B> = #'\\w'
C = #'\\w'+"))

(defn pass-validate [parsed-info]
  (let [[_ min max letter pass] parsed-info
        freq (count (filter #(= letter %) pass))]
    (and (>= freq (read-string min)) (<= freq (read-string max)))))

(comment
  (pass-validate (parse-validate "1-3 a: abcde"))
  (count
   (filter #(pass-validate %)
           (map #(parse-validate %)
                (line-seq (clojure.java.io/reader "/tmp/advent-2.txt")))))
  (println "This line is to make repl evals less annoying"))

;; 2
(defn pass-validate2 [parsed-info]
  (let [[_ min max letter pass] parsed-info
        first-pos (nth pass (read-string min))
        second-pos (nth pass (read-string max))]
    (not= (= first-pos letter) (= second-pos letter))))

(comment
  (pass-validate2 (parse-validate "1-3 a: abcde"))
  (count
   (filter #(pass-validate2 %)
           (map #(parse-validate %)
                (line-seq (clojure.java.io/reader "/tmp/advent-2.txt")))))
  (println "There is no point to this line"))
