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
       0 [2 63 63]
       1 [2 63 64]
       2 [2 63 65]
       15 [2 20 35]))
