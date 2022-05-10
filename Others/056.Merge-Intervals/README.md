### [056.Merge-Intervals](https://leetcode.com/problems/merge-intervals/)

#### Solution 1: Line Sweep

The Line Sweep algorithm would break the continue section into events. The event of starting and the event of ending. So [1 , 6] would become {1, EVENT_START} and {6, EVENT_END} Then we can sort the event by the time and count how many events we have. For EVENT_START, we add 1 to the count and for EVENT_END, we decrease 1. When the count is zero, that means we found an end for the new interval. Then we added to the final answer. To find the start, it would be when the EVENT_START and the count is 1. But there is a trick. Because we need to merge [1 , 3] and [3, 6]. When the same time point shows, we need to check if there is any EVENT_START first. So when sort, when the time point is the same, we need sort EVENT_START first and then sort by EVEN_END. So we can use 1 for EVENT_START and -1 for EVENT_END. so we would have `Arrays.sort(events, (a,b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0])`

#### Solution 2: Sort + Greedy

The idea is to sort the intervals by starting time and keep track the end time. If the observed max end time is greater than or equal to the examed start time, we have merge the two by get the max end time between the observed one and the examed end time `end = Math.max(end, intervals[i][1])`. Otherwise, we cannot merge any more. We need to add it to the answer and provide new start time and end time from the examed interval for the next round.

The boundary need to be taken care separately. The start time is easy to set before interation. Unlike the line sweep, at the end of interation the last interval has not been added so we need to add them in the end.
