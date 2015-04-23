(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= (play-round [:spades :ace] [:club 10])
           [:spades :ace]))
    (is (= (play-round [:diamond 5] [:heart 9])
           [:heart 9])))
  (testing "queens are higher rank than jacks"
    (is (= (play-round [:heart :queen] [:club :jack])
           [:heart :queen]))
    (is (= (play-round [:club :jack] [:heart :queen])
           [:heart :queen])))
  (testing "kings are higher rank than queens"
    (is (= (play-round [:heart :queen] [:club :king])
           [:club :king]))
    (is (= (play-round [:club :king] [:heart :queen])
           [:club :king])))
  (testing "aces are higher rank than kings"
    (is (= (play-round [:heart :ace] [:club :king])
           [:heart :ace]))
    (is (= (play-round [:club :king] [:heart :ace])
           [:heart :ace])))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= (play-round [:club :jack] [:spade :jack])
           [:club :jack]))
    (is (= (play-round [:spade :jack] [:club :jack])
           [:club :jack])))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= (play-round [:club :jack] [:diamond :jack])
           [:diamond :jack]))
    (is (= (play-round [:diamond :jack] [:club :jack])
           [:diamond :jack])))
  (testing "if the ranks are equal, hearts beat diamonds")
    (is (= (play-round [:heart :jack] [:diamond :jack])
           [:heart :jack]))
    (is (= (play-round [:heart :jack] [:club :jack])
           [:heart :jack])))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (let [player1-cards (subvec (vec cards) 0 (/ (count (vec cards)) 2))
          ;; player 2 gets all the good cards
          player2-cards (subvec (vec cards) (/ (count (vec cards)) 2))
          [player1-cards player2-cards] (play-game player1-cards player2-cards)]
      (is (nil? (seq player1-cards)))
      (is (= (count player2-cards) (count cards))))
    (let [player1-cards (subvec (vec cards) (/ (count (vec cards)) 2))
          player2-cards (subvec (vec cards) 0 (/ (count (vec cards)) 2))
          [player1-cards player2-cards] (play-game player1-cards player2-cards)]
      (is (nil? (seq player2-cards)))
      (is (= (count player1-cards) (count cards))))))

