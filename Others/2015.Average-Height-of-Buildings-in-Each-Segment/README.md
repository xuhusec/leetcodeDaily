### [2015.Average-Height-of-Buildings-in-Each-Segment](https://leetcode.com/problems/average-height-of-buildings-in-each-segment/)

A perfectly straight street is represented by a number line. The street has building(s) on it and is represented by a 2D integer array `buildings`, where buildings[i] = [start<sub>i</sub>, end<sub>i</sub>, height<sub>i</sub>]. This means that there is a building with height<sub>i</sub> in the half-closed segment [start<sub>i</sub>, end<sub>i</sub>).

You want to **describe** the heights of the buildings on the street with the **minimum** number of non-overlapping **segments**. The street can be represented by the 2D integer array street where street[j] = [left<sub>j</sub>, right<sub>j</sub>, average<sub>j</sub>] describes a half-closed segment [left<sub>j</sub>, right<sub>j</sub>) of the road where the **average** heights of the buildings in the **segment** is average<sub>j</sub>.

* For example, if `buildings = [[1,6,3],[5,9,4]]`, the street could be represented by `street = [[1,5,3],[5,6,3],[6,9,4]]` because:
  1. From 1 to 5, there is only the first building with an average height of `3 / 1 = 3`.
  2. From 5 to 6, both the first and the second building are there with an average height of `(3+4) / 2 = 3`.
  3. From 6 to 9, there is only the second building with an average height of `4 / 1 = 4`.

Given `buildings`, return *the 2D integer array `street` as described above (**excluding** any areas of the street where there are no buldings). You may return the array in **any order***.

The **average** of `n` elements is the **sum** of the n elements divided (**integer division**) by `n`.

A **half-closed segment** `[a, b)` is the section of the number line between points `a` and `b` **including** point `a` and **not including** point `b`.

Constraints:

* 1 <= buildings.length <= 10<sup>5</sup>
* buildings[i].length == 3
* 0 <= start<sub>i</sub> < end<sub>i</sub> <= 10<sup>8</sup>
* 1 <= height<sub>i</sub> <= 10<sup>5</sup>

#### Solution 1: Diff Array

This problem asks to separate and group ranges based on the average height. To get the average height, we need number of buildings and the sum of height. The question asks the average height in integer divison so the sum of height keeps all the information comparing to the average. The input is the building properties: start, end and height. A building only has one height. A Brute force method would calculate the sum of height and number of buildings for every point and merge the range by the average height. It does not work because it is too time-consuming and the space consuming, start and end are from 0 to 10<sup>8</sup>. 
I
t reminds us the diff array method. We do not need to calculate the height for all points. but we only need to keep track the start's and end's. Average would only change at that point. Because the range of start and end is large. We can use Map to record those events. Please note what analyze previously. We need both the sum of height and the count of buildings. And both of them are only changed at the start's and end's. So we need to record both of them unlike other diff array problem that only keeps one variable changed. The map would store location(start or end of a building) and the value is diff in height and in count.

Then we can sort the map by the location key. Go over the sorted map. we can skip 0 change in sum and 0 change in cnt as the do not affect the result. But we could have only change in sum or only change in cnt cases. E.g. {{1, 3, 5}, {3, 6, 2}}. At point 3, we have {-3 , 0} (this first is change in height and the second is change in cnt) and it still affect the average.

Then if the previous count is zero, we can start record next start, average and cnt. Because, we skip case {0, 0}. so the next cnt always > 0. Otherwise, we can check if the average is changed. If it changed we need to add a new range. Please be careful when cnt + cur.cnt == 0.

Note
1. I used hashMap for O(1) retrive the diff and sort it by put all elements in a TreeMap
2. The final range can also be get in the following
   1. calcuale the average at all event points(when cnt == 0, the average can be marked as 0 to be discarded)
   2. merge the interval when the average is the same. and start next when the average is changed.
   3. skip 0 average interval. 