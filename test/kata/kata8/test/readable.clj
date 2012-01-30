(ns kata.kata8.test.readable
  (:use [kata.kata8.readable])
  (:use [clojure.test]))


(deftest test-str-before
  (are [substring index] (= substring (str-before "012345" index))
       "0" 1
       "01" 2
       "012" 3
       "0123" 4
       "01234" 5
       ))


(deftest test-str-after
  (are [substring index] (= substring (str-after "012345" index))
       "12345" 1
       "2345" 2
       "345" 3
       "45" 4
       "5" 5
       ))

(deftest test-is-concatenated-at
  (are [index words] (is-concatenated-at words "012345" index)
       1 [#{} #{"0"} #{} #{} #{} #{"12345"} #{"012345"}]
       2 [#{} #{} #{"01"} #{} #{"2345"} #{} #{"012345"}]
       3 [#{} #{} #{} #{"012" "345"} #{} #{} #{"012345"}]
       4 [#{} #{} #{"45"} #{} #{"0123"} #{} #{"012345"}]
       5 [#{} #{"5"} #{} #{} #{} #{"01234"} #{"012345"}]
       ))