(ns kata.kata8.perf
  (:require [clojure.string :as str])
  (:use [clojure.java.io :only (reader)]))

(defn string-before [string index]
  (.substring string 0 index))
(defn string-after [string index]
  (.substring string index))

;; Checks if a given word is the combination of one word
;; of the given length and another word, both in words-by-size
(defn is-concatenated-at [words-by-size word index]
  (and ((nth words-by-size index) (string-before word index))
       ((nth words-by-size (- 6 index)) (string-after word index)))
  )

;; Checks if the given word is the combination of two words
;; in words-by-size
(defn is-concatenated [words-by-size word]
  (or
   (is-concatenated-at words-by-size word 1)
   (is-concatenated-at words-by-size word 2)
   (is-concatenated-at words-by-size word 3)
   (is-concatenated-at words-by-size word 4)
   (is-concatenated-at words-by-size word 5)
   )
  )

;; the empty words-by-size data structure
;; a vector of 6 empty sets
(defn empty-words-by-size []
  [#{} #{} #{} #{} #{} #{} #{}])

;; Adds the given word to the existing set of words by
;; size, useful for reduce calls
(defn add-to-words-by-size [existing-list word]
  (let [size (count word)]
    (assoc existing-list size
           (conj (get existing-list size (sorted-set)) word)))
  )

;; Creates a vector of sets, where each set is all the words
;; in the given list, with the length of the index
(defn words-by-size [words]
  (reduce add-to-words-by-size (empty-words-by-size)
          (filter #(<= (count %) 6) words)))

;; Returns a sequence of all words in the given list that
;; are the result of concatenating two other words in the list
(defn six-letter-word-joins [words]
  (let [word-set (words-by-size words)]
    (filter
     #(is-concatenated word-set %)
     (get word-set 6)))
  )

;; Reads in a list of words from a file and returns all the
;; 6-letter words that are the result of concatenating two
;; other words in the file
(defn six-letter-word-joins-from-file []
  (with-open [rdr (reader "/usr/share/dict/words")]
    (six-letter-word-joins (line-seq rdr)))
  )

(defn -main [] (time (println (six-letter-word-joins-from-file))))