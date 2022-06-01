### [2104.Sum-of-Subarray-Ranges](https://leetcode.com/problems/sum-of-subarray-ranges/)

#### Solution 1: Brute force

A brute force solution is to list all the possible sub arrays and find the maxmum and the minimum of each. It would be O(n<sup>3</sup>) (list all the sub arrays O(n<sup>2</sup>); iterate over each subarray O(n) for sum and locating minimum). That would time out A better solution wouild be fix the start and keep track the minimum ams sum during the process of expanding the end. It is still  O(n<sup>2</sup>). It is only 1000 elements. It would work.

```java
public long subArrayRanges(int[] nums) {
    long res = 0;
    int min = 0;
    int max = 0;
    for (int i = 0; i < nums.length - 1; ++i) {
        min = nums[i];
        max = min;
        // for 
        for (int j = i + 1; j < nums.length; ++j) {
            min = Math.min(min, nums[j]);
            max = Math.max(max, nums[j]);
            res += max - min;
        }
    }
    return res;
}
```

#### Solution 2: Monotonic Stack

The time complexity of the brute force solution is O(n<sup>2</sup>). But this can be done in O(n) time complexity.

To reduce the time complexity, we can think reversely. Instead of list all the sub arrays. For each element, we can find out all the sub arrays take it as the minimum element. And how many subarrys take it as maximum. However, find and an element and expand to two ends is still O(n<sup>2</sup>) especially when all the element is the same. The question become how to efficiently locate the last element smaller than the current element and the next element smaller than the current element and locate the last element larger than and the next element largger than the current element. Each of them can be done via monotic stack. The question can become two sub problems. Get the sum of maxs for all subarrays and get the sum of mins for all subarrys. For an element and to get the sum of subarrays with it as min (max is the same). With monotonic stack. We can know the previous larger element and the next larger element. The total number of subarrays it is the product of "the number of ways of prefix" and "the number of ways of suffix". please do not forget counting non-prefix subarrays (the current element is the first element) and the non-suffix subarrays (the current element is the last element).

To get the sum of mins, each time, we visit a new element, the element in the stack that is greater than the new element should be taken out. The new element is the next smaller element of the token out element. How about the previous smaller element? It is a monotonic stack. So every element before the taken out element is smaller than the taken out element. So the previous element is the stack is the index of previous samller element. So, the smallest element between the previous smaller element and the next smaller element is the one we just taken out. We also have the two indices for the previous smaller element and the next smaller element (we iterate over index the current index we investigate is the index of the next smaller element). the number of ways of prefixs is (i - index of taken out element) and the numnber of ways of suffix is (index of taken out element - the top element in the stack). For index 0, there is no previous samller element. To fix it, I add -1 to the stack and does not pop it out. 

You might notice there could be duplicates in the array. Actually both `nums[stack.peek()] >= curVal` and `nums[stack.peek()] > curVal` work, the previous one use the later of the equal element to consider the prefix including equal element and the later one take the first appear element to count the suffix including the equal element. That is because we need to clear the stack after the above iteration And the sum to the next smaller element is the sum of the whole array. 

We need to handle the smallest element case. So -1 was pushed to the stack first as appending a very small element so that it become a boundaries for the sub array with smallest element in the array. And we have `stack.size() > 1` to make sure it is not popped out. 

To get the sum of maxs, it is similar, we just need to change `nums[stack.peek()] >= curVal` to `nums[stack.peek()] <= curVal`.

You might also follow LC 496 to get the two boundaries with two loop for each (mins and maxs). If so, please only consider one of them would take the equal element to avoid a element being considered twice.