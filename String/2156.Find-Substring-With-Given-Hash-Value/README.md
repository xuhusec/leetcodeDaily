### [2156. Find Substring With Given Hash Value](https://leetcode.com/problems/find-substring-with-given-hash-value/)

#### Solution 1: Rabin–Karp algorithm

The equation stated in the problem is a reversed rabin-karp hash function. In rabin-karp. The value of the leading element is multiplied by the p<sup>k-1</sup>. But in this problem it is multiplied by p<sup>0</sup> or 1. The rolling hash of [robin-karp algorithm](https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm#Hash_function_used) requires the reverse definition. So, we can travel backward. We can start from the end and travel torward the begining. In my implementation, I used two loops. One is from s.length() - 1 to s.length() - k. That is used to establish the inital hash and (p<sup>k</sup>) % modulo which would be used later. And the other loop is from s.length() - k - 1 to 0. That only apply the rolling hash function to get the new hash from the old one by O(1). Of course, you can combine them with one loop and if-statement.

Once we know the previous hash. Let's say h = a * p<sup>k - 1</sup> + b * p<sup>k - 2</sup> + ... + k * p<sup>0</sup>. For the next hash, h', we have h' = b * p<sup>k - 1</sup> + ... + k * p<sup>1</sup> + l * p<sup>0</sup>. so  h' = (h * p - a * p<sup>k</sup>) * p + l; That is how we can get h' from h in O(1).

Some notes:

The question is asking for the first satisfied substring. Why not search from beginning from left to right. Similiarly, we would have h' = (h - s[i - k] ) / p + s[i] * p<sup>k - 1</sup> and we can stop early. However, it is correct without modulo math. In modulo math, (a/b) % m != (a % m)/(b % m). It requires [modular multiplicative inverse](https://en.wikipedia.org/wiki/Modular_multiplicative_inverse) to make sure it is correct and it is harder to compute the multiplicate inverse than travese backward. So we sacrifice stopping early for simplicity of rolling hash.

In the implementation stated in wiki. The rolling hash function is `h = [( h + modulo - s[i - k] * highest) * p + s[i] ] % modulo` where the highest is (p<sup>k-1</sup>) % modulo. But there would be a catch in this question. Normally, the modulo is not closed to p. But in this problem, there is no such constraint. So the `( h + modulo - s[i - k] * highest)` can still be negative even though there is a `+ modulo` because `s[i - k] * highest` might be very large. In this case, you need to make sure `( h + modulo - s[i - k] * highest)` to be converted to a positive number before multiplied by p again. Here is an example of this implementation.

```java
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        final int N = s.length();
        final int DIFF = 'a' - 1;
        // set hash with the last char so that the loop would give highest p^k-1.
        // note the modulo could be very small such as 1. So even though only one element for this operation, it still needs % modulo.
        long hash = (s.charAt(N - 1) - DIFF) % modulo;
        long highest = 1;

        for (int i = N - 2; i >= N - k; --i) {
            hash = (hash * power + (s.charAt(i) - DIFF)) % modulo;
            highest = (highest * power) % modulo; 
        }
        int start = -1;
        if (hash == hashValue) {
            start = N - k;
        }

        for (int i = N - k - 1; i >= 0; --i) {
            // make sure (hash +  modulo - (s.charAt(i + k) - DIFF) * highest) is positive.
            // (s.charAt(i + k) - DIFF) * highest can be very large. So we can % modulo and than convert the negative to positive.
            // otherwise, you need while-statement instead of if-statement.
            hash = (hash +  modulo - (s.charAt(i + k) - DIFF) * highest) % modulo;
            if (hash < 0) {
                hash += modulo;
            }
            hash = (hash * power + (s.charAt(i) - DIFF)) % modulo;
 
            if (hash == hashValue) {
                start = i;
            }
        }


        return start > -1 ? s.substring(start, start + k) : "";
    }
```