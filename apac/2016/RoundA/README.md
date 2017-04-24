# APAC 2016 Round A
https://code.google.com/codejam/contest/4284486/dashboard

## Problem A: Googol String

Simple. Nothin to tell.

## Problem B: gCube

Simple. `V=PI_(i,l,r) {a[i]}, Edge=pow(V, 1/d).`

## Problem C: gCampus

Floyd to calculate the distance of any two offices.  
Then, for each road, if the distance between its two verticals is smaller than
its length, then it's an inefficient road.

## Problem D: gSnake

Brute force. As X<=1000000, we can simulate at most 1100000 seconds.  
for every second in [1, 1100000], check if the snake will die, or it can eat food.  
Using HashSet to remember all bodies, and all foods eaten.
