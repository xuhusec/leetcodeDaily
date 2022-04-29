### [2250.Count-Number-of-Rectangles-Containing-Each-Point](https://leetcode.com/problems/count-number-of-rectangles-containing-each-point/)

#### Solution 1: Greedy (Sort + bucket Sort)

The question has two dimension. We can sort one dimension first to reduce the complexity. With one dimension sorted, how can we check the other dimension. For this problem, if the top - right point of a rectangle has both coordinates greater than or equal to the coordinates of the point, we need count it. From sorting, we can make sure one dimension is greater than or equal to the coordinate of a point in that dimension. How about the other. With sorted dimension, we have two ways to make sure we count satisfied rectangles. 1) binary search. 2) we sort points as well. Then we can use two pointers one in rectangle array and one in the point array. We can keep move the pointer in rectangle array toward to 0 as long as its coordinates >= the coordinate in the point. All the passed rectangle are satified in that deminsion. Binary search does not provide anything in the other dimension. Let us take a look of the constraint. For y, the max is only 100. We can use a bucket to record the height of y along with the the pointer moving. Because it is only 100, we can go though the array and find the count of rectanlge of which h<sub>i</sub> >= y<sub>i'</sub>. Now, the sorting make sure we have l<sub>i</sub> >= x<sub>i'</sub>. So the count of h<sub>i</sub> >= y<sub>i'</sub> is the answer for the i' elenment in the returned array.

Note,
1. we need to move the pointer until i < 0  or rectangles[i][0] < points[j][0] and count the cntH array along the way.
2. We need to sort points but the index should be kept so that we can put the count in correct position in the returned array

#### Solution 2: Greedy (Sort + BIT)

The idea is the same as above. But we can use BIT/Fenwick Tree instead of brute force count number of satisifed hi. The reduce O(maxH) to O(log(maxH)). Although the space complexity may still not allow it deal with a very large maxH. But it is definitely a improve of Solutin one.


#### Solution 3: 2D diff Array

Is there a way does not rely on maxH? We can use 2D diff. The idea is two get the unqiue x coordinates and y coordinates respectively. We can make a 2D diff array based on the count of unqiue x and the count of unique y. Originally 2D diff Array still work with maxX and maxY. But the middle value is not important to us. we can just ignore them to save space. With this 2D diff array. We can add 1 to all the entries in the region it convers by one set 4 points (We need two maps to mapping coordinates to the id in the 2D diff array). After we done, we can fill the count by 2D sum of the 2D diff array.

Then we can perform binary search to find the minimum l<sub>i</sub> such that l<sub>i</sub> >= x<sub>i'</sub> and  the minimum h<sub>i</sub> such that h<sub>i</sub> >= y<sub>i'</sub>. it's value in the sum array is the answer.
