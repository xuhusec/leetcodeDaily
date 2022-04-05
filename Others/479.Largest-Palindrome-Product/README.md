### [479. Largest Palindrome Product](https://leetcode.com/problems/largest-palindrome-product/)

#### Solution 1: Brute Force

The question is asking max palindrome product by two n digit number. We can iterate over two n digits numbers or we can iterate over palindrome. If we iterate over two n digt numbers. O(10<sup>n<sup> * 10<sup>n<sup>) = O(10<sup>2n<sup>) It would be timeout. If we iterate over palindrome. The product would be either 2n digits or 2n - 1 digits ( 10<sup>2n<sup> = 10<sup>n<sup> * 10<sup>n<sup> < p < 10<sup>n - 1<sup> * 10<sup>n - 1<sup> = 10<sup>2n - 2<sup>). For palindrome, we only need to iterate over half. For 2n digits case, it is mirror number in n digits (n + n). And 2n - 1 digits case, it is mirror number in n digits except the last digits ((n - 1) + 1 + (n - 1)). For example, Let us check n = 3 and n digits 987. For 2n, it would mirror all digits so we get 987789. For 2n - 1, it would be mirror all digits except the last digit 98789.

Because it asks for the max product, we can iterate from 99..999 to 10..00. i.e the half is from 10<sup>n<sup> - 1 to 10<sup>n - 1<sup>. In terms of 2n case and 2n - 1 case, as you can see 2n case is always greater than 2n - 1 case, so if we can a solution in 2n we do not need to check 2n - 1. Only when there is no solution in 2n case, we can check 2n - 1 case. E.g n = 1. For each palindrom, we need to check if it can be divied by two n digit. I did not find any better way except brute force all cases. we can iterate the larget (10<sup>n<sup> - 1) to the square root of p. This is because we need check p / i so there is no need to check value < square root p because that is what p / i ranged. For this problem, we have to iterate from the max to the square root of p instead of from the lower bound/min to the sqaure root of p. Otherwise, it would be exceeding the time limit. I do not have a clear explaination for now. But the idea is to search the largest palindrome and its square root should be closed to the upper bound. So we need to search from the upper bound. It can be prove that for n is even. 9...90...0...9 would statisfy the requirement. Let n = 2k.  (10<sup>n<sup> - 1) * (10<sup>n<sup> - 10<sup>k<sup> - 1) = (10<sup>k<sup> - 1) * (10<sup>3k<sup> + 1) that is k 9s following by 2k 0s and k 9s again. But I have not found a proof for odd case. 

Ways to construct palindrom

1. appending digits 
```java
    private long genPalindrom(long half, boolean isOdd) {
        long res = half;
        // if odd case 2n - 1, we should skip the last digit
        if (isOdd) {
            half /= 10;
        }
        // appending digit in half backward
        while (half > 0) {
            res = res * 10 + (half % 10);
            half /= 10;
        }
        return res;
    }
```

2. extract digit and construct
```java
    private long genPalindrom(long half, boolean isOdd) {
        int[] arr = new int[20];
        int id = 0;
        while (half > 0) {
            arr[id++] = (int) (half % 10);
            half /= 10;
        }
        long res = 0;
        for (int i = id - 1; i >= 0; --i) {
            res = res * 10 + arr[i];
        }

        if (!isOdd) {
            res = res * 10 + arr[0];
        }

        for (int i = 1; i < id>; ++i) {
            res = res * 10 + arr[i];
        }
        return res;
    }
```
3. String append reverse string
```java
    private long genPalindrom(long half, boolean isOdd) {
        StringBuilder sb = new StringBuilder().append(half);
        StringBuilder secondHalf = new StringBuilder(sb).reverse();
        if (isOdd) {
            sb.setLength(sb.length() - 1);
        }
        return Long.parseLong(sb.append(secondHalf).toString());
    }
```