### [1201.Ugly-Number-III](https://leetcode.com/problems/ugly-number-iii/)

#### Solution 1: binary search

It is very hard to find a way resolve it directly. Both greedy and dp seems not working. However, for a given number, it would be easy to count how many ugly number less than or equal to it. This can be done via [Inclusion–exclusion principle](https://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle). We can first count how many numbers can be divied by each of the three. And numbers are divided by two of the three are added twice so we need to remove them. Then numbers are divided by all three are substracted twice, we need add them back. So we have `cnt = m/a + m/b + m/c - m/ lcm(a,b) - m / lcm(b, c) - m /lcm(a, c) + m / lcm(a, b, c)`. Clearly, the ugly number is the minimum number such that cnt == n. For example ` n = 3, a = 2, b = 11, c = 13`. Then answer is 6 (6/2 + 6/11 + 6/13 - 6/ 22 - 6 /143 - 6 / 26 + 6 / 286 = 3). However, for 7, the cnt is also 3 (7/2 + 7/11 + 7/13 - 7/ 22 - 7 /143 - 7 / 26 + 7 / 286 = 3). Clearly, 7 is not the 3rd ugly number and actually it is not a ugly number. The formula also tells that when m increased, cnt is non-decreased. So we can try binary search.

Then, the problem becomes, find the minimum number such that cnt == n. Because it asks for the minimum, we need track hi = mid to keep pushing the upper boundary down. And lo = mid + 1 when cnt < n. the 0 - 1 test tells `int mid = lo + (hi - lo)/2` works well and `hi - (hi - lo) /2` would cause infinite loop. Please note `m/a + m/b + m/c - m/ lcm(a,b) - m / lcm(b, c) - m /lcm(a, c) + m / lcm(a, b, c)` is an Integer but  `m/a + m/b + m/c` might exceed the boundary. So we need set long instead of int.

As you can see, for any m, lcm(a, b), lcm(b, c), lcm(a, c) and lcm(a, b, c) is not changed. So we can preprocess them to avoid duplicate work. Please note there is no guarantee that no duplicates in a, b and c. If two of a, b, c are equal, we only have two number. Let's say a and b,  `cnt = m/a + m/b - m/ lcm(a,b)`. If all three are equal, we only have one number `cnt = m/a`.  So we need to remove duplicates in the divisors.

```java
private long[][] genDivisor(int a, int b, int c) {
        long[][] ans = new long[3][];
        // remove duplicates
        Set<Integer> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        ans[0] = new long[set.size()];
        int id = 0;
        for (int num : set) {
            ans[0][id++] = num;
        }

        if (ans[0].length > 2) {
            //three factors ans[1] = {lcm(a, b), lcm(b, c), lcm{a, c}}, ans[2] = {lcm(a, b, c)}
            ans[1] = new long[]{lcm(ans[0][0], ans[0][1]), lcm(ans[0][1], ans[0][2]), lcm(ans[0][0], ans[0][2])};
            ans[2] = new long[]{lcm(lcm(ans[0][0], ans[0][1]),ans[0][2])};
        } else if (ans[0].length > 1) {
            // two factors ans[1] = {lcm(a, b)}, ans[2] = {}
            ans[1] = new long[]{lcm(ans[0][0], ans[0][1])};
            ans[2] = new long[0];
        } else {
            // only factors ans[1] = {}, ans[2] = {}
            ans[1] = new long[0];
            ans[2] = new long[0];
        }
        return ans;
    }
```

The only thing left is two calculate least common multiple. The least common multiple is equal to the product of two number divided by the greatest common divisor. The greatest common divisor can be found via [Euclidean Algorithm](https://en.wikipedia.org/wiki/Euclidean_algorithm).

```java
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }
```

