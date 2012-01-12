(ns kata.kata4
  (:use [clojure.java.io :only (reader)]))

(defn firstthree [text]
  (map (fn [s] (rationalize (Double/parseDouble s)))
       (take 3 (re-seq #"[0-9]+(?:\.[0-9]+)?" text)))
  )

(defn spread [three]
  (if (< (count three) 3)
    nil
    [(first three) (-' (nth three 1) (nth three 2))])
  )

(defn spreadtext [text]
  (println (firstthree text))
  (spread (firstthree text)))

(defn lesser [a b]
  (cond
   (nil? b) a
   (nil? a) b
   (< (second a) (second b)) a
   true b))

(defn temp []
  (with-open [rdr (reader "weather.dat")]
    (reduce lesser (map spreadtext (line-seq rdr)))


;;    (doseq [l (line-seq rdr)]
      ;;      (when (.contains l "MxT") (println l)) )
      )
    )