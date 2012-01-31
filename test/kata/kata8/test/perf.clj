(ns kata.kata8.test.perf
  (:use [kata.kata8.perf])
  (:use [clojure.test]))


(deftest test-string-before
  (are [substring index] (= substring (string-before "012345" index))
       "0" 1
       "01" 2
       "012" 3
       "0123" 4
       "01234" 5
       ))


(deftest test-string-after
  (are [substring index] (= substring (string-after "012345" index))
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


(deftest test-is-concatenated
  (are [index words] (is-concatenated words "012345")
       [#{} #{"0"} #{} #{} #{} #{"12345"} #{"012345"}]
       [#{} #{} #{"01"} #{} #{"2345"} #{} #{"012345"}]
       [#{} #{} #{} #{"012" "345"} #{} #{} #{"012345"}]
       [#{} #{} #{"45"} #{} #{"0123"} #{} #{"012345"}]
       [#{} #{"5"} #{} #{} #{} #{"01234"} #{"012345"}]
       ))

(deftest test-empty-words-by-size
  (is (= [#{} #{} #{} #{} #{} #{} #{}] (empty-words-by-size))))

(deftest test-add-to-words-by-size
  (are [ending word] (= ending (add-to-words-by-size (empty-words-by-size) word))
       [#{} #{"0"} #{} #{} #{} #{} #{}] "0"
       [#{} #{} #{"01"} #{} #{} #{} #{}] "01"
       [#{} #{} #{} #{"012"} #{} #{} #{}] "012"
       [#{} #{} #{} #{} #{"0123"} #{} #{}] "0123"
       [#{} #{} #{} #{} #{} #{"01234"} #{}] "01234"
       [#{} #{} #{} #{} #{} #{} #{"012345"}] "012345"
       ))


(deftest test-words-by-size
  (are [ending word] (= ending (words-by-size '(word)))
       [#{} #{"0"} #{} #{} #{} #{} #{}] "0"
       [#{} #{} #{"01"} #{} #{} #{} #{}] "01"
       [#{} #{} #{} #{"012"} #{} #{} #{}] "012"
       [#{} #{} #{} #{} #{"0123"} #{} #{}] "0123"
       [#{} #{} #{} #{} #{} #{"01234"} #{}] "01234"
       [#{} #{} #{} #{} #{} #{} #{"012345"}] "012345"
       ))


(deftest test-six-letter-word-joins
  (are [words] (= ["012345"] (six-letter-word-joins words))
       ["0" "12345" "012345"]
       ["01" "2345" "012345"]
       ["012" "345" "012345"]
       ["0123" "45" "012345"]
       ["01234" "5" "012345"]
       ))