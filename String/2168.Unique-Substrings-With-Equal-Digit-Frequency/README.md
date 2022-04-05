### [2168. Unique Substrings With Equal Digit Frequency](https://leetcode.com/problems/unique-substrings-with-equal-digit-frequency/)

Given a digit string s, return the number of unique substrings of s where every digit appears the same number of times. s consists of digits.

#### Solution 1: count array + string set

If we put the uniqueness aside, we can enumerate all the substrings and validate whether a substring satisfies equal digit frequency. When we require the unique string, we can put the valid strings in a set. Then the size of the set is the final answer.

To avoid repeate work. we can fix a side, says the start, and expending the right/end. Along the way, we can keep the cnts array so that we can get the cnts of substring i...j to substring i...j+1 in O(1). For the substring, you can keep a string builder for each start or you can just call s.substring(i, j + 1) once a valid case is found. To valid the equal digit frequency, I have not find a way rather than counting along the cnts array. Please note we should skip the 0 count element.

To improvate the performance a little bit, a max count for each start is recorded. For each round a cnt element would change. If it is less than the max, we do not have a equal digit fequency. Otherwise, there might be a chance. This alone would not determine the equal digit frequency but it can filter a lot of invalid cases.

#### Solution 2: count array + integer set

The Solution 1 works but it is slow. It would not be hard to figure out when we add the string into the set. Its hash is computed from ground 0. Even though, you might have something like "11" and "1122". Computing the hash of "1122" does not use anything from the hash of "11". To improve this part, we can use an encoding integer to replace the string. In this case, the hash function would not need to convert the string into an integer. It would get the integer directly. For substring is the O(n) (n characters) to compute the integer for hash. But our encoding, we can make sure it only cost O(1) from previous encoding. 

It is encoding so we need to make sure everything is unique. The digits is from 0 to 9. We can convert it to the integer. But that would fail the unique requirement with leading 0. So we can shift them by 1 to make an 11-base integer with element from 1 to 10. E.g. "0256791" => 1 * 11<sup>6</sup> + 3 * 11<sup>5</sup> +6 * 11<sup>4</sup> + 7 * 11<sup>3</sup> + 8 * 11<sup>2</sup> + 10 * 11<sup>1</sup> + 2 * 11<sup>0</sup>.  From "025679" to "0256791", we only need multiply the previous encoding by 11 and add the current incremental. encoding("0256791") = encoding("025679") * 11 + 2. That satisfy the O(1) requirement.

For other part, it is the same as the Solution 1.