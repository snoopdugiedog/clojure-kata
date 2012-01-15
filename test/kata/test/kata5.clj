(ns kata.test.kata5
  (:use [kata.kata5])
  (:use [clojure.test]))

(deftest t-bit-array
  (are [array size] (= array (bit-array size))
       [] 0
       [false] 1
       [false false] 2
       [false false false] 3)
  )

(deftest t-index-hash
  (are [i text] (= i (index-hash text 1024))
       994 "boo"
       898 "Boo"
       593 "Box")
  )

(deftest t-index-bit-array
  (are [indices res] (= res (index-bit-array indices 5))
       [0 3]   [true false false true false]
       [1 4]   [false true false false true]
       []      [false false false false false]
       [1 2]   [false true true false false]
       [0 1 2 3 4] [true true true true true]))

(deftest t-add-byte
  (are [initial final byte] (= final (add-byte initial byte))
       0 7 7
       0 255 255
       1 256 0))

(deftest t-byte-array-to-int
  (are [int array] (= int (byte-array-to-int array))
       0 [0 0 0 0 50 36]
       255 [0 0 0 255 50]
       512 [0 0 2 0 50]
       2745638952 [163 167 32 40])
  )

(deftest something
  (is (= 3 (create-bloom))))

