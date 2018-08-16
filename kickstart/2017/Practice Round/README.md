# KickStart 2017 Practice Round
https://code.google.com/codejam/contest/6304486/dashboard

## Problem A: Country Leader

Simple. Nothing to tell.

## Problem B: Vote

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

## Problem C: Sherlock and Parentheses
   
`x*(x+1)/2, where x=min(l, r)`.
