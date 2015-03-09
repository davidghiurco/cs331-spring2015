(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque] ))

(facts "about make-deque"
       (fact "it creates an empty deque"
            (:size (make-deque)) => 0
(facts "about deque-size"
       (fact "it returns the size of a deque"
             (let [(Deque. '(1 2 3) '(6 5 4) 6) dq]
                 (deque-size dq) => (:size dq))))
(facts "about push-front"
       (fact "it adds an element to the front of the deque"
             (let [(Deque. '(1 2 3) '(6 5 4) 6) dq]
                  (:front (push-front dq 7)) => '(7 1 2 3))))
