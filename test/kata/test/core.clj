(ns kata.test.core
  (:use [kata.core])
  (:use [clojure.test]))

(deftest test-mid
  (are [ret array] (= ret (mid array))
       0 [0]
       1 [1]
       1 [0 1]
       1 [0 1 2]
       2 [0 1 2 3]
       2 [0 1 2 3 4]))

(deftest test-before-mid
  (are [first whole] (= first
                        (before-mid whole))
       [] [0]
       [0] [0 1]
       [0] [0 1 2]
       [0 1] [0 1 2 3]
       [0 1] [0 1 2 3 4])
  )

(deftest test-after-mid
  (are [last whole] (= last
                        (after-mid whole))
       [] [0]
       [] [0 1]
       [2] [0 1 2]
       [3] [0 1 2 3]
       [3 4] [0 1 2 3 4]
       [4 5] [0 1 2 3 4 5])
  )

(deftest not-there1
  (are [x array] (= -1 (chop x array))
       3 []
       3 [1]
       )
  )

(deftest there1
  (is (= 0 (chop 1 [1]))))

(deftest not-there3
  (are [x] (= -1 (chop x [1 3 5]))
       0 2 4 6)
  )
(deftest there3
  (are [ret x] (= ret (chop x [1 3 5]))
       0 1
       1 3
       2 5))

(deftest not-there4
  (are [x] (= -1 (chop x [1 3 5 7]))
       0 2 4 6 8)
  )
(deftest there4
  (are [ret x] (= ret (chop x [1 3 5 7]))
       0 1
       1 3
       2 5
       3 7))


(deftest not-there5
  (are [x] (= -1 (chop x [1 3 5 7 9]))
       0 2 4 6 8 10)
  )
(deftest there5
  (are [ret x] (= ret (chop x [1 3 5 7 9]))
       0 1
       1 3
       2 5
       3 7
       4 9))

(deftest not-there6
  (are [x] (= -1 (chop x [1 3 5 7 9 11]))
       0 2 4 6 8 10 12)
  )
(deftest there6
  (are [ret x] (= ret (chop x [1 3 5 7 9 11]))
       0 1
       1 3
       2 5
       3 7
       4 9
       5 11))
