### [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/)

#### Solution 1: Dijkstra + conversion

The probability for a given path is the product of all the probablities on each edge. p = p<sub>e1</sub> * p<sub>e2</sub> * p<sub>e3</sub> .... Let us take log of them because we want to have some function to sum instead of probablities. log(p) = log(p<sub>e1</sub>) + log(p<sub>e2</sub>) + log(p<sub>e3</sub>) + .... Also, it is probability, so every iterm is less than or equal to 0. Maximizing p is to maximizing log(p). Maximizing log(p) is minimizing -log(p). -log(p) is non-negative and it is minizmizing. We can apply Dijkstra for -log(p). Dijkstra is for shortest path from a single node on a non-negative edge graph. After converting p to -log(p). The rest is to apply Dijkstar to minimize the cost on -log(p) graph. Please do not forgot convert it back to p in the end.

#### Solution 2: The idea of Dijkstra

Dijkstra is to find shortest path from a single source to all other nodes on a graph with non-negative edges. Why it works? If the path from source to node A is the shortest one among all nodes connected to discovered node. You cannot find another better path becuase the edge is non-negative and the cost to this node would be greater or equal to the current cost to A because A is the shortest. Let us take a look this problem. The quesition is asking for the probability which is the product of a bunch of probability raning from 0 to 1. When you find a maximum proability after exploring all reachable path, there is no better path because going another route would be at most with probability 1 which would at best to get the same probability. So although the question is asking from maximium probablity, which sounds like a longest path problem. you can still apply the idea of Dijkstra because we found the global best with that procedure.

1. create a PriorityQueue offer largest probablity on the top.
2. set the probabilty of start to 1 and add to the queue.
3. explore all the connected nodes and save the pobabilties and their id to the queue
4. repeat the process ignoring the visited node
5. find the path to the end and stop or return 0.

#### Solution 3: BFS

Try all possible solutions with BFS