# APAC 2016 Round C
https://code.google.com/codejam/contest/4284487/dashboard

## Problem A: gRanks

Brute force. Simple.

## Problem B: gFiles

Intervals intersection.  
Take care of p=0 and p=100. Use `long` instead of `double`.

## Problem C: gGames

DP/DFS with memorization.  
dp[i][x] means, can we put x in first i rounds. Here x records people
(one in a bit).  
To solve this, just break x into two numbers y and z, then check dp[i-1][y]
and dp[i-1][z]. Check all possible subsets.  
Return `dp[n][(1<<n)-1]`.

## Problem D: gMatrix

First, solve the problem [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/).  
Then, just apply it to each row, then each col. 