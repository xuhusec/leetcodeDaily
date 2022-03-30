### [2189. Number of Ways to Build House of Cards](https://leetcode.com/problems/number-of-ways-to-build-house-of-cards/)

You are given an integer `n` representing the number of playing cards you have. A **house of cards** meets the following conditions:

* A **house of cards** consists of one or more rows of **triangles** and horizontal cards.
* **Triangles** are created by leaning two cards against each other.
* One card must be placed horizontally between **all adjacent** triangles in a row.
* Any triangle on a row higher than the first must be placed on a horizontal card from the previous row.
* Each triangle is placed in the leftmost available spot in the row.

Return the number of **distinct house of cards** you can build using ***all*** `n` cards. Two houses of cards are considered distinct if there exists a row where the two houses contain a different number of cards.

#### Solution 1: DP

In the beginning, there might not be many clues for this question. Let check some basics. If a row contains k triangles, how many cards do you need? It would 2 * k for making left side and right side and k - 1 cards to connect between k triangles on the top. That is 3 * k - 1. So we can calculate number of cards needed for one triangle, two triangels, three triangels, ... etc. Also, it says in the statement, the lower level should contain more triangle than the higher level. So the problem becomes number of ways to picks a non-empty subsent of the number of cards sequence such that their total is n. It is a subsequence because for each level number of card is strictly mono-increasing.

After converting this prioblem to searching groups of subsequence of which the sum is n, we can resolve this by dfs + memo or dp kinds of knapsack problem. there is no need to consider number of triangles greater than n, the max number of triangle of items to be considerred is (n+1)/3, concluding from 3 * k - 1 = n. This can be precomputed. Let say it is the cardNeeded array. Then, the job is to pick a non-empty set such that the sum of cards is n.

We can define dp[i][j] as number of ways to build house of cards containing i triangles with j cards. We can build card without the ith triangle or with the ith triangle. If we do not use ith triangle, we need to check number of ways to build with base/ the first row with one of the first (i - 1) triangles. That is `dp[i-1][j]`. If we do consider the ith triangle, it must be the first row because it contains the most cards in the first i triangles. Then we can check how many the rest can build. It is build with first (i-1) triangles with j - cardNeeded[i] cards, i.e. `dp[i-1][j-cardNeeded[i]]`.  We can simply combine them.

`dp[i][j] = dp[i-1][j] + dp[i-1][j-cardNeeded[i]]`

Let us check the boundary conditions. If j - cardNeed[i] < 0, that means build house with negative number of cards that is not possible, `dp[i-1][j-cardNeed[i]]` should be 0. Also, we can see if we do not have any initial value the computation should stays with 0s from one to another. Let's check with 0 triangles. the "empty" triangle can be build with 0 cards. so `dp[0][0] = 1`. For other dp[0], it is nonsense for the "empty" triangle, so they would stay 0. This setup also solve some special cases such as n = 1. No triangle is formed by only 1 card so it is zero. please note dp[1] is actually the first triangle. we should shift 1 `cardNeeded[i-1]`.