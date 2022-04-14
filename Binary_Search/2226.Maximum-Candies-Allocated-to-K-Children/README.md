### [2226.Maximum-Candies-Allocated-to-K-Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children/)

#### Solution 1: binary search

the question asks the maximum number of candies in a sub piles so that we can have at least k such subpiles/piles for k children. It said some piles of candies may go unused. So we only need to focus on if we can get k such piles. It seems hard. However, given a number of candies, says num, we can easily verify if we can get k piles. That would be the sum of candies[i]/num because we cannot merge two piles togehter. 

So, if m is the max number of candies, m - 1 would also gives at least k piles. So does m - 2 ... 1. So we can resolve this via binary search.