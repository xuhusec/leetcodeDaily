### [1300.Sum-of-Mutated-Array-Closest-to-Target](https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/)

#### Solution 1: binary search

Clearly, as the limit growths, the muntated sum is non-decreasing. We can use binary search.

If there is solutions for mutated sum == target, how many solutions would there be? It depends. If the integer is less than the max value, we would only have one. If we have two solutions say a and b and a < b, mutated sum with a < mutated sum with b (we can just check the larget value). It contradicts the two sums are equal to the target. But if the integer is greater than or equal to the largest value. We have infity solution.  So to make the program simple, we can just set the hi = the largest element in the array.

We can try with the `while (lo < hi)` template. If there is a solution to make the mutated sum == the target, we just need to return that value. Otherwise, we have two candidates. One make the sum a little greater than target and the other is a little less than the target. And we need to pick the one with less difference from the two as the final answer. This template also works for multiple solution. 

```java
    while (lo < hi) {
        int mid = lo + (hi - lo)/2;
        int sum = getMutatedSum(arr, mid);
        // track hi = mid to minimum value such that the mutated sum greater than or equal to target when exit 
        if (sum < target) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }
```
In this way, we can check getMutatedSum(arr,lo); and getMutatedSum(arr, lo - 1). Pick the better one from the two. As we already know the first is greater than or equal to the target and the second is less than the target. We can skip the Math#abs. But please note we still need to set hi = max(arr), When the sum(arr) without mutation < target. we would keep increase low and that won't end until it reaches the upper limit. In that case, we should keep the upper limit as max(arr).
```java
    int diff0 = getMutatedSum(arr, lo) - target;
    int diff1 = target - getMutatedSum(arr, lo - 1);
    return diff0 < diff1 ? lo : lo - 1;
```
with hi = max(arr), we can also check push the up lower limit lo = mid when sum <= target but it works only when there is one solution and we would check getMutatedSum(arr,lo); and getMutatedSum(arr, lo + 1).

As there is only one solution when hi = max(arr), we can also use the `while (lo <= hi)` template and we can record the candidate result along the way because when lo == hi. It either from lo = mid + 1 or hi = mid - 1. That means we must already check the ajdacent value and when lo == hi, we did not skip to check it. So all the potential solutions are checked.

```java
    public int findBestValue(int[] arr, int target) {
        int lo = -1;
        int hi = 0;
        for (int a : arr) {
            hi = Math.max(hi, a);
        }
        int diff = Integer.MAX_VALUE;
        int ret = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int sum = getMutatedSum(arr, mid);
            if (target > sum) {
                lo = mid + 1;
                // >= because we want to keep the lower value if diff is the same
                if (diff >= target - sum) {
                    diff = target - sum;
                    ret = mid;
                }
            } else if (target < sum) {
                hi = mid - 1;
                if (diff > sum - target) {
                    diff = sum - target;
                    ret = mid;
                }
            } else {
                return mid;
            }
        }
        return ret;
    }
```

With `while (lo < hi)` template, you can also record the result. However, please note the lo == hi is not calculated. For most cases, it does not matter because low and high are set after computation. The only expection is two boundaries. No one calcualte their value because they are directly set by us. So, we can shift the boundary by 1 to force the boundary is calculated. ie. lo = -1 and hi = max(arr) + 1.

For getMutatedSum function, we can also make the arr sorted and generate the prefix sum array for arr. In that case, we only need to find the last element in the array that < the boundary and get the prefix sum + (rest of len) * mid. The sort of arr would be nlogn. For each validation it is reduced from n to logn.  So the the time complexity is O(nlogn + log(max(arr))logn) = O((n + log(max(arr)))logn). Our current implementation is O(nlog(max(arr))). It is hard to say which one is better. So I did not go that way.