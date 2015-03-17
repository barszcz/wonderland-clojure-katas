(ns alphabet-cipher.coder
  (:require [clojure.set :refer [map-invert]]))

(def offsets-map (zipmap "abcdefghijklmnopqrstuvwxyz" (range 26)))
(def letters-map (map-invert offsets-map))

(defn encode-letter [k m]
  (let [x (get offsets-map k)
        y (get offsets-map m)]
    (get letters-map (mod (+ x y) 26))))

(defn decode-letter [k m]
  (let [x (get offsets-map k)
        y (get offsets-map m)]
    (get letters-map (mod (- y x) 26))))


(defn code [keyword message func]
  (let [len (count message)
        key (take len (cycle keyword))]
    (->> message
         (map func key)
         (apply str))))

(defn encode [keyword message]
  (code keyword message encode-letter))

(defn decode [keyword message]
  (code keyword message decode-letter))
