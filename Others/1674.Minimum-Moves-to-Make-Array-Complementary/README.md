### [1674.Minimum-Moves-to-Make-Array-Complementary](https://leetcode.com/problems/minimum-moves-to-make-array-complementary)

#### Solution 1: Diff Array

It is hard to think about the answer directly. So let check the reverse way. For a given sum S, how many moves are at least required. Let us check the range. 1 <= nums[i] <= limit <= 10<sup>5</sup>. So the minimum possible solution is 2 when both changed to 1 and the maximum possible solution is 2 * limit when both are changed to limit. And it is possible to change the sum to any number between 2 and 2 * limit as we can change both number form 1 to limit. Let us go back the question. For a given S, how many moves are required. 

Clearly, when S = nums[i] + nums[n - i - 1], for this pair no change needed. 0 moves. When we increase the sum S, it starts need to change the smaller element. let's say the smaller element of the two is lo and the other is hi. If S = lo + hi + 1, we need to change the lo to lo + 1. You can increase hi as well in this case. But since hi might equal to limit. It is safe to increase lo instead of hi. As it continue increase, once it reaches limit + hi + 1. We need to increase hi because there is no way to increase lo alone. Then we have 2 moves until reaching 2 * limit. Similarly, for the decrease process, to reach lo + hi - 1, we need one move by chaning hi to hi - 1. Again, we change hi because it has more capacity than lo. In such case we need one move. Once we reach lo, we need decrease lo as the minimum of hi is 1. So we need to change lo to lo - 1 such that lo - 1 + 1 = lo. Starting from here, we need 2 moves until we reach 2.

In summary, for a given S, we have five change point.

From 2 to lo, we need 2 moves.  
From lo + 1 to lo + hi - 1, we need 1 moves.  
At lo + hi, we need 0 moves  
From lo + hi + 1 to limit + hi, we need 1 moves.  
From limit + hi + 1 to 2 * limit, we need 2 moves.  

The answer is the S such that the sum of moves is minimum. The brute force solution would be find all ranges and try all possible S and figure out the moves and gind the minimum one. Unfortantely, it would time out. And binary search cannot be applied because there is non mono-increasing or mono-decreasing shown.

Let's check the solution for five change points. They are continious within each range and no overlop for a given pair. And we want to find S such that the sum of moves is minimum. Another brute force would add all the moves for every possible point and find the minimium sum of moves. This can be done add 2, 1, 0 to all points from 2 to 2 * limit according to the range. and then go over the range find the smallest sum of moves. So can we figure out the sum of moves in quick way. We can use diff array or line sweep. Instead of go over the range multiple times to acculmate moves. we can use diff array to mark the data change point and delta. The prefix sum at a point is the sum of moves at that point.

So
diff[2] += 2;  
diff[lo + 1]--;  
diff[lo + hi]--;  
diff[lo + hi + 1]++;  
diff[limit + hi + 1]++;  

Please note it deals with overlap cases directly. if lo = 1 and hi = 1, diff[2] = 0 after the first three operations.

The reduce the time complexity to O(N + L) where N is length of the array (actually N/2 as we look for pairs) and L is the limit. O(N) to mark the diff array and O(L) for prefix sum. And the space complexity is O(L). 

In this problem, we can just have a diff Array with length 2 * L (I used 2 * limit + 2 because I do not shift 2 to 0 and the presum iteration starts from 2 to skip impossible value 0 and 1). However when L is large, both time complexity and space complexity would be unaffordable. In that case, we can use map to replace the array. But in this problem using map would be slower because map requires sorting before prefix sum. That costs O(NlogN).

```java
    public int minMoves(int[] nums, int limit) {
        Map<Integer, Integer> map = new HashMap<>();
        final int n = nums.length;
        
        for (int i = 0; i < n/2; ++i) {
            int lo = nums[i];
            int hi = nums[n - i - 1];

            if (lo > hi) {
                var temp = lo;
                lo = hi;
                hi = temp;
            }
            
            map.merge(2, 2, (v1, v2) -> v1 + v2);
            map.merge(lo + 1, -1, (v1, v2) -> v1 + v2);
            map.merge(lo + hi, -1, (v1, v2) -> v1 + v2);
            map.merge(lo + hi + 1, 1, (v1, v2) -> v1 + v2);
            map.merge(limit + hi + 1, 1, (v1, v2) -> v1 + v2);
        }
        //sort by put everything in TreeMap
        map = new TreeMap<>(map);
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            sum += entry.getValue();
            
            ans = Math.min(ans, sum);
        }
        return ans;
    }
```