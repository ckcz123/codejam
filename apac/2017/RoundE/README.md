# APAC 2017 Round E
https://code.google.com/codejam/contest/5264487/dashboard

## Problem A: Diwali lightings

Simple. Just calculate the number of blue lights between [1, k].

## Problem B: Beautiful Numbers

Read this [leetcode discuss topic](https://discuss.leetcode.com/topic/76368/python-solution-with-detailed-mathematical-explanation-and-derivation)
for explanation.

## Problem C: Partioning Number

If the smallest number is x.  
Then, just calculate the number of pairs `(p,q,r) where px+q(x+1)+r(x+2)=n`.  
for each p in [1, n/x], `q(x+1)+r(x+2)=n-px`.  
Obviously, `(q+r)(x+1)+r=n-px`, that is, `r%(x+1)=(n-px)%(x+1)`.  
So, we just need to calculate how many r in [0, (n-px)/(x+1)] satisfies `r%(x+1)=(n-px)%(x+1)`.

## Problem D: Sorting Array

This problem is hard, really hard.  
I don't know how to solve or explain it...  
If you can solve this problem, please contact me at [ckcz12345@gmail.com](mailto:ckcz12345@gmail.com).
