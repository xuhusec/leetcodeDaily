### [253.Meeting-Rooms-II](https://leetcode.com/problems/meeting-rooms-ii)

Given an array of meeting time intervals `intervals` where `intervals[i] = [starti, endi]`, return the *minimum number of conference rooms required*.

```
Example:

Input: intervals = [[0,30],[5,10],[15,25]]
Output: 2
```
 
Constraints:

* 1 <= intervals.length <= 10<sup>4</sup>
* 0 <= start<sub>i</sub> < end<sub>i</sub> <= 10<sup>6</sup>

#### Solution 1: Line Sweep

Each meeting would specify a range. For that range, a meeting room would be occupied. It changes at the start of the meeting and at the end of the meeting. The time is divided by 3. The first part is before the meeting starts, there is no meeting room needed for the meeting. The second part is during the meeting between the start time and end time (exclusive). A meeting room is required for the meeting. The 3rd part is after the end time (inclusive). No meeting room is for the meeting again. So, if we can check the start and end time of a meeting we would know when a room is needed. Think the whole problem as several event lines horizontally. We would move a vertical line. The max number of horizatonal line crossed during the whole process is the anwser we need. Please check the drawing in Notes of [LC 732](../732.My-Calendar-III/README.md). Also, from the analysis above, we only need to check the start time and end time for each meeting because that is where changes happens. So, a meeting would be broken into two parts. The first part is the event time (start time or end time) and the second the 1 for start and -1 for ends to help us calculate the line crossed, i.e. the acculmated sum. The max acculated sum during the process is our answer.

Please note when a meeting ends another meeting can take the room immediately. So we can sort the second item if the event times are equal to make sure the end would count first.