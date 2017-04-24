# KickStart 2017 Round A
https://code.google.com/codejam/contest/8284486/dashboard

## Problem A: Square Counting

This is a math problem. Hard.  
All the squares can be divided to two different types: center is a grid,
or center is not a grid.  

First, calculate the number of squares with a grid-center.  
Let `k` is the minimal distance between the center and the border, then there
are `k*(k+1)` squares. (Just draw some grids to figure out.)  

Second, calculate the number of squares with a non-grid-center.  
Let `k` is the minimal distance between the center and the border, then there
are `k*k` squares. (Just draw some grids to figure out.)  

So we can figure the small solver:
``` java
long cnt=0;
for (int i=1;i<=r;i++) {
    for (int j=1;j<=c;j++) {
        int k=Math.min(Math.min(i-1, r-i), Math.min(j-1, c-j));
        cnt+=k*(k+1);
        cnt%=1000000007;
    }
}
for (int i=1;i<r;i++) {
    for (int j=1;j<c;j++) {
        int k=Math.min(Math.min(i, r-i), Math.min(j, c-j));
        cnt+=k*k;
        cnt%=1000000007;
    }
}
return cnt;
```

As for large, we need to calculate the sum in O(1) time.  
It's hard to calculate, but just check [A.java](A.java) for the result.

## Problem B: Patterns Overlap

Simple. DP.  
dp[i][j] means if the first i characters in s1 and first j characters in s2 can match.  
Just take care of `*`, it can only match 0-4 characters.  
And some corner cases:  
```
"", "*"
"a", "*"
"aaab", "*b"
"***a***a***a***a***a***", "*a"
...
```

## Problem C: Space Cubes

Consider the huge cube which can cover all the stars.  
Then, the two cubes must be placed into the diagonal vertex. 
(left-front-top with right-back-bottom, etc.)  
So, binary search for edge length, and check the four possible placements.
