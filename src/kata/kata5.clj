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
  ([numbits indices] (index-bit-array numbits indices [] 0))
  ([numbits indices initial index]
     (if (empty? indices)
       (concat initial (falses (- numbits index)))
       (index-bit-array numbits (rest indices)
                        (concat initial
                                (falses (- (first indices) index))
                                [true])
                        (+ (first indices) 1))))
  )

(defn bloom-hash [numbits numhashes array word]
  (when (= 1 (.length word)) (println word))
  ;; without the doall it would try to run all the maps at once
  ;; when the last reduce happened, or something like that,
  ;; causing a stack overflow
  ;; see: http://stackoverflow.com/questions/5794831/lazy-sequence-min-max-finder-stackoverflow-problem
  (doall (map #(or %1 %2) array
        (index-bit-array numbits
                         (hash-indices word numbits numhashes))))
  )

(defn create-bloom
  ([] (with-open [rdr (reader "/usr/share/dict/words")]
        (create-bloom (line-seq rdr))))
  ([words] (reduce #(bloom-hash 1024 32 %1 %2) (bit-array 1024)
                   words)))

;; this was my first actual result. Strange
;;[true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true]