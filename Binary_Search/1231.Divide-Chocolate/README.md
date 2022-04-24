### [1231.Divide-Chocolate](https://leetcode.com/problems/divide-chocolate/)

You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array `sweetness`.

You want to share the chocolate with your `k` friends so you start cutting the chocolate bar into `k + 1` pieces using `k` cuts, each piece consists of some **consecutive** chunks.

Being generous, you will eat the piece with the **minimum total sweetness** and give the other pieces to your friends.

Find the **maximum total sweetness** of the piece you can get by cutting the chocolate bar optimally.

#### Solution 1: binary search

Each piece consists of some consecutive chucks. When the minimum total sweetness increases, the total number of pieces would be non-increased. And because the consecutive requirment, it would give O(n) runtime to check how many pieces can we get given some minimum total sweetness. So we can try  resolve this question with binary search.

The question asks for the maximium of the mini total sweetness. Because it asks for the max, we would keep track lo = mid to push for the maximum solution in binary search. And the hi would be hi = mid - 1. The 0- 1 tests would tell `int mid = lo + (hi - lo)/2;` would lead to infinite loop ( lo = 0, hi = 1 => mid = 0 => lo = mid = 0). So we need `int mid = hi - (hi - lo)/2;`.

For boundaries, lo would be the minimum of the sweetness and hi would be the sum of the sweetness. Because k < sweetness.length. We can get sweetness.length with the minimum of the sweetness. So it must contains solution that means we do not need to check at the need.

To verify a sweetness work, we need some greedy strategy. As long as we found a group of consecutive chucks >= required sweetnes, it would be a piece to serve.