(ns bst.core)

;; # Introduction
;;
;; In this lab you get to write a BST like the one we did in class, only
;; this time it is a dictionary structure and not a set.
;; As such, the "data" element from before will have a key and value instead.

(defrecord BST [root size])
(defrecord BNode [left key value right])

(defn make-node
  ([key value]  (make-node nil key value nil))
  ([left key value right] (BNode. left key value right))
  )

(defn make-tree []
  (BST. nil 0))

;; # Size
;;
;; A warmup function.

(defn size "Return the size of the tree."
  [t] (:size t))

;; # Add
;;
;; The nodes will be entered into the tree on the basis of their key.
;; If someone tries to add a key that is already there, we replace the value
;; with the new entry.

(defn add-h "Helper function"
  [node nu-key nu-val]
  (cond (nil? node) (make-node nu-key nu-val)
        (pos? (compare [nu-key] [(:key node)])) (make-node (add-h (:left node) nu-key nu-val) (:key node)  (:value node) (:right node))
        (neg? (compare [nu-key] [(:key node)])) (make-node (:left node) (:key node) (:value node)  (add-h (:right node) nu-key nu-val)) 
        :else (make-node  (:left node) (:key node)  nu-val (:right node))))

(defn preorder [bst]
  (when-not (nil? bst)
    (cons (:key bst)
          (concat (preorder (:left bst ))
                  (preorder (:right bst))))))

(defn add "Add a key and value to the BST"
  [bst nu-key nu-val]
  (let [nu-tree (add-h (:root bst) nu-key nu-val)]
    (cond (neg? (compare [(count (preorder (:root bst)))] [(count (preorder nu-tree))]))
          (BST. nu-tree (inc (:size bst)))
          :else (BST. nu-tree (:size bst)))))
    




;; # Find
;; We need two versions of find.  The first one takes a key and returns the
;; value.  The second takes a value and returns the key.  Note that the second
;; version of the function must search the entire tree!  If the search item is not
;; there, return nil.

(defn find-h
  [node look-key]
  (cond (nil? node) nil
        (zero? (compare look-key (:key node))) (:value node)
        :else (let [left-value (find-h (:left node) look-key)]
                (cond (nil? left-value) (find-h (:right node) look-key)
                      :else left-value))))
(defn find "Look for a key and return the corresponding value."
  [bst look-key]
  (find-h (:root bst) look-key))




(defn find-key-h
   [node look-value]
   (cond (nil?  node) nil
         (zero? (compare look-value (:value node))) (:key node)
         :else (let [left-value (find-key-h (:left node) look-value)]
                     (cond (nil? left-value) (find-key-h (:right node) look-value) 
                           :else left-value))))
(defn find-key "Look for a value and return the corresponding key."
  [bst look-value]
  (find-key-h (:root bst) look-value))
        





;; # Delete
;;
;; Similiarly, we have two versions of delete.  Please use the predecessor node if
;; you need to delete a child with two elements.

(defn get-succ [node]
  (loop [x (:right node)]
    (if (nil? (:left x)) x
        (recur (:left x)))))

(defn delete-helper [node victim]
  (cond (nil? node) nil
        (neg? (compare victim (:key node))) (assoc node :left (delete-helper (:left node) victim))
        (pos? (compare victim (:key node))) (assoc node :right (delete-helper (:right node) victim))
        :else (cond (and (nil? (:left node)) (nil? (:right node))) nil
                    (nil? (:right node)) (:left node)
                    (nil? (:left node)) (:right node)
                    :two-else (let [succ (get-succ node)]
                                (make-node (:left node) (:key succ) (:value succ)
                                           (delete-helper (:right node) (:key succ)))))))

(defn delete [bst victim]
  (cond (empty? (:root bst)) bst
        :else
  (let [t (delete-helper (:root bst) victim)]
    (BST. t (size t)))))

(defn delete-value [bst victim]
  (cond (empty? (:rooot bst)) bst
        :else
  (let [search (find-key bst victim)]
    (if (nil? search) nil
                (delete bst search)))))





;; # Map Tree
;;
;; This function takes a tree t and maps a function f over it.
;; If your tree is ((x 3 x) 5 ((x 7 x) 6 x)), then (map-tree t inc)
;; will return ((x 4 x) 6 ((x 8 x) 7 x))

(defn map-tree-h
  [t f] (when-not (nil? t)
          (BNode. (map-tree-h (:left t) f) (f (:key t)) (:value t) (map-tree-h (:right t) f))
          ))
(defn map-tree [bst f]
  (BST. (map-tree-h (:root bst) f) (size bst)))


