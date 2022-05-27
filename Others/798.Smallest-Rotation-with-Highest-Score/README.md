### [798.Smallest-Rotation-with-Highest-Score](https://leetcode.com/problems/smallest-rotation-with-highest-score/)

#### Solution 1: Diff Array

The Brute Froce solution would be shift element one by one for n - 1 times and check the score for each cases. This is time-consuming and it will exceeds the time limit. So we need to look from another angle. Instead of considering the whole array, we can check individual element. For each element after k moves, what score it would get. Rememebr that we only get score when the the index is greater or equal to the value. So, for each individual we can directly calcaulte when it can get score. Then, we have a better brute force. Instead of moving the array to simulate, we can have an array to record scores after moving k times. We kown when it would get score and add 1 to corresponding position. The index of the max is the answer. This is improved but it is still time consuming.

Let check when an elment would obtain a score. 
1. If it's original index, says i, >= the value, or nums[i], we got a score from this element without any move. Until we shift the element to nums[i] - 1, we would always get one score. After i - (nums[i] - 1) steps, we would get no score until we move to the end of the array. That takes i + 1 steps. 
2. If i < nums[i], we won't get score until it moves to the end. That takes i + 1 steps. And we need to move to num[i] - 1. It takes i + 1 + (n - nums[i]) steps.

So, when we start get score, the interval is continuous. It is a range. and the delta bring by this range is always 1. Hence, we can use Diff Array to avoid mark for all interval each time. The real sum is the prefix of the diff array and we want the max of the sum during the process. 

We would identify the positon start to get score and the first positon we do not get score. We add 1 for the start and -1 for the end. Note for the case i >= nums[i], we have two ranges.

Please note i + 1 would exceed the the bnoundary when i = n - 1. Hence I add an additional space in diff array. 
