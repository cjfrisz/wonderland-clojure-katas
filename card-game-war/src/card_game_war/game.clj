(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round [player1-card player2-card]
  (let [[suit1 rank1] player1-card
        [suit2 rank2] player2-card]
    (if (= rank1 rank2)
        (let [suit1-idx (.indexOf suits suit1)
              suit2-idx (.indexOf suits suit2)]
          (if (> suit1-idx suit2-idx)
              player1-card
              player2-card))
        (let [rank1-idx (.indexOf ranks rank1)
              rank2-idx (.indexOf ranks rank2)]
          (if (> rank1-idx rank2-idx) 
            player1-card
            player2-card)))))

(defn play-game [player1-cards player2-cards]
  (if (or (nil? (seq player1-cards))
          (nil? (seq player2-cards)))
      [player1-cards player2-cards]
      (let [[p1-card player1-cards] ((juxt first (comp vec rest)) player1-cards) 
            [p2-card player2-cards] ((juxt first (comp vec rest)) player2-cards) 
            winner (play-round p1-card p2-card)]
        (if (= winner p1-card)
            (recur (conj player1-cards p1-card p2-card) player2-cards)
            (recur player1-cards (conj player2-cards p1-card p2-card))))))
