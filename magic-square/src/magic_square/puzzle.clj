(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

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

(defn magic-square
  [values]
  (let [set-average (/ (reduce + values) (count values))
        row* (for [x values
                   y values
                   z values
                   :when (and (not (or (= x y)
                                       (= y z)
                                       (= x z)))
                              (= (/ (+ x y z) 3)
                                 set-average))]
               [x y z])]
    (first (take 1 (for [row1 row*
                         row2 row*
                         row3 row*
                         :let [square [row1 row2 row3]]
                         :when (and (= (set (concat row1 row2 row3))
                                       (set values))
                                    (column-sums-equal? square)
                                    (diagonal-sums-equal? square))]
                        square)))))

