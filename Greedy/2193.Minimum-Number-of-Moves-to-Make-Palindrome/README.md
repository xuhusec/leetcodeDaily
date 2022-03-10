### 2193. Minimum Number of Moves to Make Palindrome

#### Solution 1: Greedy

This question is the same as [ENCD12](https://www.codechef.com/problems/ENCD12)

The questions said the input string can form a palindrome. There are two kind of palindrome. 1) even length => no odd cnt of character 2) odd length = > contains a odd cnt of character and one of it should be put in the middle.

Let's check the even-length string first.

For even length, how do we find which characters to move the the boundary first? we have the following case.

1 should we move the outter character to the begining or the inner.  
```
 * ... * x * ... * y * ... * y * ... * x * ... *
   a         b         c          d         e 
```
Let's say
1. a characters before the first x
2. b characters between the first x and the first y
3. c characters between the first y and the second y
4. d characters between the second y and the second x
5. e characters after the second x

Note it does not count x's and y's in above. E.g. before the first y. there is a + 1 (the first x) + b characters

For this case, move x's to the boundary is better to move y's to the boundary. Move x's to boundary would be  a + e moves and Move y's would be a + 1 + b + d + 1 + e = a + b + d + e + 2 moves. So move x's are always better because even if a == b == d == e == 0. the second statement is 2 and it is still > 0 which is the cost of moving x's. Not only it is better at this stage, but also helps any y's left in the rest string. Because x's are moved to the boundary, we only need to form palindrome within two x's. So move ANY y's would be a + b and d + e. That is a + b + d + e (< a + b + d + e + 2). So it also reduce the cost for the rest characters. So move outter pair of characters is better.

2 how about they are appear in turns
```
 * ... * x * ... * y * ... * x * ... * y * ... *
   a         b         c          d         e 
```

The cost of moving x's to the boundary is (a) + (d + 1 + e). Let's also consider the next character in the final string y. It would cost (a + b) + (e). There is no ones because x's are already moved `x * ... * y * ... * y * ... *x`.   In total, a + d + 1 + e + a + b + e = 2a + 2e + b + d + 1 moves.
Let's check moving y's to the boundary first. It would be (a + 1 + b) + (e) for y's and (a) + (d + e) for x's It is also 2a + 2e + b + d + 1. Since x and y are randomly picked. So for all characters in this case, it would be cost equally to form a palindrom.

3 one kind of character appears first and the other follows
```
 * ... * x * ... * x * ... * y * ... * y * ... *
   a         b         c          d         e 
```
The cost of moving x's to the boundary is (a) + (c + 1 + d + 1 + e). And for y's, (a + b + c ) + (e). In total, it would be 2a + 2c + 2e + b + d + 2. Simliarly, if we move y's first. The cost is (a + 1 + b + 1 + c) + (e) for y's and (a) + (c + d + e) for x's. It is still 2a + 2c + 2e + b + d + 2.  As x and y are picked randomly, it costs equally to move such characters to the boundary to form palindrom.

For all 3 cases, only the first cases requires outter being picked and the other two does not care which character to be placed in the boundary. So move the outter character wins. It could be the first or the last. In ease of implementation, the first character is picked and recursively form the rest of string.

