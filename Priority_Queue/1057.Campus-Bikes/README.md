### [1057. Campus Bikes](https://leetcode.com/problems/campus-bikes/)

#### Solution 1: Sorting

We can just follow the requirement in the problem.
 there are multiple (worker<sub>i</sub>, bike<sub>j</sub>) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index. If there are multiple ways to do that, we choose the pair with the smallest bike index. Repeat this process until there are no available workers.

We can have a dedicated class or an int array with length 3 to record distance between the worker and bike and the worker id and the bike id. Do this for all the workers and bikes and put them into a List. We can sort them with the following

```java
// the list is List<int[]> for each int[] it composed by manhattan dist, worker id and bike id.
Collections.sort(list, (a, b) -> (a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]));
```
Note, the list would be RandomAccess like ArrayList to make this sort in O(nlogn). Otherwise it might be O(n<sup>2</sup>).

Then we go over the sorted list and maintain a usedBikes array to avoid assigning the same bike twice and a satisfiedWorker array to avoid give two or more bikes to the worker. Then while iterating the array, if we found both worker and bike are not assigned, this is our next candidate.

Note, replacing RandomAccess list with PriorityQueue without modification would be time out. This is because although they have the same time complexity for sorting the array but when element out of the queue. PQ would be O(logn) but for list it is O(1).

The time complexity is O(MN);

#### Solution 2: PriorityQueue

We can do better for O(MN). Actually this problem is a variation of merge n sorted linkedList. The only difference is that we only need the pick m elements with some requirement.

So we can select the closest bike of each worker and put them into the Priority Queue. 
Then we start poll bike from the priority queue. If the bike is not used, we would assign the bike to the worker.
If the bike has been assigned, we find closest bike from the rest for that worker

To find the closest bike would require checking all the distance. We can preprocess the distance to avoid repeat computing.

I tried to use Deque array (save the distance and bike id in an array list, sort and store all elements in LinkedList) instead of int array to achieve O(1) pick up but the int array perform much better.
