### [866. Prime Palindrome](https://leetcode.com/problems/prime-palindrome/)

#### Solution 1: Brute Force

To get the prime palindrome, we have two choices. 1 try all palindrome and check if the palindrome is a prime. 2 we can try all primes and check if the prime is palindrome. In the given range, the palindrome should be less than the prime. See[Twin_prime_conjecture](https://en.wikipedia.org/wiki/Twin_prime#Twin_prime_conjecture). The idea is to try the odd-digits palindrome and even-digits palindrome and first the first prime palindrome greater than or equal to n. Since the odd-digits palindrome is less than the even-digits palindrome, we can return if we have a odd-digit result.

We can start from the half length digit. That is Math.pow(10, (len - 1) / 2). (len - 1) / 2 can cover both odd and even. For example, if len = 2. we should start from 1. For odd, it is kind of waste because 1 ~ 9 is less than 10 but for even, it would starting from 11. 

However, we exactully do not need to worry about even-digit cases for most cases.

The even digit palindrome can always be divied by 11.

We have 
11 % 11 = 0;
1111 % 11 = 0;
111111 % 11 = 0;
...

And
1001 = (1111 - 11) % 11 = 0;
100001 = (111111 - 1111) % 11 = 0;
...

So abcddcba would be 
(a * 10000001 + b * 100001 + c * 1001 + d * 11) % 11 = 0.

So even-digts palindrome can be alwaysed divided by 11. But 11 itself is a prime palindrome. we need find all cases resulating to 11 and make an exception so that we do not need to worry about 11 any more. That is n >= 8 && n <= 11.

Because we now only need to consider the odd digit case, so we do not need worry about even digit. Then we can start from Math.pow(10, len / 2).

For each palindrome, we can check if it is a prime and if it is greater than or equal to. For prime, we would try from 2 to the square root of it to see if we can find two integer factors.
