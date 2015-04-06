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
  [bst look-key]
  (cond (nil?  bst) nil
        (zero? (compare (:key bst) look-key)) (:value bst)
        (pos? (compare (:key bst) look-key)) (find-h (:left bst) look-key)
        :else (find-h (:right bst) look-key)))

(defn find "Look for a key and return the corresponding value."
  [bst look-key]
  (find-h (:root bst) look-key))


(defn find-key-h 
  [bst look-value]
  (cond (nil? bst) nil
        (zero? (compare [(:value bst)] [look-value])) (:key bst)
        :else (let [left-value (find-key-h (:left bst) look-value)]
                (cond (nil? left-value) (find-key-h (:right bst) look-value)
                    :else left-value))))
(defn find-key "Look for a value and return the corresponding key."
  [bst look-value]
  (find-key-h (:root bst) look-value))

;; # Delete
;;
;; Similiarly, we have two versions of delete.  Please use the predecessor node if
;; you need to delete a child with two elements.

(defn delete [bst victim]
  nil
  )

(defn delete-value [bst victim]
  nil
  )

;; # Map Tree
;;
;; This function takes a tree t and maps a function f over it.
;; If your tree is ((x 3 x) 5 ((x 7 x) 6 x)), then (map-tree t inc)
;; will return ((x 4 x) 6 ((x 8 x) 7 x))

(defn map-tree
  [t f] nil)
