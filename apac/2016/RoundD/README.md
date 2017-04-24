# APAC 2016 Round D
https://code.google.com/codejam/contest/11214486/dashboard

## Problem A: Dynamic Grid

Simple problem.  
For each query, just bfs is enough.

## Problem B: gBalloon

Solution #1: DP  
dp[i][j] means the minimal time of first i balloons with j amounts of energy.  
`dp[i][j]=min(max(dp[i-1][j-|h[i]-k|], |p[i]/wind[k]|)) for 1<=k<=m`.  
Time complexity: O(mnq)~10^9, large solved in 164.685s.  

Solution #2: Binary Search
For each time, check if we can collect all balloons.  
Time complexity: O(mn*log(p_max)), large solved in 1.175s

## Problem C: IP Address Summarization

Trie. For each node in trie, mark if it's an end.  
If a node is end, remove all sub-trees of it.  
If the left and right children of a node are both end, then it's an end.
 
## Problem D: Virtual Rabbit

For small, just check if `[curr+1, curr+x]` has intersection with
`[day_start+g, dat_start+w-1], [day_start+h, day_start+b-1],
[day_start+86400+g, dat_start+86400+w-1], [day_start+86400+h,
day_start+86400+b-1]`.  
Find the max point to be the fed time.

For large, we cannot simulate in the whole process.  
What's to do is, find the loop. That is, find two days where
Alice feed the rabbit at the same second.  
After finding the loop, we can just calculate how many days in a loop,
how many feed times in a loop, and how many loops there are.  
It's hard to implement, please be careful.  
Check [D.java](D.java) for more details.