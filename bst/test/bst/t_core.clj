;; Score: 100%
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
(facts "about make-tree"
       (fact "It creates a tree with two parts: root and size."
             (let [Atree (add (make-tree) :a 17)]
               (:key (:root Atree)) => :a
               (:size Atree) => 1))
       (fact "make-node makes a node with 4 parts: left, key, value, and right."
             (let [Anode (make-node :b 32)]
               (:key Anode) => :b
               (:value Anode) => 32
               (:left Anode) => nil)))
(facts "about size"
       (fact "returns size of root of BST"
                          (size LTree) => 10))
(facts "about add"
       (fact "it correctly adds a key-value pair when the key is new"
             (let [tree (BST. (make-node nil 5 4 nil) 1)]
               (size (add tree 1 2)) => 2)
             (preorder-t (:root (add LTree :k 40))) => '(:f :d :a :c :e :m :i :k :w :s :x)
             (size (add LTree :k 40)) => 11)
       (fact "it replaces the value of the corresponding key if nu-key is in the tree"
             (let [tree (BST. (make-node nil 5 4 nil) 1)]
               (size (add tree 5 7)) => 1)))
(facts "about find"
       (fact "it searches according to key and returns corresponding value if key exists"
             (find LTree :e) => 16))
(facts "about find-key"
       (fact "it searches accoding to value and returns corresponding key if value exists"
             (find-key LTree 56) => :m))
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


