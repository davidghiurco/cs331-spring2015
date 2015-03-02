(ns deque.core)

(defrecord Deque [front back size])

;; # Your Work

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
  "Adds an element to the back fo the deque."
  [dq elt]
  nil
 )

(defn flip-front
  "Flip the back list to the front list, if necessary."
  [dq]
  nil
)

(defn flip-back
  "Flip the front list to the back list, if necessary."
  [dq]
  nil
)

(defn front
  "Return the front element of the deque.  May cause a flip."
  [dq]
  nil
)

(defn back
  "Return the back element of the deque.  May cause a flip."
  [dq]
  nil
)

(defn pop-front
  "Pops/dequeues an element from the front of the deque."
  [dq]
  nil
)

(defn pop-back
  "Pops/dequeues an element from the back of the deque."
  [dq]
  nil
)
