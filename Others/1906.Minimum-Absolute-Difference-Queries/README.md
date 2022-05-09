### [1906.Minimum-Absolute-Difference-Queries](https://leetcode.com/problems/minimum-absolute-difference-queries/)

#### Solution 1: PreSum

The question looks very difficult in the first glance. But in the constraint section, it said  1 <= nums[i] <= 100. This condition dramtically low the complexity. we know the range of all possible number is small. The minimium absolute difference would be produced by two closed numbers. Given the range is small, it would not be hard to figure out bucket sort can help this problem. The closest two buckets with positive count would tell the minimium absolute difference by the difference between their indices. Because the bucket index is increasing, so it is always the later - the previous. 

Then the question become how to quickly compute the bucket for a given range. Because the query range is consecutive. It reminds me presum. However, this presum is not for just a cnt but the cnt for all possible elements, i.e. from 1 to 100.

For each query, we can get the cnt array in the range by make the difference between the count at the end of the range and the count before the start of the range. However, we acutally do not need really count them. The only thing we care is whether it is greater than 0. So, instead of compute the array, we can just check if the count at the end > the count before the start. if it is satisfied and there is a previous index also satisfied the requirement, we can check if their difference is minimum.

In the presum, I add a additional virtual element in the head to avoid check the boundary condition `i > 0 ? presum[i-1] : new int[101]`. so the presum[query[0]] is actually presumNoVitrual[query[0]-1] (presum without the additional head value) and presum[query[1] + 1] is actually presumNoVirtual[query[1]]

Time complexity: preprocess the presum O(n) and for each query. The diff and search is O(100) = O(1) and we have O(m) queries. O(mn) 

Note,
1. this question can also be solved by Segment Tree
2. If we do not have the limit 1 <= nums[i] <= 100, [Souvenirs](https://codeforces.com/contest/765/problem/F). This question can be solved by a complex version of Segment tree -> [Persistent Segment Tree](https://usaco.guide/adv/persistent?lang=cpp#persistent-segment-tree).