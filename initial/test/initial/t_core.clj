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
             (socialist-plus 10) => 11)
       (fact "it does nothing with two elements. Except add."
             (socialist-plus 10 20) => 30)
       (fact "it taxes the result if there are more than 2 elements."
             (socialist-plus 10 20 30) => 59
             (socialist-plus 10 20 20 10) => 58
             ))

(facts "about capitalist plus"
       (fact "it taxes fewer than two elements"
             (capitalist-plus 10) => 9
             )
       (fact "it does nothing with two elements. Except add."
            (capitalist-plus 10 20) => 30 )
       (fact "it subsidizes the result if there are more than 2 elements."
             (capitalist-plus 10 20 30) => 61
             (capitalist-plus 10 20 20 10) => 62
             ))

(facts "about communist plus"
       (fact "it only returns 10."
             (communist-plus 53) => 10
        ))

(facts "about political extreemist plus"
       (fact "it multiplies instead of adds."
             (political-extreemist-plus 1 2 3 4) => 24
        ))


(facts "about this lab"
       (fact "the student never bothered to start it."
       true => true))
