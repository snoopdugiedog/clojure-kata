(ns kata.test.kata6
  (:use [kata.kata6])
  (:use [clojure.test]))


(deftest tword-key
  (are [sorted original] (= sorted (word-key original))
       "abr" "bar"
       "aaab" "baaa"))

(deftest tadd-to-map
  (are [out in word] (= out (add-to-map in word))
       {"abr" ["bar"]} {} "bar"
       {"abr" ["bar" "rab"]} {"abr" ["rab"]} "bar"
       {"abr" ["bar"] "fhis" ["fish"]} {"abr" ["bar"]} "fish"
       ))

