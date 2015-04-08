(ns bst.t-core
  (:use midje.sweet)
  (:use [bst.core])
  (:import [bst.core BST] ))

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
               (map-tree tree inc) => (BST. (make-node (make-node nil 6 5 nil) 5 7 (make-node nil 10 8 nil)) 3))))             
