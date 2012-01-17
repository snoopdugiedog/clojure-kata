(ns kata.kata5
  (:use [clojure.java.io :only (reader)])
  (:use [clj-time.core]))

(defn bit-array [numbits]
  (repeat numbits false))

(defn add-byte [int byte]
  (bit-or (bit-shift-left int 8) byte))

(defn byte-array-to-int [array]
  (reduce add-byte 0 array))

(defn md5 [text]
  (.digest (java.security.MessageDigest/getInstance "md5")
           (.getBytes text)))

(defn hash-16 [text]
  (map byte-array-to-int (partition 4 (md5 text)))
  )

(defn index-hash [text numbits]
  (map #(mod % numbits) (hash-16 text)))

;; TODO: get more than 16 hashes
(defn hash-indices [word numbits numhashes]
  (sort (index-hash word numbits)))

(defn falses [numbits]
  (repeat numbits false))

;; indices must be sorted and all less than numbits
(defn index-bit-array
  ([numbits indices] (index-bit-array numbits indices [] 0))
  ([numbits indices initial index]
     (if (empty? indices)
       (concat initial (falses (- numbits index)))
       (recur numbits (rest indices)
                        (concat initial
                                (falses (- (first indices) index))
                                [true])
                        (+ (first indices) 1))))
  )

(defn bloom-hash [numbits numhashes array word]
  (when (= 1 (.length word))
    (println word)
    (println (now)))
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
  ([words] (reduce #(bloom-hash (Math/pow 2 12) 32 %1 %2)
                   (bit-array (Math/pow 12 12))
                   words)))

;; this was my first actual result. Strange
;;[true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false false true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true true]