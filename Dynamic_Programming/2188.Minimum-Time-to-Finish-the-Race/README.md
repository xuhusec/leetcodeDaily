### [2188. Minimum Time to Finish the Race](https://leetcode.com/problems/minimum-time-to-finish-the-race/)

#### Solution 1: DP

We need to check all the different combinations/plans to find the minimum time. Because there is no restriction on new tires after change. We can use DP.

Let us define dp[i] is the minimum time to finish i laps. We need to a kind of tire on the ith laps. But we do not know how many laps this tire already worked on. So we can try all possiblities. For the laps finished before equip this tire, we can get from previous dp result. so we have

`dp[i] = Math.min(dp[i], dp[i-j] + (i == j ? 0 : changeTime) + minTime[j-1])`  where minTime[j - 1] is the minimum time to finish j laps with only one tire. `(i == j ? 0 : changeTime)` is because we do not need to change time if drive without change. for minTime, we can try to preprocess it by calculate all the tires at j laps and get the minimum. However, if the travel time on a lap is greater than changeTime + f. Then we should change it because at least changing to the same kind of tire is better than continuing using the current tire. In my implemention, the number of minTime is compute first f*r<sup>n - 1</sup> = changeTime + f; ==> n = 1 + log((changeTime + f)/f)/log(r). We can find the maximum laps from all. You can also get it by using the upper bound of changeTime and minimum of f and r. 1 * 2  <sup>n - 1</sup> = 100000 + 1; => n = 20. With this number we can try to find the minimum time of each laps. But please note we need to sum of the costs to find the time to each laps. It was computed by loop but you can also get it from the sum of Geometric progression f(1 - r<sup>n</sup>)/(1 - r)

It is still slow. note for tires. if fi >= fj and ri >= rj, we can always to choose j instead of i because  f*r<sup>n - 1</sup> for i would be always larger than rj. We can ignore such tires. We can sort the tires array first by r and than by f. Because r become larger and larger, we need to choose f less than the previous f. This would improve the performance dramatically. 

Note the time grows very fast, you might use long or double instead of integer to avoid overflow.