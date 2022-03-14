### 1627. Graph Connectivity With Threshold

The problem asks for connectivity directly or indirectly. 
If only directly connection is allowed. This would be a easy problem. We only need to count when the greater common divisor is greater than the threshold. For reference, the following is the gcd function.

```java
private gcd(int a, int b) {
    if (a < b) {
        var temp = a;
        a = b;
        b = a;
    }
    return gcdInternal(a, b);
}

private gcdInternal(int a, int b) {
    return a % b == 0 ? b : gcdInternal(b , a % b);
}
```

However this question asks indirectly connection as well. For exanmple, [8, 9], 8 connects to 2, 4, 6 with 2 and 9 connects to 3, 6 with 3. So for threshold == 1, 8 and 9 are connected through 6.

This connection tests fits union find because when two group union by 6 we do not need worry about it comes from 2 or comes from 3. DFS might accomplish this as well. Currently in my mind is to point element to a set and merge set when we found a merge. However, we still need to track all the elements in at least one group and update their reference to the new set. That is time-consumming. The path compression in Uinion Find seems more effective.

To preprocess the input, you might think about have a nest loop to fix an integer and check the rest of integers and see if their gcd is greater than the threshold and union if it is. But that would be O(n<sup>2</sup>). It is not for 10<sup>4</sup>. It would timeout.

Instead, we can try start  i from threshold + 1 and find all the mutiples of the i (at least 2 * i) within n in a nest loop to reduce to complexity.

The outter loop for i would be n. Each time (in the worst case starting from 1) the nested loop would be run 
n/1 + n/2 + n/3 + n/4 + .. n/n = n * (1 + 1/2 + 1/3 + .. + 1/n).

1 + 1/2 + 1/3 + ... + 1/n is called Harmonic series. It is a diverge series but it grows very slow. you can check [here](https://en.wikipedia.org/wiki/Harmonic_series_(mathematics)#Partial_sums). It is with logarithmic growith. So it can be estimated as O(nlogn). That works.

After finding all the groups satisfy the threshold directly or indirectly, the rest would be just test in each query if the two has the common parent or root in the union find.

There is one place can be improved. For the outter loop, any number which is a multiple of previous visited element can be safely ignored. So we can use the idea of [sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) to have a boolean array to record visited number in the inner loop and skip them when the outter loop encounter them again.


[Leetcode Link](https://leetcode.com/problems/graph-connectivity-with-threshold/)