(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn row-sums-equal?
  [square]
  (apply = (map (partial reduce +) square)))

(defn column-sums-equal?
  [square]
  (= (reduce + (map first square))
     (reduce + (map second square))
     (reduce + (map last square))))

(defn diagonal-sums-equal?
  [square]
  (= (+ (get-in square [0 0]) 
        (get-in square [1 1])
        (get-in square [2 2]))
     (+ (get-in square [2 0]) 
        (get-in square [1 1])
        (get-in square [0 2]))))

(defn magic-square?
  [square]
  (and (row-sums-equal? square)
       (column-sums-equal? square)
       (diagonal-sums-equal? square)))

;; random!
(defn magic-square [values]
  (letfn [(random-square [values]
            (vec (map vec (partition 3 (shuffle values)))))]
    (loop [square (random-square values)]
      (if (magic-square? square)
          square
          (recur (random-square values))))))
