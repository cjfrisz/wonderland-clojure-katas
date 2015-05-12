(ns wonderland-number.finder)

(defn hasAllTheSameDigits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(defn wonderland-number []
  (first (for [n (range 100000 999999)
               :when (and (hasAllTheSameDigits? n (* n 2))
                          (hasAllTheSameDigits? n (* n 3))
                          (hasAllTheSameDigits? n (* n 4))
                          (hasAllTheSameDigits? n (* n 5))
                          (hasAllTheSameDigits? n (* n 6)))] 
           n)))
