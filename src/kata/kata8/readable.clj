(ns kata.kata8.readable
  (:require [clojure.string :as str])
  (:use [clojure.java.io :only (reader)]))

(defn str-before [string index]
  (.substring string 0 index))
(defn str-after [string index]
  (.substring string index))

(defn is-concatenated-at [words-by-size word index]
  (and ((nth words-by-size index) (str-before word index))
       ((nth words-by-size (- 6 index)) (str-after word index)))
  )

(defn is-concatenated [words-by-size word]
  (or
   (is-concatenated-at words-by-size word 1)
   (is-concatenated-at words-by-size word 2)
   (is-concatenated-at words-by-size word 3)
   (is-concatenated-at words-by-size word 4)
   (is-concatenated-at words-by-size word 5)
   )
  )

(defn empty-words-by-size []
  [#{} #{} #{} #{} #{} #{} #{}])

(defn words-by-size [existing-list word]
  (let [size (count "word")]
    (assoc existing-list size
           (conj (get existing-list size (sorted-set)) word)))
  )

(defn six-letter-word-joins [words]
  (let [word-set (reduce words-by-size (empty-words-by-size)
                         (filter #(<= (count %) 6) words))]
    (filter
     #(is-concatenated word-set %)
     (get word-set 6)))
  )

(defn six-letter-word-joins-from-file []
  (with-open [rdr (reader "/usr/share/dict/words")]
    (six-letter-word-joins (line-seq rdr)))
  )

(defn -main [] (time (println (six-letter-word-joins-from-file))))