### [687.Longest-Univalue-Path](https://leetcode.com/problems/longest-univalue-path)

#### Solution 1: DFS

The question is similiar to LC 543 but it asks for the all nodes on the path should be with the same value. So, we can think the same as 543. The longest path should have a turning point and that is the root of a subtree because tree does not contains cycle. And the any of the two segements can be empty. The only difference from 543 is we need to make sure the all values in a path should be the same. So we only count the path when the value of a child matches its parent. Otherwise, we view it as a null child with length 0.

The question also asks for number of edges connecting the longest unique value path. So we need return number of nodes - 1. But there is a catch here. Because there can be no node at all. That would gives 0 - 1 = -1. Clearly, it should 0. We can return this case early.