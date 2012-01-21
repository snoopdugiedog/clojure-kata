(ns kata.kata6
  (:require [clojure.string :as str])
  (:use [clojure.java.io :only (reader)]))

(defn word-key [string]
  (str/join (sort (str/lower-case string))))

(defn add-to-map [initial word]
  (let [sorted (word-key word)]
    (assoc initial sorted (cons word (get initial sorted [])))))


(defn all-anagrams [words]
  (reduce add-to-map {} words))

(defn append-anagrams [in anagrams]
  (if (> (count anagrams) 1)
    (cons anagrams in)
    in
    )
  )
(defn create-anagrams [words]
  (reduce append-anagrams [] (vals (all-anagrams words))))

(defn anagrams-in-dict []
  (with-open [rdr (reader "/usr/share/dict/words")]
    (println (create-anagrams (line-seq rdr)))
    ))

(defn -main [] (time (anagrams-in-dict)))