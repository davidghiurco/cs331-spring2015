(ns bst.t-core
  (:use midje.sweet)
  (:use [bst.core])
  (:import [bst.core BST] ))



(defn preorder-t [bst] ;; used to check trees
  (when-not (nil? bst)
    (cons (:key bst)
          (concat (preorder-t (:left bst))
                  (preorder-t (:right bst))))))

(def LTree (BST. (make-node (make-node (make-node nil :a 8 (make-node :c 10)) :d 15 (make-node :e 16)) :f 17 (make-node (make-node :i 32) :m 56 (make-node (make-node :s 60) :w 65 (make-node :x 98)))) 10))

(facts "about this lab"
       (fact "the student started it"
             (+ 1 2) => 3))
(facts "about add"
       (fact "it correctly adds a key-value pair when the key is new"
             (let [tree (BST. (make-node nil 5 4 nil) 1)]
               (size (add tree 1 2)) => 2))
       (fact "it replaces the value of the corresponding key if nu-key is in the tree"
             (let [tree (BST. (make-node nil 5 4 nil) 1)]
               (size (add tree 5 7)) => 1)))
(facts "about find"
       (fact "it searches according to key and returns corresponding value if key exists"
             (let [tree (BST. (make-node (make-node nil 5 4 nil) 4 6 (make-node nil 9 7 nil)) 3)]
               (find tree 5) => 4
               (find tree 12) => nil)))
(facts "about find-key"
       (fact "it searches accoding to value and returns corresponding key if value exists"
             (let [tree (BST. (make-node (make-node nil 5 4 nil) 4 6 (make-node nil 9 7 nil)) 3)]
               (find-key tree 7) => 9
               (find-key tree 54) => nil)))
(facts "about map-tree"
       (fact "it applies a function to every key-value pair in the tree"
             (let [tree (BST. (make-node (make-node nil 5 4 nil) 4 6 (make-node nil 9 7 nil)) 3)]
               (map-tree tree inc) => (BST. (make-node (make-node nil 5 5 nil) 4 7 (make-node nil 9 8 nil)) 3))))             
(facts "about delete"
       (fact "it does nothing with empty trees"
             (let [tree (BST. nil 0)]
               (delete tree 5) => tree ))
       (fact "it deletes a node by searching for the key"
             (preorder-t (:root (delete LTree :f))) => '(:e :d :a :c :m :i :w :s :x)
             (size (delete (delete LTree :w) :f)) => 8))               
(fact "about delete-value"
      (fact "it does nothing with empty trees"
            (let [tree (BST. nil 0)]
              (delete-value tree 5) => tree))
      (fact "it deletes a node by searching for the value"
            (preorder-t (:root (delete-value LTree 32))) => '(:f :d :a :c :e :m :w :s :x)
            (size (delete-value LTree 56)) => 9))


