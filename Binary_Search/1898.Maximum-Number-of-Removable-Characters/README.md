### [1898.Maximum-Number-of-Removable-Characters](https://leetcode.com/problems/maximum-number-of-removable-characters/)

#### Solution 1: binary search

For such maximum number problem, we can try greedy and DP. But it seems no greedy and DP would be establish on removeable array which seems not provide any optimal substructure. However, we know, if p is a subsequence of s after deleting first k characters indicated by removable from s, p is a subsequence of s after deleting *k-1* sucg characters. So this contains some monotonic properties. This remind me for binary search.

The question asks for maximum, so we need to keep track lo = mid in binary search. Once we have lo = mid, we can see `int mid = lo + (hi - lo)/2` would cause infinite loop. lo = 0, hi = 1 => mid = 0 => lo = mid = 0 .... In this case, we need `int mid = hi - (hi - lo)/2`.

the boundary of hi would be the minimum of removable.length and s.length() - p.length() because removeable contains distinct elements and once s.length() < p.length() it won't contain p as a subsequence.

To check if s after removal is still contains p as a subsequence. Note this is subsequence not substring. We can use double pointer and greedy. We skip the removal character in s and if we found one match we move the pointer in p until we used all characters in s and/or all characters in p. Only when all characters in p are matched, we find the substring. To do so, we can generate a removed boolean array for each mid.

```java
    // note charS = s.toCharArray() and charP = p.toCharArray();
    private boolean isSubStringAfterRemoval(char[] charS, char[] charP, int[] removable, int used) {
        if (used == 0) {
            return true;
        }
        
        boolean[] removed = new boolean[charS.length];
        for (int i = 0; i < used; ++i) {
            removed[removable[i]] = true;
        }
        int idP = 0;
        for (int i = 0; i < charS.length && idP < charP.length; ++i) {
            if (removed[i]) {
                continue;
            }
            if (charS[i] == charP[idP]) {
                idP++;
            }
        } 
        
        return idP == charP.length;
    }
```

Every time, we need to recompute the removed array seems a waste. Can we preprocess the removable so that we do not need to compute the removed array every time? Yes, we can record the order of removable so that check if this apply to current mid.

```java
    private int[] genremoveOrder(int len, int[] removeable);
        int[] removeOrder = new int[s.length()];
        Arrays.fill(removeOrder, Integer.MAX_VALUE);
        for (int i = 0; i < removable.length; ++i) {
            removeOrder[removable[i]] = i;
        }
        return removeOrder;
    }
    // this time the removed is number of element to be removed i.e. mid
    private boolean isSubStringAfterRemoval(char[] charS, char[] charP, int[] removeOrder, int removed) {
        int idP = 0;
        for (int i = 0; i < charS.length && idP < charP.length; ++i) {
            if (removeOrder[i] < removed) {
                continue;
            }
            if (charS[i] == charP[idP]) {
                idP++;
            }
        }
        return idP == charP.length;
    }
```

Some notes:
For removable.length << s.length(), we can consider Map< Integer, Integer > instead of int array. If you do not want to check null, We can just use `map.getOrDefault(i, Integer.MAX_VALUE) < removed`; 

If removable contains duplicates, we only keep the earliest index.
```java
   if (removeOrder[removable[i]] == Integer.MAX_VALUE) {
       removeOrder[removable[i]] = i;
   }
   // or in map
   map.putIfAbsent(removable[i], i);
```
Also, if there are duplicates, we do not have hi = Math.min(s.length() - p.length(), removable.length) any more. It is only hi = removable.length;