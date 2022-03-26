### [499. The Maze III](https://leetcode.com/problems/the-maze-iii/)

There is a ball in a `maze` with empty spaces (represented as `0`) and walls (represented as `1`). The ball can go through the empty spaces by rolling **up, down, left or right**, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.

Given the `m x n maze`, the ball's position ball and the hole's position hole, where ball = [ball<sub>row</sub>, ball<sub>col</sub>] and hole = [hole<sub>row</sub>, hole<sub>col</sub>], return a string instructions of all the instructions that the ball should follow to drop in the hole with the **shortest distance** possible. If there are multiple valid instructions, return the **lexicographically minimum** one. If the ball can't drop in the hole, return `"impossible"`.

If there is a way for the ball to drop in the hole, the answer `instructions` should contain the characters `'u'` (i.e., up), `'d'` (i.e., down), `'l'` (i.e., left), and `'r'` (i.e., right).

The **distance** is the number of **empty spaces** traveled by the ball from the start position (excluded) to the destination (included).

You may assume that **the borders of the maze are all walls**.

#### Solution 1: Dijkstra

The question can be a follow up of 505 The Maze II. The ball can only change direction when it hits a wall. This is a single source non-negative cost graph. We can apply Dijkstra. To reach the hole, the ball must from the source or cell adjcent to the wall. Then the ball fall in the hole on the way to another cell adjcent to the wall. So the distance is "distance from source to the adjacent cell to the wall" + "distance from the cell to the hole". When the cell is found, the later is fixed and we need to minimize the first part. That can be done by Dijkstra. The difference is the cost is not given directly. it can be obtained by simulation. And during the simulation, we can check if a ball can be fall in the hole. But we cannot stop when it first hit the hole because our Dijkstra does not optimize the distance to the hole directly. for example,

w 2 0 0 0 0 0 h 0 3 w  
The shortest path to the left cell is 2 and the right cell is 3. 2 < 3 but it take 6 steps rolling from 2 to the hole (h) which costs 2 + 6 = 8. However from 3 it is 3 + 2 = 5 and 5 < 8. But Dijkstra would poll the node with dist 2 before 3.

We need keep explore. When should we stop? When the discovered distance to the hole is less than or equal to the the miniumn distance in the pq. That is because distance found would be always >= the poll the element >= the discovered shortest distance. So it is global minimum.

Note, this problem as for lexicographicall minimum of instruction when there is a tie. So, we need to make pq sort first by distance and then by instruction lexicographical order. 

notes:
1. It can work without the discovered distance to the hole is less than or equal to the the miniumn distance in the pq. but it is better to have.

2. You can also use stop early when the ball stops at the hole (if the hole next to a wall). But at that time the distance polled from the queue might not be the answer because the answer might be found before we hit the wall. We can only make sure the shortest path is found it is one of the scenario of notes 1. Here is an example.


0 0 w 0 0 0   
0 b h 0 0 0  
0 0 0 w 0 0  
0 w 0 0 0 0  

b is ball and h is hole. 
Clearly the shortest dist is 1 when travel to the right. But it would first stop at the hole with d -> r -> u that is 3 steps 

3. The instruction can be composed by "uld" + 'd' instead of StringBuilder append. However, the perforamnce is very bad when "" + '' was used. 

4. I also skip the point which the ball come from. Again, this can be removed but better to have.

5. You can use the static field for the candidate instruction and minimun distance if there is no thread interference
