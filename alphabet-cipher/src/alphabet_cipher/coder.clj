(ns alphabet-cipher.coder)

(defn encode [keyword message]
  (letfn [(encode-letter [c1 c2]
            (let [off (int \a)]
              (char (+ (mod (apply + (mapv #(- % off) (mapv int [c1 c2]))) 26) off))))]
    (loop [kidx 0
           midx 0
           encrypted ""]
      (if (= midx (count message))
          encrypted
          (recur (mod (inc kidx) (count keyword))
            (inc midx)
            (str encrypted 
              (encode-letter (get keyword kidx)
                (get message midx))))))))

(defn decode [keyword message]
  (letfn [(decode-letter [k m]
            (let [kint (- (int k) (int \a))
                  mint (- (int m) (int \a))]
              (char (+ (mod (- mint kint) 26) (int \a)))))]
    (loop [kidx 0
           midx 0
           decrypted ""]
      (if (= midx (count message))
          decrypted
          (recur (mod (inc kidx) (count keyword))
            (inc midx)
            (str decrypted
              (decode-letter (get keyword kidx)
                (get message midx))))))))
