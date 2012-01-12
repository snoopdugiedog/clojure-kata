(ns kata.kata5
  (:use [clojure.java.io :only (reader)]))

(defn bloom-hash [a b]
  a)


(defn create-bloom []
  (with-open [rdr (reader "/usr/share/dict/words")]
   (reduce bloom-hash (line-seq rdr))))