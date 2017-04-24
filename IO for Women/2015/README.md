# Codejam to I/O for Women 2015
https://code.google.com/codejam/contest/9214486/dashboard

## Problem A: I/O Error

Simple. Nothing to tell.

## Problem B: Dreary Design

Assume the smallest number of (a, b, c) is k.
Then a,b,c should be in `[s, min(k, s+v)]`. Let `t=min(k, s+v)-s`; 
If `a=b=c=s`: only 1 pair.  
If `a=b or b=c or c=a`:
- If `a=b=s, c>s` then the number should be `3*t`.
- If `a=b>s, c=s` then the number shoule be `3*t`.  

If `a!=b, b!=c, c!=a`: the number shoule be `6*t*(t-1)`.  
Sum them, and get `(3v^2+3v+1)*(k-v)+(v+1)^3`.

## Problem C: Power Levels

How to calculates the digits number of `9000!!!!!...!`?  
Just log10 it!  
Binary search to find `log10(9000!!!....!)<n`.

## Problem D: Googlander

DP.  
Let's consider the first turn-down place.  
for each grid (i, j), if the googlander turns from right to down here, then
he only has a (i-1)*(j-1) grid to go!

