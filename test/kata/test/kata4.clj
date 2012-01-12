(ns kata.test.kata4
  (:use [kata.kata4])
  (:use [clojure.test]))

(deftest testread
  (is (= 30 (temp))))

(deftest firstthreetest
  (are [expect text] (= expect (firstthree text))
       [2 63 63] "   2  63    63    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5"
       [5 63 64] "   5  63    64    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5"
       [7 63 65] "   7  63    65    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5"
       [12 20 35] "   12  20    35    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5"))


(deftest testspread
  (are [expect text] (= expect (spread text))
       [2 0] [2 63 63]
       [3 1] [3 64 63]
       [4 2] [4 65 63]
       [5 15] [5 35 20]))
