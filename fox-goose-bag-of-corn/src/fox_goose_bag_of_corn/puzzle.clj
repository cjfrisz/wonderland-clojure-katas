(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn left-shore
  [pos & item*]
  (let [[left river right] (mapv (fn [p] 
                                   (filterv #(not ((set item*) %)) 
                                     p)) 
                             (peek pos))]
    (conj pos [(apply conj left item*) river right])))

(defn boat
  [pos & item*]
  (let [[left river right] (mapv (fn [p] 
                                   (filterv #(not ((set item*) %)) 
                                     p)) 
                             (peek pos))]
    (conj pos [left (apply conj river item*) right])))

(defn right-shore
  [pos & item*]
  (let [[left river right] (mapv (fn [p] 
                                   (filterv #(not ((set item*) %)) 
                                     p)) 
                             (peek pos))]
    (conj pos [left river (apply conj right item*)])))

(defn river-crossing-plan []
  ;;; [[:fox :goose :corn :you] [:boat] []]
  (-> start-pos
  ;;; [[:fox :corn] [:boat :goose :you] []]
      (boat :goose :you)
  ;;; [[:fox :corn] [:boat] [:goose :you]]
      (right-shore :goose :you)
  ;;; [[:fox :corn] [:boat :you] [:goose]]
      (boat :you)
  ;;; [[:fox :corn :you] [:boat] [:goose]]
      (left-shore :you)
  ;;; [[:fox] [:boat :corn :you] [:goose]]
      (boat :you :corn)
  ;;; [[:fox] [:boat] [:goose :corn :you]]
      (right-shore :you :corn)
  ;;; [[:fox] [:boat :goose :you] [:corn]]
      (boat :goose :you)
  ;;; [[:fox :goose :you] [:boat] [:corn]]
      (left-shore :you :goose)
  ;;; [[:goose] [:boat :fox :you] [:corn]]
      (boat :you :fox)
  ;;; [[:goose] [:boat] [:corn :fox :you]]
      (right-shore :you :fox)
  ;;; [[:goose] [:boat :you] [:corn :fox]]
      (boat :you)
  ;;; [[:goose :you] [:boat] [:corn :fox]]
      (left-shore :you)
  ;;; [[] [:boat :goose :you] [:corn :fox]]
      (boat :goose :you)
  ;;; [[] [:boat] [:corn :fox :goose :you]]
      (right-shore :you :goose)))
