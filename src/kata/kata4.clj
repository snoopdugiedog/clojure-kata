(ns kata.kata4
  (:use [clojure.java.io :only (reader)]))

(defn firstthree [text]
  (map (fn [s] (Integer/parseInt s))
       (take 3 (re-seq #"[0-9]+" text)))
  )

(defn spread [first]
  (if (< (count first) 3)
    nil
   (- (nth first 2) (nth first 1)))
  )

(defn spreadtext [text]
  (println (firstthree text))
  (spread (firstthree text)))

(defn temp []
  (with-open [rdr (reader "weather.dat")]
    (map (fn [l] (println l)) (line-seq rdr))


;;    (doseq [l (line-seq rdr)]
      ;;      (when (.contains l "MxT") (println l)) )
      )
    )