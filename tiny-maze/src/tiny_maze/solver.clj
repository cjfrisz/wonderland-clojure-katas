(ns tiny-maze.solver)

(defn exit? [maze [y x]]
  (and (= x (dec (count (first maze))))
       (= y (dec (count maze)))))

(defn next-moves [maze [y x]]
  (for [[next-y next-x] [[y (dec x)] [(inc y) x] [y (inc x)] [(dec y) x]]
        :when (and (<= 0 next-x (dec (count (first maze))))
                   (<= 0 next-y (dec (count maze)))
                   (some #{(get-in maze [next-y next-x])} [0 :E]))]
       [next-y next-x]))

(defn solve-maze [maze]
  (loop [coords [0 0]
         maze (assoc-in maze [0 0] :x)
         alt-move* nil
         prev-maze nil]
        (if (exit? maze coords)
            maze
            (let [move* (next-moves maze coords)]
              (if (nil? (seq move*))
                  (let [alt-move (first alt-move*)]
                    (recur alt-move
                           (assoc-in prev-maze alt-move :x)
                           (next alt-move*)
                           nil))
                  (let [move (first move*)]
                    (recur move
                           (assoc-in maze move :x)
                           (next move*)
                           maze)))))))
