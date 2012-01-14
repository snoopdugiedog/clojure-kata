(ns kata.kata5
  (:use [clojure.java.io :only (reader)]))

(defn single-hash [text]
  (first (.digest (java.security.MessageDigest/getInstance "md5")
                  (.getBytes text))
         )
  )

(defn nums []
  (range 0 256))

(defn create-hash-bit [word i]
  (= 1 (mod (single-hash (str i word i)) 2)))


(defn hash-array [word]
  (map #(create-hash-bit word %) (nums))
  )

(defn bloom-hash [a b]
  (hash-array a))


(defn create-bloom []
  (with-open [rdr (reader "/usr/share/dict/words")]
   (reduce bloom-hash (line-seq rdr))))