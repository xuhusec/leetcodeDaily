### [2209. Minimum White Tiles After Covering With Carpets](https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/)

#### Solution 1: DP

To get the minimum white tiles exposed after placing carpets, we can write a backtracing functions. And you would recognize a lot duplication work. We can improve it with dynamic programming. 

As usual, we can get our dp definition from the problem statement. Let's define dp[i][j] as the minimum number of white tiles for the first i tiles after placing j carpets. Now we need to think about how to get dp[i][j] from previous states.

If we place the jth carpet on the ith tiles, it is better to place its right end on the ith tile  because we want to cover as many white tiles as possible. that means the number of white tiles exposed would be the minimum white tiles on the first i - carpetLen tiles with j - 1 carpets aka dp[i-carpetLen][j-1].

If we do not place the jth carpet on the ith tiles, this is the same problem to place j carpets on the first i - 1 tiles + number of white tiles provided by the ith tile. dp[i-1][j] + (isWhiteTile ? 1 : 0).

Those two cases covers all the possibilities and we need the minimum of the two.

`dp[i][j] = Math.min(dp[i-carpetLen][j-1], dp[i-1][j]);`

We need to make sure i-carpetLen >= 0 and j - 1 >= 0. When i <= carpetLen, all the tiles would be covered if there is at least one carpet. So all the white tiles are covered as well. But j < 1 or j == 0, there is no carpet, it is the presum of white tiles.

A small improvement would if carpetLen * numCarpets >= the whole length, all the tiles are covered so are the white tiles only.