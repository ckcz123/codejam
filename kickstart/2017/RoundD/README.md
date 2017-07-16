# KickStart 2017 Round D
https://code.google.com/codejam/contest/8284487/dashboard

## Problem A. Go Sightseeing

Simple DP Problem.  
dp[i][j] means, the earliest time we arrive at city `i`, if we visit `j` cities.  
dp[i][j] =  
- visit `i-1`th city: dp[i-1][j-1] + Ts + wait_time + bus_time
- do not visit `i-1`th city: dp[i-1][j] + wait_time + bus_time  

Then, find the max `m` satisfied `dp[n][m]<=Tf`.  

## Problem B. Sherlock and The Matrix Game

I only solve the small part.  
Just O(n^4) to calculate all the sums of sub-matrices, then choose the k-th largest.  
We don't need to remember all tht sums (~8*10^8), however using `PriorityQueue` to remember the largest k items is enough.  

## Problem C. Trash Throwing

It's a math problem.  
First, binary search for `R`. For each R, just check if there is a valid `a`.  
To do this, for each point `(x0, y0)`, we just need to find `a1, a2`, that is, if `0<=a<=a1` or `a2<=a<=a_max`, then the parabola has no intersections with circle `(x0, y0, R)`.
Here a_max is determined by `H`.  
We can find the standard `a_standard`, where `(x0, y0)` is on the parabola.  
Then, binary search `[0, a_stardard]` to find a1, binary search `[a_standart, a_max]` to find a2.  
To binary search `[0, a_standard]`, just check if parabola with `a=mid` has intersections with the circle.
It's a quartic function, and to find the min value of it, just calculate the derivative, **binary search** to find the solution, and check the minimal value.  
Yes, in this problem, we need three binary-searches.  

Finally, find if there exists `a` satisfied `0<=a<=a1 || a2<=a<=a_max` for every point.  
Not easy, but interesting.
