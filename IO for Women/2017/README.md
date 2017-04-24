# Codejam to I/O for Women 2017
https://code.google.com/codejam/contest/12224486/dashboard

## Problem A: Ticket Trouble

Simple. For each row, greedy to get the maximum number.

## Problem B: Understudies

Sort all probabilities. Then match the smallest one with the biggest one.

## Problem C: Word Search

Not so simple.  
Just consider of this pattern:
```
IIIIIIIIIIIIII
//////////////
OOOOOOOOOOOOOO
//////////////
IIIIIIIIIIIIII
...
```
For each `/` on border, it will create 1 `I/O`.  
For each `/` in middle, it will create 3 `I/O`.

## Problem D: Where Ya Gonna Call?

This problem is hard, very hard, maybe the hardest problem in Codejam to I/O for Women.  
Using Floyd algorithm to calculate distance between any nodes.  
Then, for each edge, for each possible position, check the minimax.  
The problem is, what's the possible position?  

Assume the verticals of the edge is `u, v` and its length is `l`.  
Assume the distance between u and any point is `a[]`, distance
between v and any point is `b[]`.
Then, all possible positions x should satisfies `a[i]+x = b[j]+(l-x)`.  
So, just iterate i and j, to calculate all possible x, and find the minimax.
 
