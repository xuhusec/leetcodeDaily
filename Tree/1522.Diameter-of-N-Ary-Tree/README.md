### [1522.Diameter-of-N-Ary-Tree](https://leetcode.com/problems/diameter-of-n-ary-tree/)

Given a `root` of an N-ary tree, you need to compute the length of the diameter of the tree.

The diameter of an N-ary tree is the length of the **longest** path between any two nodes in the tree. This path may or may not pass through the root.

*(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)*

#### Solution 1: DFS

This question can be a follow up of the question 543 which asks for diameter of a binary tree. The only difference between this two problem is N-Ary Tree has at most n children instead of only at most 2 children. But the idea is the same, the longest path would need a turning point. And the turning point is the root of the subtree that two segements located. That is because a tree would not have any cycle so the root of the subtree can be the only turing point. And please note any of the two segements can be empty. Those segements are the deepest and the second deepest path. We define the depth as the number of nodes from a given node to its farest leaf. 

So we can write a function to calulate the depth of each node. You can record its somewhere and calculate the longest path with another dfs. However, we can record the longest path along the way. For any given node, get the two max depth of its children + 1. That is the number of nodes in the longest path. Please note it may not be the root. Say a node with two children with depth 10 and 10. The longest path is 10 + 10 + 1 = 21. But its depth is only 1 + 10 = 11. Then let's say it is just a children of root and it is the longest path but the max depth among others is only 5. So the longest path through the root is 11 + 5 + 1 = 16 and 16 < 21. So we need to record the max along the way.

To find the deepest and the second deepest depth from all children, you can record them and sort. Please note there might not be always 2 or more nodes. Please handle where no child, one child and two or more children. But it is not neccesary, we can perform O(1) to get the those two. Keep two variable for max and second max. If we found a depth > max, we assign the current max to the second manx and assign newly found depth to the max. Otherwise if we found a depth > second max, we assign the the newly found depth to the second max.

In the end, the question asks from the number of edges which is number of nodes - 1 (fully connected with no cycle)