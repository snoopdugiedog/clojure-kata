(ns kata.kata6
  (:require [clojure.string :as str]))

(defn word-key [string]
  (str/join (sort (str/lower-case string))))

(defn add-to-map [initial word]
  (let [sorted (word-key word)]
    (assoc initial sorted (cons word (get initial sorted [])))))

