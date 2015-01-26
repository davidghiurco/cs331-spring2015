(ns initial.t-core
  (:use midje.sweet)
  (:use [initial.core]))

(facts "about plus"
  (fact "it adds numbers."
        (plus)   => 0
        (plus 10)  => 10
        (plus 10 20) => 30
        (plus 10 20 30 40 50) => 150))

(facts "about socialist plus"
       (fact "it subsidized fewer than two elements" 
        )
       (fact "it does nothing with two elements. Except add."
)
       (fact "it taxes the result if there are more than 2 elements."
             ))

(facts "about capitalist plus"
       (fact "it taxes fewer than two elements"
             )
       (fact "it does nothing with two elements. Except add."
             )
       (fact "it subsidizes the result if there are more than 2 elements."
             ))

(facts "about communist plus"
  (fact "it only returns 10."
        ))

(facts "about political extreemist plus"
  (fact "it multiplies instead of adds."
        ))


(facts "about this lab"
       (fact "the student never bothered to start it."
       true => false))
