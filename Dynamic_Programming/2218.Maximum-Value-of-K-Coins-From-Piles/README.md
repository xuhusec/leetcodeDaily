### [2218. Maximum Value of K Coins From Piles](https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/)

#### Solution 1: DFS + Memo

Let's first resolve this problem by DFS. we can try to take 0 to k coins from every pile if possible and take the rest from the piles after it. When we get enough coins we can return the value and when we take from all piles but there are still coins should be taken we return the Integer.MIN_VALUE in case this one be selected. To be easy memorized, we return value bottom up. It would be hard if you pass the current sum in the input and return the sum in the end. It would be easy if we return the maximum value get from rest of piles after some coins are already taken.

```java
private int dfs(List<List<Integer>> piles, int id, int left) {
        if (left == 0) {
            return 0;
        }

        if (id == piles.size()) {
            // left > 0 but no more to take
            return Integer.MIN_VALUE;
        }


        int ans = dfs(piles, id + 1, left);
        int sum = 0;
        Iterator<Integer> it = piles.get(id).iterator();
        for (int i = 1; i <= left && it.hasNext(); ++i) {
            sum += it.next();
            ans = Math.max(ans, sum + dfs(piles, id + 1, left - i));
        }
        return ans; 
    }
```

the above function has two variable: id and left (coin left to take). so we can define the memo 2D-array cache. when cache is 0, that means it is not checked otherwise you can return it directly. Because we always can take one coin, so 0 can be repesent not taken.

#### Solution 2: DP

we can define the dp function dp[i][j] as the maximum value choosing j coins from the first i piles. For ease of implementing, we set dp[0] for not chosing any piles and dp[x][0] for not choosing any coins from pile x. They should be 0 because of no coins are taken. And then, for dp[i][j], it only denpends on dp[i-1]. we can take 0 to j coins from pile i and get the rest from first i - 1 piles. 

`dp[i][j] = Math.max(dp[i][j], dp[i][j-z] + 'sum of first z coins from the ith pile'`

sum of first z coins from the ith pile would be used a lot, so we can preprocess presum  array to reduce the complexity. 
```java
        for (int i = 1; i <= piles.size(); ++i) {
            List<Integer> pile = piles.get(i - 1);
            int[] preSum = new int[Math.min(k + 1, pile.size() + 1)];
            for (int j = 1; j < preSum.length; ++j) {
                preSum[j] = preSum[j - 1] + pile.get(j - 1); 
            }

            for (int j = 1; j <= k; ++j) {
                for (int z = 0; z < Math.min(j + 1, preSum.length); ++z) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-z] + preSum[z]);
                }
            }
        }
```

Time complexity: it seems the three nested loop would be O(n * k * k). For this problem n <= 1000 and k <= 2000. It might exceed the time limit. However, the loop of z is to take first k coins from the pile if any. Let's relax it to the all the coins from the ith pile. the j-loop and z-loop would be O(k * num of coins on ith pile). num of coins on the ith pile * the i-loop. we would get total number of coins. In total, it si O(k * sum of coins). The sum of coins is also <= 2000.