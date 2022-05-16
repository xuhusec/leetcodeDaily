### [1109.Corporate-Flight-Bookings](https://leetcode.com/problems/corporate-flight-bookings/)

#### Solution 1: Diff Array/ Line sweep

To find out the number of seats reversed at a given stop, we need to count all the passenger getting on board before and at this stop. The brute force solution would take an interval and fill all the people from aboard stop to the getting off stop. But that would lead to O(n<sup>2</sup>). To reduce the complexity, we can use Diff Array. For each interval, we add reversed seats at the start stop and minus them at the last stop + 1 (This is according to the statement for trips from 1 to 2. Stop 2 still counts for the reversed seats). Finally, we can get the presum array of the diff. And that is the answer.

Usually, we do not map the Diff Array into the full range. Instead, we can make a sorted events for aboarding and taking off to save some space. But in this problem, the answer is the number of passengers at each stop. So we make  the diff in the final array and get the prefix sum in the array as well. However, the last stop + 1, aka ans[n], does not exists, we skip that during marking the diff. 