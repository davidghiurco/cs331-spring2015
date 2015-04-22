(ns heap-lab.t-core
  (:use midje.sweet)
  (:use [heap-lab.core])
  (:import [heal_lab.core Heap] ))

(def h (Heap. 7 [1 9 2 13 10 5 3]))
(facts "about numbers"
       (fact "one plus one is two."
             (+ 1 1)  =>  2)
       (fact "two plus one is three."
             (+ 2 1)  =>  3))
(facts "about add"
       (fact "it properly increments the size"
             (:size (add h 15)) => 8))
(facts "about delete"
       (fact "it decrements the size"
             (:size (delete h)) => 6))
