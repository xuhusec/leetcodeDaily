### [1870.Minimum-Speed-to-Arrive-on-Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time/)

#### Solution 1: binary search

This problem asks the minimum positive integer speed. It is hard to get the answer directly. So we can try the binary search. Also, it provides the upper limit 10<sup>7</sup>. We have the possible range from 1 to 10<sup>7</sup>. Also, with a given speed, it requires O(n) for checking if it satisfy the requirement (where n is the length of dist array). Also with the result, we know what to check next because the travel time is non-increased when speed growth. 

As there might be no solution, we can set the upper limit to 10<sup>7</sup> + 1 then we know if the result > 10<sup>7</sup>, there is no answer. However, also the problem says the length of dist array <= 10<sup>5</sup>. Given the speed 10<sup>7</sup>, the minimum time to spend is len(dist) - 1 + dist[n-1]/10<sup>7</sup> > len(dist) - 1. So you can also keep the hi as 10<sup>7</sup> but firstly check if hours > len(dist) - 1. If not, we would return -1 directly.

```java
    if (hours <= dist.length - 1) {
        return false;
    } 
```

Now, we need to calculate the time with a given speed. According to the statement, we can round up the time expect the last trip. So we can write as follow.
```java
    private double travelTime(int[] dist, int speed) {
        double time = 0;
        for (int d : dist) {
            // round up to add waiting time
            time = Math.ceil(time);
            time += ((double)d) / speed;
        }
        return time;
    }
```
It works but it is slow. The computation in double is slower than that in integer. Note, expect the last one, all others are integer computeration. If the d cannot be divisiable by speed, we can just add 1 to the integer division result. Otherwise, it does not need to anything.
```java
    private double travelTime(int[] dist, int speed) {
        //we only need integer for the first n - 1
        int time = 0;
        // keep the last distance
        int prev = 0;
        for (int d : dist) {
            // round up to add waiting time
            time += prev % speed == 0 ? 0 : 1;
            time += d / speed;
            prev = d;
        }
        // add the decimal part of the last one
        return time + ((double)(prev % speed))/speed;
    }
```
or if we track the index.
```java
    private double travelTime(int[] dist, int speed) {
        //we only need integer for the first n - 1
        int time = 0;
        // skip the last
        for (int i = 0; i < dist.length - 1; ++i) {
            time += dist[i] / speed + （dist[i] % speed == 0 ? 0 : 1);
        }
        return time + ((double)dist[dist.length - 1])/speed;
    }
```

Note (dist[i] - 1) / speed + 1 is equivalent to dist[i] / speed + （dist[i] % speed == 0 ? 0 : 1) so we can made it a little more faster.

```java
    private double travelTime(int[] dist, int speed) {
        //we only need integer for the first n - 1
        int time = 0;
        // skip the last
        for (int i = 0; i < dist.length - 1; ++i) {
            time += (dist[i] - 1) / speed + 1;
        }
        return time + ((double)dist[dist.length - 1])/speed;
    }
```