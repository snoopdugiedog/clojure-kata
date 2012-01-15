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

(defn hash-indices [word numbits numhashes]
  (sort (map #(index-hash (str %1 word %1) numbits)
             (range 0 numhashes))))

(defn falses [numbits]
  (repeat numbits false))

;; indices must be sorted and all less than numbits
(defn index-bit-array
  ([indices numbits] (index-bit-array indices numbits [] 0))
  ([indices numbits initial index]
     (if (empty? indices)
       (concat initial (falses (- numbits index)))
       (index-bit-array (rest indices) numbits
                        (concat initial
                                (falses (- (first indices) index))
                                [true])
                        (+ (first indices) 1))))
  )


(defn set-bits [array word numbits numhashes]
  (trampoline ))


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