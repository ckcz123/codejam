# APAC 2015 Round C
https://code.google.com/codejam/contest/5214486/dashboard

## Problem A: Minesweeper

Simple brute force problem.  
Tag each empty cell. Each time choose a `0` cell, and bfs to clean board.  
If there are no `0` cells, calculate the number of all invisible cells.

## Problem B: Taking Metro

Ah-ha! This problem is not difficult, but you must be careful.  
For each station, we build two verticals, one is on the metro (v),
the other is on the station (v').  
Obviously, distance[v][v']=0, distance[v'][v]=time[line(v)].  
You need to calculate distance[start'][end'].  
Take care of some cases, for example, you do not need to go on metros, instead, 
just walk though tunnels to reach the destination.  
Check [B.java](B.java) for more details.

## Problem C: Broken Calculator

DFS with memorization / DP.  
For each number n, find all factors of n.

## Problem D: Tetris

Tetris is a wonderful problem, I love it!  
Brute force in this problem.  
Take care of the height of while inserting.  
