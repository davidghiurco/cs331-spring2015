(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque] ))

(facts "about make-deque"
       (fact "it creates an empty deque"
            (:size (make-deque)) => 0))
(facts "about deque-size"
       (fact "it returns the size of a deque"
             (let [dq (Deque. '(1 2 3) '(6 5 4) 6)]
                 (deque-size dq) => (:size dq))))
(facts "about push-front"
       (fact "it adds an element to the front of the deque"
             (let [dq (Deque. '(1 2 3) '(6 5 4) 6)]
                  (push-front dq 7) => (Deque. '(7 1 2 3) '(6 5 4) 7))))
(facts "about pop-front"
       (fact "it pops/dequeues an element from the front of the deque"
             (let [dq (Deque. '() '(2 4 9 24 6 17) 6)]
               (pop-front dq) => (Deque. '(6 24 9 4 2) '() 5)))
       (fact "it does not allow size to get negative"
             (let [dq (Deque. '() '() 0)]
               (:size (pop-front dq)) => 0)))
(facts "about push-back"
       (fact "it adds an element to the back of the deque"
             (let [dq (Deque. '() '() 0)]
               (push-back dq 23) => (Deque. '() '(23) 1))))
(facts "about pop-back"
       (fact "pops/dequeues an element from the back of the deque"
             (let [dq (Deque. '(1 2 3 4 5 6 7) '() 7)]
               (pop-back dq) => (Deque. '() '(6 5 4 3 2 1) 6)))
       (fact "it does not allow size to get negative"
             (let [dq (Deque. '() '() 0)]
               (:size (pop-back dq)) => 0)))
(facts "about flip-front"
       (fact "it flips the back list to the front list, if necessary"
             (let [dq (Deque. '() '(6 5 4 3 2 1) 6)]
               (flip-front dq) => (Deque. '(1 2 3 4 5 6) '() 6)))
       (fact "it does not flip when the front list is not empty"
             (let [dq (Deque. '(1 2) '(6 5 4 3) 6)]
               (flip-front dq) => dq)))
(facts "about flip-back"
       (fact "it flips the front list to the back list, if necessary"
             (let [dq (Deque. '(1 2 3 4 5 6) '() 6)]
               (flip-back dq) => (Deque. '() '(6 5 4 3 2 1) 6))))
(facts "about front"
       (fact "it retuns the front element of the deque. May cause a flip"
             (let [dq (Deque. '(1 2 3) '(6 5 4) 6)]
               (front dq) => 1)))
(facts "about back"
       (fact "it returns the back element of the deque. May cause a flip"
             (let [dq (Deque. '(1 2 3 4 5 6) '() 6)]
               (back dq) => 6)))
