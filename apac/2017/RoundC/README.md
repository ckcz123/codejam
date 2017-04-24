# APAC 2017 Round C
https://code.google.com/codejam/contest/6274486/dashboard

## Problem A: Monster Path

DFS.  
When we reach a point in the k-th time, the possibility to get score
is `pow(1-p, k)*p`.

## Problem B: Safe Squares

Simple.  
For each point,
calculate the max square length (as the bottom-right point).
Then sum them.

## Problem C: Evaluation

Topological sort. Simple.  
We only add one variable to the set iff it's can be calculate. (That is, 
`x=f(...)` where `...` can all be calculated.)

## Problem D: Soldiers

Let's assume the max attack is x and the max defense is y.  
If there is one soldier s where `s.a=x and s.d=y`, the Alice can pick s and she win!  
If no soldiers have max attack and max defense, then Alice cannot pick one
with max attack (or Bob will choose one with max defense, and game over!).  
Thus, we can remove all soldiers with max attack or max defense, and a new game starts!
