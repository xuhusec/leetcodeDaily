### 42. Trapping Rain Water

#### Solution 1: Three Pass

Can water be kept at a given position? 
The water can be hold if there is at least one higher bar than the current one on each side (left and right) respectively. If no higher bar on either side of the two, water leaks.
How much water it can hold at a given position?
It depends on the minimum height of the max height on the left side of the position and the max height on the right side of the position

So we can solve this question by three passes. The first two pass record the max height from the left and the max height from t he right. 
```java
left[0] = height[0];
left[i] = Math.max(left[i-1], height[i]);

right[0] = height[N-1];
right[i] = Math.max(right[i+1], height[i]);
```
And the final one compute the difference.
```java
res += Math.min(left[i], right[i]) - height[i];
```

To avoid negative difference in height, the left[i] record the max height from 0 to i (inclusive). And right[i] record the max height from N - 1 to i. In this case, if i is the highest on the left and/or right, we would only get 0.

#### Solution 2: Two Pointer

Can we optimize Solution 1 with less loop? One simple way is to only record the left and set the right as a integer instead of the array and combine the 2nd the 3rd loop by iterating backward.
```java
int right = height[N-1];
for (int i = N - 2; i >= 0; --i) {
    right = Math.max(right, height[i]);
    res += Math.min(left[i], right) - height[i];
}
```

Can we do better with only one loop and constant extra space? 
Note "How much water the it can hold at a given position". It only depends on the smaller of the too max sides on the left and on the right. 
So we can try to iterate from both sides and record the max height on each side. The lower of the two is boundary from the water on its own side because we already know the other side is higher. Whatever between the current position and the other side (where the higher of the two locates), the water can be hold upto the lower hight because the other side can support it.
E.g
```
3     1     2     *     *     *     6 (6 means the bar at this postion is 6 high)

|           |                       |

leftMax    left                   rightMax/right
```

The current height is 2. And the leftMax is 3 and rightMax is 6. What can always reach 3 (leftMax) or holding 1 (3 - 2). This is because even though all those * can be less than 3 (e.g. 1). rightMax 6 is still higher than 3. From the right side of current position, the rightMax would be at least 6 as some "*" might be higher than 6. But since we only care the minimum of the leftMax and rightMax. So it is always 3 because rightMaxCur >= 6 > 3. So we can be sure 3 - 2 = 1 of volume of water can be hold at the current position.
This enables the two pointer solution.
We can move from left and right. Find the leftMax and rightMax. Move the side with smaller max hights toward the other side. and record water being kept.

```java
while (left < right) {
    if (leftMax < rightMax) {
        int cur = height[++left];
        leftMax = Math.max(leftMax, cur);
        res += leftMax - cur;
    } else {
        int cur = height[++right];
        rightMax = Math.max(rightMax, cur);
        res += rightMax - cur;
    }
}
```

#### Solution 3: Stack

From the question "Can water be kept at a given position?" we know it requires both sides have a higher bar than the one in the current position. We can find the clostest higher bar (in terms of the position) on each side respectively. And fill the water to the lower of the height of the two higher bar. And then check if on the direction of the lower bar to see if we can find a higher one. And then we can check how much water can be hold on top of the previous fills.
```
3     1     2     *     *
```

For position 1, the left higher bar is 3 and  the right is 2.  So At pos 1, it can hold (min(3,2) - 1) * (2 - 0 - 1) = 1 of water. The first part is the minimum of two higher bar - the current hight and the second is the range to fill. 
```
3     1     2     3     *
```

If the next element is 3 at pos 3, for 2 both higher bar are 3. it can hold (min(3, 3) - 2) at a postion. And we can 2 to fill. This is because we already fill pos 1 to raise it to the hight 2 in prevous operation. But with the new 3, it can be raised again. So it is (3 - 0 - 1). In total (min(3, 3) - 2) * (3 - 0 - 1) = 2.
```
3     1     2     3     5
```

If the next element is 5, we can not hold any water at pos 3 because it is also 3 at pos 0. No water can be hold. Also because 5 is highest so far, we do not need to check any previous bar.

So once the lower bar is filled with water, we do not need to care its original value. Only the new value plays a role in future and it is the same as the lower of the higher bar. So we need to record the index to calulate the range to fill intead of iterating how many positons to fill. And we only care the clostest higher bar on both side.

Then we can use stack and maintain a mini stack. the mini stack is based on the height but record the index for calculating the range. Why mini stack? because we need to find two higher bar on both side. the mini stack guarantee the cloest left higher bar is record and once we find a new element is higher than the current postion, the other side is found too. Then, the water can be calcualted. Also this element can be throwed out from the stack because in the future only the lower of the two higher bar matters instead of that bar.


In java, If a Collection of Stack such as Deque is used, it might be slow because of auto-boxing of integer. I also provide a simulation of stack by an integer array. And you can see this algorithm is also as fast as the previous two. However, you should not use the later one, it is only to illustrate. The approach is error-prone and it is too specific for integer primative. It only means to illustrate the impact of auto-boxing.

084.Largest-Rectangle-in-Histogram can also be resolved this way but you need to think in reverse.

[Leetcode Link](https://leetcode.com/problems/trapping-rain-water)
