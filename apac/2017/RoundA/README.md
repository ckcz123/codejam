# APAC 2017 Round A
https://code.google.com/codejam/contest/11274486/dashboard

## Problem A: Country Leader

Simple. Nothing to tell.

## Problem B: Rain

For each point, calculate the `real height` of it.  
For points on board, real height equals to its height.  
Then, using bfs (with priorityQueue) to calculate
all real heights.   

## Problem C: Jane's Flower Shop

Binary Search. If mid*start>0 then start=mid, else end=mid.  
As f(1) and/or f(-1) may be zero, we need to calculate [-1+1e-12, 1-1e-12].

## Problem D: Clash Royale

DFS.  
For each card, each level, dfs to find the max attack.  
Prune if `currAtk+maxAtkLeft<=currMaxAtk`. That is, if the sum 
of attack current and max attacks left is smaller than the max attack
we have found, just return immediately.  
Check [D.java](D.java) for more details.

