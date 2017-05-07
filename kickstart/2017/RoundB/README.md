# KickStart 2017 Round B
https://code.google.com/codejam/contest/11304486/dashboard

## Problem A: Math Encoder

Simple problem.  
Just sort all numbers, and for each i<j, there are 2^(j-i) subsets with min-a[i] and max-a[j].  

## Problem B: Center

For each point (a, b), make two diagonal lines `y=x+b-a`, `y=-x+a+b`.  
Then, the final point must be an intersection of two lines.

For small input, just check all intersections to calculate the min-value. O(n^3).  

For large input, we need to calculate in O(n^2).  
For any diagonal line L, there are n intersections on the line.  
Let's assume points Pi=(ai, bi), the intersection of L and
anti-diagonal line of Pi is Qi=(ci, di).  
Then, for X=(x, y): 
```
dis(X, Pi)=max(|x-ai|, |y-bi|)
dis(X, Pi)=dis(X, Qi)+dis(Qi, Pi)
```
As all Qi can be calculated by Pi, we just need to calculate 
```
sum = sigma_(i,1,n) {dis(X,Qi)}
= sigma_(i,1,n) {w[i]*|x-ci|}
```
So, the problem is, there are N points on a line, (c[1], c[2], ..., c[n]), calculate the minimal
value of `s(x) = sigma_(i,1,n) {w[i]*|x-c[i]|`.  
Let `x[0]=c[0], s[0]=s(x[0]), q[0]=-sigma {w[i]}`.  
Then for `1<=j<=n`, 
```
x[j]=c[j]
dx=x[j]-x[j-1]
s[j]=s[j-1]+dx*q[j]
q[j]+=2*w[j]
```
Finally, calculate the minimal value of `s[i]`.

## Problem C: Christmas Tree

DP problem.  
For each position `(i, j)` and each `1<=v<=k`,  dp[i][j][v] means the largest v-Christmas tree
with top `(i,j)`. We just need to calculate the max value of `dp[i][j][k]`.  
To do this, just calculate the height of each point, then dp.  


