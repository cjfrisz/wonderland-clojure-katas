(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

#_(def words ["book" "look"])

(defn link?
  [word mlink]
  (loop [word word
         mlink mlink
         diffs 0]
    (if (and (nil? (seq word))
             (nil? (seq mlink)))
        (= diffs 1)
        (recur (next word)
               (next mlink)
               ((if (= (first word) (first mlink))
                    identity
                    inc)
                diffs)))))

(defn find-link
  [word dic]
  (if (nil? (seq dic))
      nil
      (let [d (first dic)]
        (if (link? word d)
            d
            (recur word (next dic))))))

(defn doublets [word1 word2]
  (loop [word1 word1
         link* [word1]
         word* words]
    (let [word* (vec (remove (partial = word1) word*))]
      (if (= word1 word2)
          link*
          (let [link (find-link word1 word*)]
            (if (nil? link)
                []
                (recur link (conj link* link) word*)))))))
