### [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/)

#### Solution 1: Dijkstra

Similar to 2093, we cannot just think about the shortest path. In this problem, the restriction is at most k stops before reaching the destination. That means one can take at most k + 1 flights. One might reach the destination with the lowest cost but take more than k + 1 flights. Also there is no need to consider the intermediate states that one already take more than k + 1 flights before reaching the destination. So we need to track two states, the cost and the flights taken. Another dimension is needed to keep the restraints that at most k + 1 flights can be taken. And the state at a given stop with more cost but less flights would still be considered because more flights might lead to violation of the k stops rule. For example, you might reach the stop 2 with cost 5 and 3 flights but you can also get there with cost 10 and 1 flights. After stop 2, you need to take 2 flights to the destination. If you can only take at most 4 flights. There is no way for the first case to reach the destination because 3 + 2 = 5 > 4 even though it cost less. However, you can reach the destination with the 2nd plan since 1 + 2 = 3 < 4. Even though it might not be the chipest but it is feasible. So we need to record the shortest path for (node, flights taken).

After adding the second dimension, it is only a one source shortest path problem with non-negative costs. We only start with src and 0 flights taken. So it is a single source problem and the prices cannot be negative. We can apply Dijkstra. When consider the destination, we just return what we can first from the priority queue as long as less than k + 1 flights are taken because that must be the shortest among all plans given the k stop restriction.

#### Solution 2: DP/[Bellman-Ford](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm)

As analysis above, we need take flights taken as a deminsion. Let's define the state dp[i][j] is the shortest path from source to j with less than or equals to i flights taken. Each time, we can try to find another "shortcut" (in terms of cost) with one addition flights. 

Intially, a large number was put to all the flights except the source because the passenger is already there so the cost is 0. You can also put -1 but that requires to check if dp[i-1][j] is -1 before consider the shortest path. -1 would be more general with large costs and Integer.MAX_VALUE/2 would be convenient. Integer.MAX_VALUE/2 was used to prevent overflow when it is added with another integer.

In the following, we need to repeat to check if we can find a shorter route between the source and the node j. You take at most k + 1 flights so it loop from 1 to k + 1(included). For the nested loop, you can construct the adjcent list like Dijkstra and with a nest loop (node + adjcent list 3 nested loops in total). However, a path only exists when there is a flight. So we can actually loop the flights (2 nested loop in total) instead of the nest loop because they are doing the same thing and we do not need to generate the adjcent list. Because my definition here is the path less than or equal to i flights so I also get the previous minimum before the nest loop on the flight. 
 `dp[i][flight destination] = Math.min(dp[i][flight destination], dp[i-1][flight source] + flight price);`

The above process is call relaxtion. It must loop the number of flights taken before loop the flights because taking one flights k + 1 times does not help the minimization. This algorithm is call Bellman-Ford. In a general setting, one took at most v - 1 flights to the destination where v is the number of nodes. That is because one at most need to visiting all other nodes before reach the destination. That would give the answer to the shortest path problem without restiction on stops. Bellman-Ford can also deal with negative edges without negative cycles. In a complete implementation, it can also detect the negative cycles if exists. For that case, there is no shortest path because you can lower the cost by taking the loop of the negative cycles as many times as you want.

You can also define the status dp[i][j] as is the shortest path from source to j with exact i flights taken. In that way, you might need to loop dp[i][dst] for all i because you can get the destination with less than i flights but you can skip the `dp[i][j] = dp[i-1][j] for all j` before the second nested loop.