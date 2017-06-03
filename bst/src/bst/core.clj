(ns bst.core)

;; Score: 100%

;; # Introduction
;;
;; In this lab you get to write a BST like the one we did in class, only
;; this time it is a dictionary structure and not a set.
;; As such, the "data" element from before will have a key and value instead.

(defrecord BST [root size])
(defrecord BNode [left key value right])

(defn make-node
  ([key value]  (make-node nil key value nil))
  ([left key value right] (BNode. left key value right)))

(defn make-tree []
  (BST. nil 0))

;; # Size
;;
;; A warmup function.

(defn preorder [bst]
  (when-not (nil? bst)
    (cons (:key bst)
          (concat (preorder (:left bst))
                  (preorder (:right bst))))))

(defn size "Return the size of the tree."
  [t]
  (:size t))

;; # Add
;;
;; The nodes will be entered into the tree on the basis of their key.
;; If someone tries to add a key that is already there, we replace the value
;; with the new entry.
(defn add-helper
  [node nu-key nu-val]
  (cond (nil? node) (make-node nu-key nu-val)
        (neg? (compare nu-key (:key node))) (make-node (add-helper (:left node) nu-key nu-val) (:key node) (:value node) (:right node))
        (pos? (compare nu-key (:key node))) (make-node (:left node) (:key node) (:value node) (add-helper (:right node) nu-key nu-val))
        :else (make-node (:left node) (:key node) nu-val (:right node))))

(defn add "Add a key and value to the BST."
  [bst nu-key nu-val]
  (let [nu-tree (add-helper (:root bst) nu-key nu-val)]
    (cond (neg? (compare [(count (preorder (:root bst)))] [(count (preorder nu-tree))]))
          (BST. nu-tree (inc (:size bst)))
          :else (BST. nu-tree (:size bst)))))

;; # Find
;;
;; We need two versions of find.  The first one takes a key and returns the
;; value.  The second takes a value and returns the key.  Note that the second
;; version of the function must search the entire tree!  If the search item is not
;; there, return nil.

(defn find-helper
  [node look-key]
  (cond (nil? node) nil
        (zero? (compare look-key (:key node))) (:value node)
        (neg? (compare look-key (:key node))) (find-helper (:left node) look-key)
        :else (find-helper (:right node) look-key)))

(defn find "Look for a key and return the corresponding value."
  [bst look-key]
  (find-helper (:root bst) look-key))

(defn find-key-helper
  [node look-value]
  (cond (nil? node) nil
        (zero? (compare look-value (:value node))) (:key node)
        :else (let [left-part (find-key-helper (:left node) look-value)]
                (cond (nil? left-part) (find-key-helper (:right node) look-value)
                      :else left-part))))

(defn find-key "Look for a value and return the corresponding key."
  [bst look-value]
  (find-key-helper (:root bst) look-value))

;; # Delete
;;
;; Similiarly, we have two versions of delete.  Please use the predecessor node if
;; you need to delete a child with two elements.

(defn go-right [t]
  (cond (nil? (:right t)) t
        :else (go-right (:right t))))

(defn get-pred [node]
  (loop [x (:left node)]
    (if (nil? (:right x)) x
        (go-right x))))

(defn delete-helper [node victim]
  (cond (nil? node) nil
        (neg? (compare victim (:key node))) (assoc node :left (delete-helper (:left node) victim))
        (pos? (compare victim (:key node))) (assoc node :right (delete-helper (:right node) victim))
        :else (cond (and (nil? (:left node)) (nil? (:right node))) nil
                    (nil? (:right node)) (:left node)
                    (nil? (:left node)) (:right node)
                    :two-else (let [pred (get-pred node)]
                                (make-node (delete-helper (:left node) (:key pred))
                                           (:key pred) (:value pred) (:right node))))))

(defn delete [bst victim]
  (let [t (delete-helper (:root bst) victim)]
    (BST. t (count (preorder t)))))

(defn delete-value [bst victim]
  (let [search (find-key bst victim)]
    (delete bst search)))

;; # Map Tree
;;
;; This function takes a tree t and maps a function f over it.
;; If your tree is ((x 3 x) 5 ((x 7 x) 6 x)), then (map-tree t inc)
;; will return ((x 4 x) 6 ((x 8 x) 7 x))


(defn map-tree-helper [t f]
  (when-not (nil? t)
    (BNode. (map-tree-helper (:left t) f) (:key t) (f (:value t)) (map-tree-helper (:right t) f))))

(defn map-tree
  [t f]
  (BST. (map-tree-helper (:root t) f) (count (preorder (:root t)))))
