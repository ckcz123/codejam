# APAC 2016 Round B
https://code.google.com/codejam/contest/10214486/dashboard

## Problem A: Travel

As `Cost[t] ¡Ü Cost[t+1]+1`, we cannot stop at any city.  
So, just for each start time, bfs to find the minimal time.

## Problem B: gWheels

As `p/e*e'/t=P/Q`, we can calculate all possible `e/e'` in the extra
gears. Then, for each p in Np and t in Nt, check if Pt/pQ is possible.
 
## Problem C: gNumbers

Calculate all factors of n with only one prime. `9075=3*25*121, etc.`  
Then dfs with memorization to solve it!

## Problem D: Albocede DNA

It's a really, really difficult problem.  
DP, dp[i][j][k][l] where i means the current character, j means
the number of a, k means the number of b, and l means if we have
started to deal with c/d.  
Check [D.java](D.java) for more details.

