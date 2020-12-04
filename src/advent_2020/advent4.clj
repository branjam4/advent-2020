(ns advent-2020.advent4
  (:require [instaparse.core :as insta]
            [clojure.spec.alpha :as s]))

;; 1
(def parse-validate
  (insta/parser
   "passport = ((ecl | pid | eyr | hcl | byr | iyr | cid | hgt) <#'(\\s|\\n)?'>)+
ecl = <#'ecl:'>#'[^\\s-]+'
pid = <#'pid:'>#'[^\\s-]+'
eyr = <#'eyr:'>#'[^\\s-]+'
byr = <#'byr:'>#'[^\\s-]+'
iyr = <#'iyr:'>#'[^\\s-]+'
hcl = <#'hcl:'>#'[^\\s-]+'
cid = <#'cid:'>#'[^\\s-]+'
hgt = <#'hgt:'>#'[^\\s-]+'"))

(defn pass-validate [parsed-info]
  (s/valid? ::passport parsed-info))

(s/def ::passport (s/keys :req-un [::ecl ::pid ::eyr ::byr ::iyr ::hcl ::hgt] :opt-un [::cid]))

(comment
  (count
   (filter
    #(pass-validate %)
    (map #(into (sorted-map) (rest %))
         (map #(parse-validate %)
              (clojure.string/split (slurp "/tmp/advent-4.txt") #"\n\n"))))) ; 208
  (count
   (filter #(pass-validate %)
           (map #(parse-validate %)
                (clojure.string/split (slurp "/tmp/advent-4-test.txt") #"\n\n"))))
  (println "Hello."))

;; 2 - Incomplete
(defn passport? [parsed-info] 
  (s/explain ::passport parsed-info))

(defn hexadecimal? [hair-color])
  
  

(s/def ::byr (s/and integer? #(<= 1920 % 2002) #(= (count %) 4)))
(s/def ::iyr (s/and integer? #(<= 2010 % 2020) #(= (count %) 4)))
;; (s/def ::eyr (s/and integer? #(<= 2020 % 2030) #(= (count %) 4)))
(s/def ::eyr (s/and #(integer? (read-string %)) #(<= 2020 (read-string %) 2024) #(= (count %) 4)))
(s/def ::hgt (s/or ()))
(s/def ::hcl (s/and #(= (first %) \#) #(= (count %) 7) #(= (rest %) #"\\[0-9a-f+\\]")))
(s/def ::ecl #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})
(s/def ::pid (s/and integer? #(= (count %) 9)))

(comment
  (count
   (map
    #(passport? %)
    (map #(into (sorted-map) (rest %))
         (map #(parse-validate %)
              (clojure.string/split (slurp "/tmp/advent-4-test.txt") #"\n\n")))))
  (println "Hello."))
