### [1722. Minimize Hamming Distance After Swap Operations](https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/)

The allowedSwaps enable swapping characters. The statement said you can swap elements at a specific pair of indices multiple times and in any order. That means the results of swapping is all permutations of character in the swapping chain. If there is one pair, you can only swap two characters back and forth. However, there exists another pair and one of the element is also in the previous pair. Then all three elements form a chain. Similarly, you may have four element chain, five element chain etc. 

The minimun Hamming distance would be achieved greedily by pairing the same numbers from the chain to the number at the same position in target string as much as possible. 

So we can identify each chain and record all elements in that chain and try to use them to reduce distance from the target. However, there might be more than one of the same numbers in the chain. We need to record their occurance as well. A Map of numbers and their occurance of a chain should be good.

How do you get those maps? 

Currenlty, I think about two methods.
1. Union Find
2. DFS.

#### Solution 1: UnionFind

Create a UnionFind with size of the source. And go over allowedSwaps to union those indices.

Go over the source array and record the occurance of source[i] to the occurance map of the current chain. And the chain would be identified by the parent of i in the UnionFind.
` map.get(uf.find(i)).merge(source[i], 1, (v1, v2) -> v1 + v2); ` This example all occurance map in another map. The key the index in source array and the value is the occurance map.

Then when compare the target string. Let's check if the number in target appears in the chain. `map.get(uf.find(i)).getOrDefault(target[i], 0) == 0`. If it does not we would have hamming distance increased by 1. If it does, there is no cost at this step but we need to decrease the occurance so that the number would not match others in the target.

#### Solution 2: DFS

For DFS, the idea is similary to union find. However, we do not have the parent of a chain. Instead, we can have a map of map or an array of map for each position. The reference of the same occurance map would be put for element in the chain. As in prevous method the map of map was used. In this example, an array of map is used instead. 

1. create a adjcent Map , `List<Integer>[] graph`, to record indices connected. i.e. for allowedSwaps[i], `graph[allowedSwaps[i][0]].add(allowedSwaps[i][1]);` and `graph[allowedSwaps[i][1]].add(allowedSwaps[i][0]);`.
2. iterate the index of source, run dfs with each index according to the graph. Set the reference of map to all the indices explored within one dfs run and record the occurance of the number in source along the road. Here, I use array of maps. It would be `groups[i] = occuranceMap` (for all i along the road). If the map of map was used. it would be `map.put(i, occuranceMap)` 
3. With the occurance maps, the rest is the same as the UnionFind solution

##### Notes

UnionFind seems fast from my result. However, DFS can also helps in some espcial cases. For example, If this is dynamic question, after some instruction in allowedSwaps was removed, what the minimize hamming distance would be?  For UnionFind, it must run all the instructions in allowedSwaps (after deletion) again. With DFS, we can update the adjcent map, graph, and rerun dfs only on the two indices in the deleted allowedSwaps instruction. Some minor changes are required,
1. keep a deep copy of the array of maps before calculate the hamming distance so that the data can be used after the current calculation
2. the termination condition in the DFS. from `group[i] != null` to `group[i] != map` (map here is the new occurance map). Further, after iterate one of the indices in the instruction. We can just get the other map and minus the occurance from the previous node. Of course, you should make sure they are not connected before the subtract. i.e a <-> b, a <-> c and b <-> c. Even though you delete the instruction of a <-> b, because of c, a is still connected with b.