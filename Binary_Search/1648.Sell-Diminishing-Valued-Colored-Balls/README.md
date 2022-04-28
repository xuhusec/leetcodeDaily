### [1648.Sell-Diminishing-Valued-Colored-Balls](https://leetcode.com/problems/sell-diminishing-valued-colored-balls/)

#### Solution 1: Math

As stated, the value of the ball depends on the number of how many balls in the same color left in the inventory.  My first thought is to maintain a PriorityQueue (max heap). Greedily take the balls with max inventory and sell all the balls that more expensive than the rest of the inventory. That means `int n = pq.poll(); int diff = n - (pq.isEmpty() ? 0 : pq.peek();`. If diff is 0, we can decrease it by 1 to break the tie. Then, the `n - diff` back to the pq. Unfortunately , this would exceed the time limit. Why it is slow? After a few loops, all the rest inventory would be the same. And it begins take decrease one by one. That is very slow.

Although it failed, the main idea is in the right route. We need to sell all the balls that are expensive. Let's check the following example. 

```
1 2 3 7 8 9
1 2 3 7 8 8
1 2 3 7 7 7
1 2 3 3 3 3
```

The first round we decrease 9 to 8. In the second round, we have two 8's. They are equally profitable. We can try to sell them together. In this round we sell 2 balls for both 8's. Similiarly, we have three 7's and we should sell all the three 7's to maximize the profit. 

As you can see, in each round, we decrease the max number of balls to the second max. Then in the next round, we should sell that newly added kind of balls as well because it is the one of the balls with max inventory. 

To do so, we can sort the array and keep track how many kinds of balls are with max inventory and decrease them as the whole.

If taken diff balls from all piles are too much (exceeded orders). We need to take from largest to smallest. we can still take `orders / (n - i)` from all piles with max balls. For the left `orders % (n - i)`, taking from all piles with max balls would be too many. So we only need to take one from each piles until we get `orders % (n - i)` balls. As the price is `orders - orders / (n - i)` at that time. We have `orders - orders / (n - i) * (orders % (n - i))` for that part.

#### Solution 2: binary search

Binary search are the same idea. However, unlike most binary search, we would guess the max profit. It is hard to verify a given profit and the return value is remainder after modulo operation so we lost the monotonicity. 

But we can guess the final stage in the previous solution. That means we can guess the max number of balls in any pile after selling. And the total should be <= orders. So we need find the max such that the total number of sold balls is less than or equal to orders. This need to align with our greedy strategy. If the piles contains less balls than the given limit, we keep it and only take balls from those piles contains more. This would give us the max profit.

After figuring out the limit, we can calculate the profit from each pile. And if the total number < orders. That means we have some balls left to pick but the number is less than the current number of piles contains max number of balls. so we only need to pick left balls from the top ball in piles with max counts.