How about the odd-length string?
The odd character should be moved to the middle after the rest part is formed palindrom. This is because in previous discussion. The character picked is moved to the boundary. the rest characters would move less or equally (x's are aleardy at boundary) to form a palindrome. but the single character should be placed in the middle. That means characters moved from one side on the middle to the other side would cost more. E.g

```
FROM
 * mid ... * x * ... * x * . . . * y * ... * y * ... *
     a + 1        b           c          d         e 
 TO    
 * ... * x * ... * x * . mid . * y * ... * y * ... *
    a         b         c + 1          d         e 
```

Now moving x's to the boundaries, it would cost (a) +  (c + 1 + 1 + d + 1 + e) And moving y's would be (a + b + c + 1) + (e). It would be 2a + 2c + 2e + b + d + 4. But in previous analysis, it would be 2a + 2c + 2e + b + d + 2. And move mid in the begining is (a + b + c + d + e)/2
So if we ignore mid in the first beginging and than move mid to the middle, it would be better.

```
 * mid ... * x * ... * x * . . . * y * ... * y * ... *
     a + 1        b           c          d         e 
IGNORE mid   
 * mid [... * x * ... * x * . . . * y * ... * y * ... *]
         a         b         c + 1          d       e 
IGNORE mid   
mid [x y ... ... ... ... ... y x]
MOVE mid to the middle
xy ...  ... mid ... ... yx
```
If we ignore mid, it would be cost 2a + 2c + 2e + b + d + 2 and move mid to the middle is still (a + b + c + d + e)/2. This solution is better in this case than moving it in the begining. And for `... * x * ... * y * . mid . * y * ... * x * ... *` or `... * x * ... * y * . mid . * x * ... * y * ... *`, it performs the same as moving it to the middle first.

With the above, we have our algorithm
1. check the first character in the left and set the right boundary at the end of the string.
2. search backward until a match of first character is found or reach the left index.
3. two cases:
  1. if a character is found, move that charater to the right boundary and move left boundary and right boundary toward to the middle and record the cost
  2. if it is not found or reaches the left index. Keep it is and only move left boudnary and finish the rest string first. Since we only need the count, we can add half the length of the rest string to the cost and than move the left index. 
4. repeat until left index meets the right.
The time complexity is O(N<sup>2</sup>)

#### Solution 2: Greedy + reverse pair(Merge Sort)

We can improve the above solution by avoid moving character.

With Analysis of Solution 1. We know that 
1. we need to move to keep the characters from the left as long as it is not moved due to previous operations.
2. If the string is in odd length, one of the odd occurance should be placed in the middle
3. the final result of the second half String is shown in reverse order of the first half.

For the first case, if no character is moved to the second half in previous operations, there is no cost. But if there is, we need to move character from its current position to the vacancy. What such operations do? They move the current investigated character to the vacany and because we are swaping, They also move the previous paired character to the second half. It may not put it to the final position but at least move it to the second half. If we a cnt for how many character is already moved, it would cost i - cnt

For the second case, we just need to move the single character to the left side. However, we should increase the cnt. Because, we still need to move others to that position and as others moved, the single character would be moved to the right and finally it would be in the middle. So the only steps need to count is to move it to the end of the left side. This is the same as case 1. That is i - cnt. They only difference is that we do not increase the cnt because the single character would not be kept there. This is consistent with Solution 1. In Solution 1, when we reach the single character, we move it to the end of current arranged left side and continue work on the rest of string. But note, int that case, the all the second characters in the left that matched are already moved to the right. In this solution, when we move the single character to the end of left side. We also move those matched second characters to the rest. And in Solution 1, although the single character is kept until others matched. 
So those first characters in the rest would move one less than the current solution but when the single character would be moved to the center later, they need to move again for one more time. So the total is equal.

For the third case, as following 1 and 2. the second half can be placed in its final status. i.e. once a case 1 is found, the index of second character in the pair is placed in the rightmost unset postion. the steps of moving them after push them to the second half via "bubble sort" algorithm is the number of reverse pair. As following case 1, character is moved from the left side to the right. However, their relative order does not change. The minimum cost to sort them is the number of reverse pairs. That can be resolved by merge sort, BIT etc.

As mentioned above, we need to find the pair. So we can use Deque for each character in this way. The first and last untouched indices of a character can be found quickly.

Why this is correct?

As mentioned in the first solution

We can keep the left side as much as possible. After moving the paired character to the second half or right side. Moving them to final placed is costs (+ cost moving them to the right side) the same as the way in solution 1 because the relative order does not change. For the single character, unlike the previous solution, it is placed at the end of the left side at that time. In that case, it does not add cost to the future operation because the next character would move the cnt position anyway.

[Leetcode Link](https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/)