# APAC 2015 Round A
https://code.google.com/codejam/contest/3214486/dashboard

## Problem A: Seven-segment Display

Simple problem.  
Just check for all start time and all possible broken LED sets.

## Problem B: Super 2048

Simple problem. Brute force is enough.  
Be care of `2 2 4 4`.
 
## Problem C: Addition

A hard problem. Very hard to implement.  
For each variable, try to write it to `ax+b`, where a is flag (1/-1), b is a constant,
x is a variable (unionFind-find/bfs).  
Then check if each variable can be calculated.  
Finally, check if we can calculate `(ax+b)+(cy+d)`.(Iff x and y both can be calculated, or x=y and a+c=0.)  
Be careful of these cases:
`a+a=3, b+b=4 => a+b=3.5 (output 3)`.  
Check my code [C.java](C.java) for more details. A really hard problem.

## Problem D: Cut Tiles

Greedy problem. For each tile, try to cut it to satisfy tiles as much as possible.  
Harder than A/B, but easier than C, I think.

