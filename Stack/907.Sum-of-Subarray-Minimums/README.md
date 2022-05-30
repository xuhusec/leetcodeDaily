### [907.Sum-of-Subarray-Minimums](https://leetcode.com/problems/sum-of-subarray-minimums)

#### Solution 1: Monotonic Stack

A brute force solution is to list all the possible sub arrays and find the minimum of each. It would be O(n<sup>3</sup>) (list all the sub arrays O(n<sup>2</sup>); iterate over each subarray O(n)). A better solution wouild be fix the start and keep track the minimum as a variable during the process of expanding the end. It is still  O(n<sup>2</sup>). It would exceed the time limit.

To reduce the time complexity, we can think reversely. Instead of list all the sub arrays. For each element, we can find out all the sub arrays take it as the minimum element. However, find and an element and expand to two ends is still O(n<sup>2</sup>) especially when all the element is the same. The question become how to efficiently locate the last element smaller than the current element and the the next element smaller than the current element. It can be done with monotic stack. The question become similar to LC 84. 

We can maintain a monotonic increasing stack. The element is the index of the element but the stack is about the value of corresponding index not the index itself. Why do we keep the indices instead of the value directly? That is because we need to calculate how many kinds of prefix can be put in front of the minimum elements and how many kinds of suffix can be put after the element.

Each time, we visit a new element, the element in the stack that is greater than the new element should be taken out. That because we find how many kinds of suffix for that taken out element. How about the prefix? It is the monotonic stack. So every element before the taken out element is smaller than the taken out element. So the difference in indices of the taken out element and the element before it is the number of ways to prefix it in to an sub array. Please note we include the zero-element-prefix here. That is why it is not taken out index - prev index -1. Similiar to suffix, it is current index - taken out index. We can repeat this process until we met an element is less than the new element. Why the element can be taken out? That is because the new element is less than it. So in future, that taken out element would not be the smallest element because of the new element.

You might find the previous analysis. We do not discuss the equal same. We need to handle the equal case to make sure we do not count it twice. Here I make the decision to make the suffix does not contain the equal element for the taken out element. So we have `arr[stack.peek()] >= curVal`. It would pop the element equal to the new element so that the suffix does not contain the new element. Of course, you can use `arr[stack.peek()] > curVal` to make the prefix array does not contain the euqal element. They both work.

We need to handle the smallest element case. So -1 was pushed to the stack first as appending a very small element so that it become a boundaries for the sub array with smallest element in the array. And we have `stack.size() > 1` to make sure it is not popped out. 

After the whole process, some elements are still in the stack like the last element. For them, the subarray can expend to the end. So we pop them out and use the length of the array to figure out the kinds of suffix.

Please note the value is very large. I cast the element to long before getting the product.
