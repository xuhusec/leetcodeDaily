### [2202. Maximize the Topmost Element After K Moves](https://leetcode.com/problems/maximize-the-topmost-element-after-k-moves/)

#### Solution 1: Greedy
The question is asking max topmost element after k moves. Let's check how to make ith element the topmost element. 

If i > k, there is no way to make it the topmost. One can at most remove k elements. In 0-indexed array, they are from 0 to k - 1 (inclusive). The rightest element to be exposed is the kth element. So we do not need to consider elements after kth element. 

If i < k - 1, we can remove first k - 1 elements and add that element back when k - 1 <= number of elements. If k - 1 > number of elements, we can remove all elements. If there is one move left, we put the ith element. Otherwise, we can choose any element other than ith element. Add it back and if more than one move left we can remove it if still more than one move left we add it back again. Repeat this process until 1 move left and we can put the ith element on the top. So when k - 1 > number of elements, we can get any element on top for most cases. **But we need at least two elements for this process.** So, if there is only one element int array, we can do perform this tick. The only thing we can do is to remove it and add it back. So if k is even, we have it on top but if k is odd there is no element on the pile, we should remove it.

How about i == k - 1 ? To expose k - 1 (0-indexed), we need to remove the first k - 1 elements, that is k - 1 steps and only 1 step left. In that case, we could only add back one element or remove the kth element at index k - 1. There is no way to expose it.

In summary, we could only expose elements from nums[0 .. k - 2] and nums[k] if k < nums.length or all elements if k > nums.length except when nums.length == 1. In that case, it depends on if k is even or odd. Only when k is even, we have a solution. The rest is the figure the max number among those elements.


Note you can also combie the k - 1 > nums.length cases as follow

```java
for (int i = 0; i < Math.min(k - 1, nums.length); ++i) {
            max = Math.max(max, nums[i]);
        }

if (k < nums.length) {
    max = Math.max(max, nums[k]);
}
```