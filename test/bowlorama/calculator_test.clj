(ns bowlorama.calculator-test
  (:require [clojure.test :refer :all]
            [bowlorama.calculator :as bcalc]))

(deftest is-strike
  (testing "should identify a strike if the first roll in a frame was 10"
    (is (true? (bcalc/is-strike [10, 5, 3])))
    (is (= 2 2))))

(deftest is-spare
  (testing "should identify a spare from two rolls adding up to 10, if the first roll was not a 10"
    (is (false? (bcalc/is-spare [10])))
    (is (false? (bcalc/is-spare [10,0])))
    (is (true? (bcalc/is-spare [1,9])))
    (is (true? (bcalc/is-spare [0,10])))))

(deftest rolls-for-frame
  (testing "should only score two rolls if a frame is less than ten"
    (is (= (bcalc/rolls-for-frame [4,5,6,7]) [4,5])))
  (testing "should add the next roll to the score after a spare"
    (is (= (bcalc/rolls-for-frame [1,9,6,7]) [1,9,6])))
  (testing "should add the next two rolls to the score after a strike"
    (is (= (bcalc/rolls-for-frame [10,9,6,7]) [10,9,6]))))

(deftest remaining-rolls
  (testing "should remove the first roll if the frame was a strike"
    (is (= (bcalc/remaining-rolls [10,9,6,7]) [9,6,7])))
  (testing "should remove the first two rolls if the frame was a spare"
    (is (= (bcalc/remaining-rolls [1,9,6,2]) [6,2])))
  (testing "should remove the first two rolls if the frame was less than 10"
    (is (= (bcalc/remaining-rolls [1,5,6,2]) [6,2]))))

(deftest to-frames
  (testing "should correctly produce frame totals for <10 frames"
    (is (= (bcalc/to-frames [3])           [3]))
    (is (= (bcalc/to-frames [1,2,3,4,5])   [3,7,5]))
    (is (= (bcalc/to-frames [1,2,3,4,5,3]) [3,7,8])))
  (testing "should correctly product frame totals that include spares"
    (is (= (bcalc/to-frames [3,7])       [10]))
    (is (= (bcalc/to-frames [3,7,6])     [16,6]))
    (is (= (bcalc/to-frames [3,7,6,4,2]) [16,12,2])))
  (testing "should correctly product frame totals that include strikes"
    (is (= (bcalc/to-frames [10])      [10]))
    (is (= (bcalc/to-frames [10,3])    [13,3]))
    (is (= (bcalc/to-frames [10,10,2]) [22,12,2]))))

(deftest score
  (testing "The bowling scorer"
    (testing "should add and extra roll if the tenth frame was a spare"
      (is (= (bcalc/score (concat (repeat 18 1) [4,6])) 28))
      (is (= (bcalc/score (concat (repeat 18 1) [4,6,1])) 29)))
    (testing "should add two extra rolls if the tenth frame was a strike"
      (is (= (bcalc/score (concat (repeat 18 1) [10])) 28))
      (is (= (bcalc/score (concat (repeat 18 1) [10,1])) 29))
      (is (= (bcalc/score (concat (repeat 18 1) [10,1,1])) 30)))
    (testing "should end the scoring after 10 frames"
      (is (= (bcalc/score (repeat 24 1)) 20))
      (is (= (bcalc/score (concat (repeat 18 1) [4,6,1,1])) 29))
      (is (= (bcalc/score (concat (repeat 18 1) [10,1,1,1])) 30))))
  (testing "The final score"
    (testing "should be zero if only gutter balls are rolled"
      (is (= (bcalc/score [0, 0, 0, 0])) 0))
    (testing "should be 20 if all rolls knocked down a single pin"
      (is (= (bcalc/score (repeat 20 1)) 20)))
    (testing "should be 150 if all rolls knocked down 5 pins (including 1 bonus roll for ending on a spare)"
      (is (= (bcalc/score (repeat 21 5)) 150)))
    (testing "should be 300 when bowling all strikes (including 2 bonus rolls for ending with a strike)"
      (is (= (bcalc/score (repeat 22 10)) 300)))))