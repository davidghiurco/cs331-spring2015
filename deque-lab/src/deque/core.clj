(ns deque.core)

(defrecord Deque [front back size])

;; David Ghiurco's deque lab

(defn make-deque
  "Create an empty deque."
  []
  (Deque. '() '() 0))

(defn deque-size
  "Return the size of a deque."
  [dq]
  (:size dq))

(defn push-front
  "Adds an element to the front of the deque."
  [dq elt]
  (let [{:keys [front back size]} dq]
    (Deque. (cons elt front) back (inc size))))

(defn push-back
  "Adds an element to the back of the deque."
  [dq elt]
  (let [{:keys [front back size]} dq]
    (Deque. front (cons elt back) (inc size)))
)

(defn flip-front
  "Flip the back list to the front list, if necessary."
  [dq]
  (let [{:keys [front back size]} dq]
    (cond (nil? front)  (Deque. (reverse back) nil size)
          :else dq))
)

(defn flip-back
  "Flip the front list to the back list, if necessary."
  [dq]
  (let [{:keys [front back size]} dq]
    (cond (nil? back)  (Deque. nil (reverse front) size)
          :else dq))
)

(defn front
  "Return the front element of the deque.  May cause a flip."
  [dq]
  (let [{:keys [front back size]} dq]
    (first (:front (flip-front dq))))  ;; flip-front checks if it should actually flip
)

(defn back
  "return the back element of the deque.  may cause a flip."
  [dq]
  (let [{:keys [front back size]} dq]
    (first (:back (flip-back dq)))) ;; flip-back checks if it should actually flip
)


(defn pop-front
  "pops/dequeues an element from the front of the deque."
  [dq]
  (let [{:keys [front back size]} dq]
    (cond (nil? front)  (Deque. (rest (:front (flip-front dq))) nil (dec size))
    :else (Deque. (rest front) back (dec size))))
)


(defn pop-back
  "pops/dequeues an element from the back of the deque."
  [dq]
  (let [{:keys [front back size]} dq]
    (cond (nil? back) (Deque. nil (rest (:back (flip-back dq))) (dec size))
    :else (Deque. front (rest back) (dec size))))
)
