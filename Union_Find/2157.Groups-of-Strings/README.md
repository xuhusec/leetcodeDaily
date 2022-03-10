### 2157. Groups of Strings

#### Solution 1: UnionFind + BitMask + Nest Loop (Time out)

This solution would time out but it gives basic idea for improve method.
The questions asks about connection. This is ususally resolved by UnionFind or DFS. And UnionFind provides almost contant time complexity. Let's try UnionFind.

It also says no letter occurs more than once in any string and in constraints section it said lowercase English letters only aka 26 cases. Also, in the connectivity section the ordr of character does not affect the result. So we can use bit mask to represent the string. each bit for a character.

So we can compare two bit masks of the string. For the connectivity rules 1 and 2, they actually the same. Because if you can find a deleted case e.g. A deleted a character to form B. Then B adds that character would A. So we can just test either deleted or added. How to test this with bit mask? Note each letter would be 1 000..00. There is only one 1. So a single english character is presented by power of 2. That means if common character was removed and the left is power of 2, those two string satisfy rule 1 and rule 2. There is actually a catch. If the two string is the same. after common characters was removed it is 0. The famous method x & (x - 1) would consider it is a power of 2 but it is not true. However, in rule 3. it is said replacing nothing is a valid case.
To remove the common characters, one can try x ^ y. And check the deletion, it checks (x & (x - 1)) == 0

For replacement, x ^ y would give a number with 2 one bits only. (x & (x - 1)) removes the least significant bit. We can test the statement twice to see if there only two bits. int y = x & (x - 1); And test y & (y - 1) == 0. However, if one string contains two more character than the other. It would give false positive. To remove the falso positive, we can test above only when the length of two string are the same.

We can preprocess the mask and record the length and running the nest loop to union.

To get the size of the largest group, we can record rank in UF where rank is number of members in a group. With an addtional loop to how many different parents in the uf. That is also the number of groups. And the largest ranks of them  is the size of the largest group

This is a O(n<sup>2</sup>) So with n = 2 * 10<sup>4</sup>. This solution would timeout

#### Solution 2: UnionFind + BitMask + Improved nested loop

In the above method the time complexity is O(n<sup>2</sup>) because the nested loop runs O(n). Is there a way to reduce the complexity? Let's check the statement again. It says each word only contains at most 26 lower case English letters and the letter only appears no more than 1 once in any word. So instead of compare different words. We can try to derive other word from a given word. For deleted or added, we only need to investigate one (either deleted or added), that costs a constant O(26) = O(1). For replace, we can try to delete a character and add a new character that costs 26 * 26 = 676 operations. So the time complexity from O(n<sup>2</sup>)  to O(676n). Also the replace part can be improved by separating the characters into 2 groups for each word. One is in the word and the other is not. Then we can have a nested loop of those two list instead of trying 26 * 26 every time.

The algorithm is following
1. calculate the mask of each word and create a map with mask as the key and index as the value. (As we would use UnionFind, it is OK to only keep one of the id for those have the same mask. This map tells if the parent of the group in the unionFind)
2. loop the words again, for each word go though all 26 bits
  1. check deleted case. See if the word can connect to any other word by comparing the deleted mask and the recorded mask in the group
  2. separate the ids in the word and the ids not in the word.
3. (2.1) checks rule 1, and we need to check rule 3
  1. check if we do not replace any character if it can be connected to other string
  2. nest loop of ids in the word and ids does not and see if the result is a known mask
The rest is the same, we need to go over the union find to figure out number of groups and the size of largest group. (Note one must run find() instead of check the parents array directly because some path might not be compressed yet)

#### Solution 3: UnionFind + BitMask + single loop

It is possible to remove the nest loop of 676(26 * 26)? Let us check the rule 3 again. It says replace of one character. This is equivalent to say after deleting one character in one string and deleting another in the other string would form the same string. So we only need to remove each character when check a word and save its masks in the map and perform union find accordingly. With Rule 1 and Rule 2, we also need to store the orignal mask in the map and perform union find accordingly. That's avoid a loop of 26 so the loop of 676 is reduced to loop of 26.

1. loop the words array
2. calculate the mask of the current word. If the mask is seen in the map, perform union find. Otherwise, put it as the key in the map and its index as the value
3. try delete character one in a loop
4. check if the mask of deleted string is seen, If yes, perform union find, otherwise, put it as the key and the index of the original string as the value in map
5. generate group size and size of the largest group as before

[Leetcode Link](https://leetcode.com/problems/groups-of-strings/)