(ns kata.test.kata5
  (:use [kata.kata5])
  (:use [clojure.test]))


;; (deftest test-ha
;;   (is (= 3 (hash-array "box")))
;;   (is (= 3 (hash-array "fish")))
;;   (is (= 3 (hash-array "A"))))

(deftest test-bh
  (is (= [true false true] (bloom-hash [true false false] [false false true]))))

(deftest something
  (is (= 3 (create-bloom))))

