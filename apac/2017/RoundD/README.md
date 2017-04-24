# APAC 2017 Round D
https://code.google.com/codejam/contest/5264486/dashboard

## Problem A: Vote

[Catalan Number](https://en.wikipedia.org/wiki/Catalan_number).  
Consider of a way with n upstrokes and m downstrokes.
It's a route from `(0, 0) -> (n+m, n-m)`, totals numbers are `(n+m)Cm`.  
However, the first vote must be A, and afterwards the way cannot 
reach the horizontal line. That is, `(1, 1) -> (n+m, n-m)` where the whole route
is above the horizontal line.  
All possible routes number of `(1, 1) -> (n+m, n-m)` is `(n+m-1)C(n-1)`.  
All invalid routes number is `(n+m-1)C(m-1)` (Reflection, 
check [here](https://en.wikipedia.org/wiki/Catalan_number#Second_proof).)  
Finally, the result is 
`((n+m-1)C(n-1)-(n+m-1)C(m-1))/((n+m)Cn) = (n-m)/(n+m)`.

## Problem B: Sitting

Assume `row<=col`.  
If `row<=2` then sit as `## ## ## ## ##...`.
Else, sit as:
```
## ## ## ## ## ...
# ## ## ## ##  ...
 ## ## ## ## # ...
## ## ## ## ## ...
...
```

## Problem C: Codejamon Cipher

Sort each word, save to HashMap.  
Then for each string, DP to find the number of ways.

## Problem D: Stretch Rope

DP & Segment Tree.  
dp[i][j] means the minimal money needed, to get length j in first i ropes.  
As the i-th rope may has [A[i], B[i]] length,
`dp[i][j]=min(dp[i-1][j], p[i]+dp[i-1][k]) where j-B[i]<=k<=j-A[i]`.  
So, we can build a segment tree for dp[i-1], to query the 
minimal of `dp[i-1][j-A[i]] ~ dp[i-1][j-B[i]]` in O(log l) time.  
Total time complexity: `O(n)*(O(l*log(l))+O(l*log(l)))=O(n*l*log(l))`.  
Check [D.java](D.java) for more details.