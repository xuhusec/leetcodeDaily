### [2226.Maximum-Candies-Allocated-to-K-Children](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)

#### Solution 1: binary search

the question asks the **minimal** weight. Given a max allowed weight, says max, we can easily verify if all the packages can be shipped within d days. Because all the weight should be non-negative. So we just try to ship consective packages together. If the weight is greater than the max, we just ship the last next day.

So, if m is the min allowed weight, m + 1 would also gives at least d days. So does m + 2, m + 3 .... So we can resolve this via binary search.