### [1996.The-Number-of-Weak-Characters-in-the-Game](https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/)

#### Solution 1: Greedy (Sort)

The question has two dimension. We can sort one dimension first to reduce the complexity. With one dimension sorted, how can we check the other dimension. For this problem we can just sort attack from high to low. Then we need to take care of the defense. If all attack values are unique, we can just record the highest defense while going through the sorted array. When a small defense value is found, we found a week characters. That is because the attack is already sorted from high to low. If the attack value is unique, the value visit late would has lower attack value than the previous visited. And if we a previous defense value larger than the current one, this character is week. However, the attack can be duplicated. We need to remove the affect of the defense with the same attack value.  In this case, we can sort the defense from low to high when the attck value is the same. In this case, the weaker defenser with the same attck value would be visited first. We will not count the weaker defenser as a weak character when a stronger defenser with the same attack value is met. Then the process is the same as the unique attack value case. 

#### Solution 2: Greedy (Sort + Monotonic Stack)

The idea is the same as the solution one. But we can track the weak characters via the stack. We can sort the array based on attacks from low to high.  And add defense of the character in the stack. Before add that to the stack, we can pop out all the weak defense value. If the attack values are unique. The rest in the stack are those not weaker than the current character. For those with the same attack values, we can use the trick in solution 1. Now, sort the defense from high to low for the characters with same attack value. In that case, the character with the same attack and high defense would not evict those with the same attack and low defense. We can repeat this steps and the total number of people - characters left in the stack. Or you can record how many defense value has been popped out.

```java
    int ans = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int[] prop : properties) {
        while (!stack.isEmpty() && stack.peek() < prop[1]) {
            stack.pop();
            ans++;
        }
        stack.push(prop[1]);
    }
    return ans;
```