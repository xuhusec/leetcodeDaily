### [2141.Maximum-Running-Time-of-N-Computers](https://leetcode.com/problems/maximum-running-time-of-n-computers/)

#### Solution 1: binary search

This question is tricky. It is hard to come up with one direct solution. So we can think about binary search to find one. Clearly, if n computers cannot run x time units simultaneously, they cannot run x + 1 time units simultaneously. Similarily, if n computers can run x time units simultaneouly, they can run x - 1 time units simultaneously. So it is possible for binary search to find the solution.

The question then become how to check if, for a given x, n computers can run x simultaneously. Because the questions stated that we can remove and insert batteries any number of time. Let's take it to the extreme. Let's swap the batteries every minutes. After each minute, we remove all batteries and insert it back if there still juice left or replace it with a battery can serve. That means a binary can serve Math.min(x, its battery Levels). It cannot exceed x otherwise, the battery served more than one computer at a given time. So the total of serving time given x minutes is the sum of Math.min(x, batteries[i]). If the sum is greater than or equal to x * n, we can serve x minutes.

With above conclusion, we can also define the upper boundary for the binary search. it is the sum of batteries[i] divded by n as we think there is no limit. the lower bound would be 0. Because we want the maximum, in binary search we should kept pushing up the lower boundary, `lo = mid`. For an invalid case, we have `hi = mid - 1`. The 0 - 1 test tells `int mid = lo + (hi - lo) / 2` would cause infinite loop. so we need `int mid = hi - (hi - lo) / 2`.