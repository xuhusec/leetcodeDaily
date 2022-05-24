### [2251.Number-of-Flowers-in-Full-Bloom](https://leetcode.com/problems/count-positions-on-street-with-required-brightness/)

You are given an integer n. A perfectly straight street is represented by a number line ranging from `0` to `n - 1`. You are given a 2D integer array `lights` representing the street lamp(s) on the street. Each lights[i] = [position<sub>i</sub>, range<sub>i</sub>] indicates that there is a street lamp at position positioni that lights up the area from [max(0, position<sub>i</sub> - range<sub>i</sub>), min(n - 1, position<sub>i</sub> + range<sub>i</sub>)] (**inclusive**).

The **brightness** of a position p is defined as the number of street lamps that light up the position p. You are given a **0-indexed** integer array `requirement` of size `n` where `requirement[i]` is the minimum **brightness** of the i<sup>th</sup> position on the street.

Return the number of positions i on the street between 0 and n - 1 that have a **brightness** of **at least** `requirement[i]`.

Constraints:

1 <= n <= 10<sup>5</sup>
1 <= lights.length <= 10<sup>5</sup>
0 <= position<sub>i</sub> < n
0 <= range<sub>i</sub> <= 10<sup>5</sup>
requirement.length == n
0 <= requirement[i] <= 10<sup>5</sup>

#### Solution 1: Line Sweep

A brute force solution would add all the places lighted by a lamp with 1. Then go over each place to check if the requiremenbt is satisfied. This would give O(mr) where m is number of lamps and r is the average of range. It would exceed the time limit.

To reduce the time complexity, we can use diff array or line sweep. We can add the start of the lighted range with + 1 and add the end + 1 with -1. Please note the lamp can cover the end so we need to add -1 to end + 1 instead of end. This would only take O(1) for each lamp or O(,) in total. And for each requirement, we can just gover the array and check if the presum >= requirement[i]. If yes, we add + 1 to the answer. Otherwise, no impact in the answer. This costs O(n). So the total time complexity is O(m + n) where n is the length of the road. Or O(n) as n >= m.

In the statement of the problem, it tells how to deal with the boundaries. We can just follow the statement. But we need diff array with one additional space as we need to put end + 1. So diff = new int[n + 1];