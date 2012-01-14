(ns kata.test.kata5
  (:use [kata.kata5])
  (:use [clojure.test]))

(deftest test-ha
  (is (= 3 (hash-array "box")))
  (is (= 3 (hash-array "fish"))))

(deftest something
  (is (= 3 (create-bloom))))