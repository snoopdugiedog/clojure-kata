(ns kata.kata5
  (:use [clojure.java.io :only (reader)]))

(defn bit-array [numbits]
  (repeat numbits false))

(defn add-byte [int byte]
  (bit-or (bit-shift-left int 8) byte))

(defn byte-array-to-int [array]
  (reduce add-byte 0 (take 4 array)))

(defn single-hash [text]
  (byte-array-to-int (.digest (java.security.MessageDigest/getInstance "md5")
                              (.getBytes text)))
  )

(defn index-hash [text numbits]
  (mod (single-hash text) numbits))

(defn nums []
  (range 0 256))

(defn create-hash-bit [word i]
  (= 1 (mod (single-hash (str i word i)) 2)))


(defn hash-array [word]
  (map #(create-hash-bit word %) (nums))
  )

(defn bloom-hash [numbits numhashes array string]

  )


(defn create-bloom []
  (with-open [rdr (reader "/usr/share/dict/words")]
    (reduce #(bloom-hash 1024 32 %1 %2) (bit-array 1024) (line-seq rdr))))