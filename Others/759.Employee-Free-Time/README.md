### [759.Employee-Free-Time](https://leetcode.com/problems/employee-free-time)

We are given a list `schedule` of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping `Intervals`, and these intervals are in sorted order.

Return the list of finite intervals representing **common, positive-length free time** for all employees, also in sorted order.

(Even though we are representing `Intervals` in the form `[x, y]`, the objects inside are Intervals, not lists or arrays. For example, `schedule[0][0].start = 1`, `schedule[0][0].end = 2`, and `schedule[0][0][0]` is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

```
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[6,10]]]
Output: [[3,5]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 5], [10, inf].
We discard any intervals that contain inf as they aren't finite.
```

Constraints:

* 1 <= schedule.length , schedule[i].length <= 50
* 0 <= schedule[i].start < schedule[i].end <= 10<sup>8</sup>

#### Solution 1: Line Sweep

The problem seems the opposite of merge k intervals. It asks for the gap of the final merged intervals. The merge intervals can be processed by Line sweep. This can be done as well. We can record the start and end time of a interval and assign 1 and -1 respectively. After sorting them based on the event happen time, i.e. start or end, the prefix sum is the number of employee that are busy. When the prefix sum is zero, that is all the employee free time. We just need to record those ranges that the prefix sum is zero. Please check Notes of LC 732 for detials of this idea -- a virtual line sweep accross the event lines.

This problem asks not record the (-inf, a) and (z, inf). So we can initialize the start time as -1 and only start record the gap when the start is greater than -1. Each time the prefix sum is zero, that is our next start time and the start time of the next interval is our end of the free time interval. Also, please note, at the boundary, we need to check +1 before -1. If one employee is free at 3 PM and the other starts work at 3PM in a two employee system, 3PM is not a start of free time. We need either check if previous start time of the free event is the same as the end. Or just sort 1 before -1 if the event time is the same. The time complexity is O(mnlogmn) where m is the number of employee and n is average counts of number of intervals. This is form the sorting and we ignore those O(mn) operations

#### Solution 2: Divide and Conquer (merge sort)

As stated in previous solution, it is a reverse way of merge k intervals. In the previous solution, one condition is not used. That is each list of interval is already sorted for an employee. So, it is basically merged k sorted list. And the final answer would be identify the gaps in the final sorted list. That would gives us O(mnlogm) which is better than O(mnlogmn). There are many ways for the merged k sorted list. Here, I used the idea from merge sort. Another O(mnlogm) algorithm is based on PriorityQueue.

To merge the k lists, I would like use the start and end. Then merge the first with the last, the second with the second last, the third with the third last etc. `while (start < end) { doSomthing(); start++; end--}`. In this way, I do not need to worry about if the total number is odd or is even. The next is to merge to sorted list. The idea is to record the start time from the early one and check if we can expand the end from overlaping. If not, we find an interval. Otherwise, we need searching.

Finally, we can go through the merged list and record the gap from one list to the next in order.

Note:
1. In my implementation, I replaced the list at the start index with merged list. In real work, you might need to copy the original list first instead of working on the input. That means always do `schedule = new ArrayList<>(schedule);` regardless of whether schedule is RandomAccess.
2. For merge sort, I have two implementation here. One is based on iterator and the other is based on index. Iterator method fits for both RandomAccess list and non-RandomAccess list, like LinkedList, and for the index method, we need to convert non-RandomAccess list to RandomAccess list. Otherwise, it would be O(n<sup>2</sup>) for each merge instead of O(n). 
