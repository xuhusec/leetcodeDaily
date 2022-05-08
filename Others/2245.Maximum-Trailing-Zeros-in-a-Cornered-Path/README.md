### [2245.Maximum-Trailing-Zeros-in-a-Cornered-Path](https://leetcode.com/problems/maximum-trailing-zeros-in-a-cornered-path/)

#### Solution 1: PreSum

The qusetion asks for a path to generate max number of trailing zeros. All the number on the path would be multiplied and it is a cornered path meaning you could only alternate the direction once.

First, to get how many trailing zeros in the end. one could multiplied them and find the trailing zero. However, it is not necessary. We know only 2 * 5 would give trailing zeros. So it the question become how many factor 2 and how many factor 5. If we can write the product as 2<sup>x</sup> * 5 <sup>y</sup> * ..... The minimum of x and y would be the number of tailing zeros. LC 172 is another application of this result but in that problem y is always less than x so we only need keep track of y. But in this problem, y could be greater than x so we need to check both.

For the path, we can address it greedily. Let us make the path reach both ends from the turning point. Even though reaching the end might or might not increase the trailing zero but it would not decrease it. So we do not need worry about if we should stop early.

The problem then become to calculate number factor 2 and factor 5 from up, down, left and right in a given point. That point is the turning point. The max number of traing zero turning at that point is the max of the min total number of factor 2 and that of factor 5.

To quickly get accmulated values in a range, it is better to use presum. Because we only need focus on one direction each time. There is no need for 2D presum. We only need row presum matrix and col presum matrix. left and up would be got from the presum matrix directly and the right and down would need the total in that row/col - the prev presum in that row/col. So we only need two matrices.

To avoid detailing with boundary. I add an additonal row and col in front and filled them with cnt 0's. you can use if statement instead. `rowPresum[i][j] = (j == 0? 0 : rowPresum[i][j-1]) + currentCnt`. Also, we should only cnt the turing cell once. I force it in the vertical direction and move the horizontal range accordingly.