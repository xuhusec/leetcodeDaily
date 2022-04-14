### [1608.Special-Array-With-X-Elements-Greater-Than-or-Equal-X](https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/)

#### Solution 1: binary search

This problem is marked as easy because the data size is small but it can be a medium question with larger datasets. The goal is to find the specfial value x that there are exactly x elements that greater than or equal to x. First, Let us check why there could be at most 1 solution.

Let's assume there are two solutions a and b.

We have
There exists a elements in the array greater than or equal to a.
There exists b elements in the array greater than or equal to b.

Because a and b are two different solutions. We have a != b. Let's say a < b. Note when a < b, there should be no less elements in the array greater than or equal to a than those of b. so number of elements in the array greater than or equal to a >=  number of elements in the array greater than or equal to b. That means a >= b (a = number of elements in the array greater than or equal to a , b = number of elements in the array greater than or equal to b). This contradicts to a < b. So, we could only have at most one solutions.

when a < b, we have number of elements in the array greater than or equal to a >=  number of elements in the array greater than or equal to b. This also gives us a way to find the solution in binary search. Because when x grows the cntGreaterOrEqual(nums, x) is non-increased, so we can perform binary search. Normally, we would have the binary search written in the following way.

```java
   while (lo < hi) {
       int mid = hi - (hi - lo)/2;
       if (cntGreaterOrEqual(nums, mid) >= mid) {
           lo = mid;
       } else {
           hi = mid - 1;
       }
   }
```
And we would have lo == hi as the final anwser. But this only works when there are at least one solution and we want the max or min one from them. In this problem, it may not have a solution. So we need to check in the final stage if cntGreaterOrEqual(nums, lo) == lo.

There is another way of writting binary search might be more suitable for this question. It works well when there is at most one solution.

```java
   while (lo <= hi) {
       int mid = hi - (hi - lo)/2;
       int cnt = cntGreaterOrEqual(nums, mid);
       if (cnt > mid) {
           lo = mid + 1;
       } else if (cnt < mid) {
           hi = mid - 1;
       } else {
           return mid;
       }
   }
```

Please note in the first solution, we have `while (lo < hi)` because we would always have lo == hi when exits so that we can have the max or min solutions. In the second, it is `while (lo <= hi)` because when lo == hi, it may not be the solution. When it is existed from the loop, we know there is no solution.

cntGreaterOrEqual(nums, mid) would be easy to implement, we just iterate over the array.

Alternatively we can sort the array first and figure out elements greater than or equal to x would be a binary search. The search part would follows;

```java
    if (nums[0] >= target) {
        return nums.length;
    }
    while (lo < hi) {
       int mid = hi - (hi - lo)/2;
       if (nums[mid] < target) {
           lo = mid;
       } else {
           hi = mid - 1;
       }
   } 

   return nums.length - lo - 1;
```

However, sorting would cost O(nlogn) so it does not improve the runtime complexity. But for a sorted input, the complexity is reduced to O(logn logn);


#### Solution 2: Bucket sorts

The above solution would be O(nlogn). Can we improve the runtime complexity? 

Please note the upper limit of x is the length of the array because there are at most elements. With a determined range, we can perform bucket sort. The idea is to put/cnt elements in consecutive buckets. The sort only costs O(n) but it requires O({range}) spaces. In this case, O(n). Then we can go backward to count elements in each buckets. The accumulated cnts are the elements greater than or equal to the current index of buckets. For those elements greater than the length of the array. We would put them in the last bucket because they cannot be a candidate of the answer and they are greater than or equal to the length of the array.