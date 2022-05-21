### [2251.Number-of-Flowers-in-Full-Bloom](https://leetcode.com/problems/number-of-flowers-in-full-bloom/)

#### Solution 1: Diff Array

The problem provide many ranges that flowers are in full bloom. And the answer would be number of flowers in the observation days. The brute force solution would mark the full bloom days in an array. This cannot work because the start and end day are from 1 to 10<sup>9</sup>. 1. It would be out of memory and 2. marking days only would be O(mn) where n is number of flowers and m is average length full bloom. So it would exceed the time complexity as well. For those ranges that only changes in start and end. We can reduce the time complexity with diff array. Instead of marking individual entry, we can mark the start and end. That is 1 for start and -1 for end. And the prefix sum of the diff array is the total. Also, please note the prefix sum only changes at those start and end points. So we can only forcus on those points and check which prefix sum fits an observer.

Please note an observer will find a flower bloom from start to the end inclusive. So, the -1 would be set at end + 1 instead of end. Also, since start and end are from 1 to 10<sup>9</sup>. record them in array might be out of memory. So we use a map. But the map should be sorted by key before calcualting the prefix sum.

To get how many flowers, an observer can find. We have two solutions.
1. convert the diffMap to prefix sum map. and find number of flowers for an observer with binary search (using TreeMap).
2. make an array of indices of persons array and sort the index based on the value in persons. And we can run 2 pointers to fill the answer.

The implementation provided is the second method.