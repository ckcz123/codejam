# APAC 2017 Round B
https://code.google.com/codejam/contest/5254487/dashboard

## Problem A: Sherlock and Parentheses

`x*(x+1)/2, where x=max(l, r)`.

## Problem B: Sherlock and Watson Gym Secrets

First, we need to calculate how many pairs (i, j) are there where
`(i^a+j^b)%k=0`.  
To do this, for each 0<=x<k, we need to record x^a and x^b mod k,
then check for each 0<=x<k, the number of i where `(i^a)%k=x`,
and the number of j where `(j^b)%k=k-x`.  

Then, we need to minus the number of i where `(i^a+i^b)%k=0`.

Note: the number of i in [1, n] where `i%k==x` is `n/k+(x!=0&&x<=n%k?1:0)`.

## Problem C: Watson and Intervals

We can use a TreeMap to remember all the segments where it's covered
by only one given interval.  
That is, sort all intervals, keep the current segments where it's covered
only once, and consider each case  
Check `calTreeMap` in [C.java](C.java) for details.  
After calculating the treemap, then for each interval, check all the length
of segments in the treemap where it's covered by this segment.

## Problem D: Sherlock and Permutation Sorting

This problem is hard, extremely hard.  
Assume `prims[n]` the number of n-permutation where it can not be divided anymore.
(0, 1, 1, 3, 13, ...)  
Then `prims[n]=n! - sigma_(i,0,n-1) {i!*prims[n-i]}`.  
Let `f[n, k]` means the number of n-permutation where it can be divided to
at most k segments.  
Then `f[n, 1]=prims[n]`, `f[n, k] = sigma_(i,0,n-1) {f[i, k-1]*prims[n-i]}`.  
We need to calculate `ss[n] = sigma_(k,1,n) {k*k*f[n, k]}`.  
To solve this, first we calculate `s[n] = sigma_(k,1,n) {k*f[n, k]}`.  
Obviously, `s[n] = sigma_(i,0,n-1) {(s[i]+i!)*prims[n-i]}`,  
and we can get `ss[n] = sigma_(i,0,n-1) {(ss[i]+2*s[i]+i!)*prims[n-i]}`.  

Time complexity:  
O(n^2) to calculate `prims[n]`, no need to calculate `f[n,k]`,
O(n^2) to calculate `s[n]`, O(n^2) to calculate `ss[n]`.  

Check [D.java](D.java) for more details.
