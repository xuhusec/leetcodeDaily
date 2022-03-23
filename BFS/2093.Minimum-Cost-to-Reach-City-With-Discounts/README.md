### [2093. Minimum Cost to Reach City With Discounts](https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts/)

A series of highways connect `n` cities numbered from `0` to `n - 1`. You are given a 2D integer array `highways` where highways[i] = [city1<sub>i</sub>, city2<sub>i</sub>, toll<sub>i</sub>] indicates that there is a highway that connects city1<sub>i</sub> and city2<sub>i</sub>, allowing a car to go from city1<sub>i</sub> to city2<sub>i</sub> and vice versa for a cost of toll<sub>i</sub>.

You are also given an integer discounts which represents the number of discounts you have. You can use a discount to travel across the ith highway for a cost of toll<sub>i</sub> / 2 (**integer division**). Each discount may only be used **once**, and you can only use at most **one** discount per highway.

Return the ***minimum total cost*** to go from city `0` to city `n - 1`, or `-1` if it is not possible to go from city `0` to city `n - 1`.

#### Solution 1: Dijkstra

The question is asking for minimum total cost. If there is no discounts, it can be resolved by shortest path algorithm. It is a non-negative graph. We can use Dijkstra. However, we have need to consider the discounts. A discount can be used in earlier stage and it also to be used in later stage. So we need to track how many discounts left. After a node being explored. we can try to reach it with,if any, and without a discount. That means for each node, we actually have a set of them related to the discount left. So we need another dimension to track the cost. Because you might reach to one node with discount paying less but you might pay more later. E.g. the current cost is 10 and you have one discount but the next toll is 100.  If only the minimum cost without discounts left is recorded, we cannot know whether we are able to use discount next time. The rest is follows the standard Dijkstra. 

the discount left should be added in tracking element

1. when check visited we need check node and discount left
```java
    if (visited[cur.node][cur.discounts]) {
        continue;
    }
```

2. when explore next edge, we need check two cases with and without discount
```java

    for (Info next : adjs[cur.node]) {
        if (visited[next.node][cur.discounts]) {
            continue;
        }
        pq.add(new Info(next.node, cur.toll + next.toll, cur.discounts));
        
        if (cur.discounts > 0 && !visited[next.node][cur.discounts - 1]) {
            pq.add(new Info(next.node, cur.toll + next.toll/2, cur.discounts - 1));
        }
    }
```