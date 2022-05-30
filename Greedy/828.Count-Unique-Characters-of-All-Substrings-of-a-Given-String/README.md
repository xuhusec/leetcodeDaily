### [828.Count-Unique-Characters-of-All-Substrings-of-a-Given-String](https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/)

#### Solution 1: Greedy

This problem is very similar to LC 2262. While LC 2262 counts substrings with multiple same characters but this one only considers substring with unique characters. A brute force solution would try to list all the possible substring and count the unique characters for each substring. That would be O(n<sup>3</sup>) (iterator over all substrings * iterator over characters in a substring). It would timeout. 

So we need another method. Instead of check substring. We can think reversely. We can check how many substrings a character can be unqiue. The sum of that over all characters is the answer we want. There is no other same character in the subtring. The number of substring would be (cur position - previous position) * (next position - cur position). Here we do not add -1 for both, which would be (cur position - previous position - 1), (next position - cur position - 1) ,  because taking no prefix and no suffix are also valid. For example, we have "abc". For 'b', "ab", "b", "bc" are all valid.

So, we can have an array of list to record the positions of each character in the string. The can be preprocessed. We can initialize the pre position to -1 to make sure (cur position - prev position) is correct to count all the characters before a character without duplicates especially the first one. And for the last segement, we can set the next position to n  at the end to count all the possible tailing section. Each round,  (cur position - previous position) * (next position - cur position) is added to the answer and prevous position is updated.

```java

        for (List<Integer> l : lists) {
            // skip if there is no such character
            if (l.isEmpty()) {
                continue;
            }
            Iterator<Integer> it = l.iterator();
            // track leading segement for the first occurance of the current character
            int prev = -1;
            int cur = it.next();
            
            while (it.hasNext()) {
                int next = it.next();

                // logic here ...
                ...
                
                prev = cur;
                cur = next
            }
            // add the tailing segment for the last occurance of the current character
            ans += (cur - prev) * (n - cur);
        }
```
The total time complexity is O(n)
