(ns kata.kata6
  (:require [clojure.string :as str])
  (:use [clojure.java.io :only (reader)]))

(defn word-key [string]
  (str/join (sort (str/lower-case string))))

(defn add-to-map [initial word]
  (let [sorted (word-key word)]
    (assoc initial sorted (cons word (get initial sorted '())))))

(defn all-anagrams [words]
  (reduce add-to-map (sorted-map) words))

(defn append-anagrams [in anagrams]
  (if (> (count anagrams) 1)
    (cons anagrams in)
    in
    )
  )

(defn create-anagrams [words]
  (reduce append-anagrams [] (vals (all-anagrams words))))

(defn pretty-print [anagrams]
  (doseq [anagram-set anagrams]
    (println (str/join " " anagram-set ))))

(defn longer [a b]
  (if (> (count (first a)) (count (first b)))
    a
    b))

(defn longest [anagrams]
  (reduce longer [""] anagrams))

(defn more-words [a b]
  (if (> (count a) (count b))
    a
    b))

(defn most-words [anagrams]
  (reduce more-words [] anagrams))

(defn do-it [rdr]
  (let [anagrams (create-anagrams (line-seq rdr))]
    (pretty-print anagrams)
    (println "Longest:" (longest anagrams))
    (println "Most Words:" (most-words anagrams))
    ))

(defn anagrams-in-dict []
  (with-open [rdr (reader "/usr/share/dict/words")]
    (do-it rdr)
    ))

(defn -main [] (time (anagrams-in-dict)))