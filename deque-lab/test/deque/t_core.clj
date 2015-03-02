(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque] ))

(facts "about this lab"
  (fact "the student never started it."
        (+ 1 2)  => 12))
