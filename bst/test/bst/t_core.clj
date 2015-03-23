(ns bst.t-core
  (:use midje.sweet)
  (:use [bst.core])
  (:import [bst.core BST] ))

(facts "about this lab"
  (fact "the student never started it."
        (+ 1 2)  => 12))
