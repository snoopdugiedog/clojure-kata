(ns kata.core)

;; I tried to figure out how to just make it convert 
;; x/2 into the floor as an integer, but couldn't
;; find a reasonable method, so just doing it manually
(defn halve [c]
  (if (= 0 (mod c 2))
    (/ c 2)
    (/ (- c 1) 2))
  )

(defn mid [array]
  (get array (halve (count array))))

;; I tried to use drop-last and nthnext, but that was
;; foolish I should have read more of the docs, to
;; realize that subvec existed
(defn before-mid [array]
  (subvec array 0 (halve (count array))
          ))

(defn after-mid [array]
  (subvec array (+ 1 (halve (count array))))
  )

(defn chop-recurse [i array]
  (if (or (nil? array) (empty? array))
    -1
    (if (= i (mid array))
      (halve (count array))
      (if (< i (mid array))
        (chop i (before-mid array))
        (let  [r (chop i (after-mid array))]
          (if (= r -1)
            -1
            (+ r 1 (halve (count array)))) ; 1 for the mid element
          )
        )
      )
    )
  )

(defn chop [i array]
  (chop-recurse i array))