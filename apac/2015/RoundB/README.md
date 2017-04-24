# APAC 2015 Round B
https://code.google.com/codejam/contest/4214486/dashboard

## Problem A: Password Attacker

Simple DP problem.  
dp[i][j] = sigma_(k) {jCk * dp[i-1][j-k]}  
Just return dp[m][n].

## Problem B: New Years Eve

Simple problem. Brute force is enough.  
Check each level, one by one. If one is overflowed, flow it to the next level.  

## Problem C: Card Game

DP.  
dp[i][j] means how many cards can be left in range [i, j].  
dp[i][j] = min(1+dp[i][j-1], dp[i][k-1]) if [k, j] can be discarded.
Just return dp[1][n]

## Problem D: Parentheses Order

For small inputs, we can generate all possible parentheses, and get the kth smallest.  
As for large, we can use DP.  
dp[i][j] means how many possible parentheses with i pairs, and has j '(' in the beginning.  
For example,   
dp[1][1]=1 `()`  
dp[2][2]=1 `(())`, dp[2][1]=2 `(()), ()()`  
dp[3][3]=1 `((()))`, dp[3][2]=3 `((())), (())(), (()())`, dp[3][3]=5 
`((())), (())(), (()()), ()(()), ()()()`  
...

And then, we need to find, for n pairs,
how many '(' are there in the k-th smallest one in the beginning.  
Find that index, and remove a pair of `()`, to get sub-problem of n-1.  

For example, for n=3 and k=4, as dp[3][2]<k<=dp[3][3], so there are only one 
'(' in the beginning.
Remove the '()', we get n=2 and k=1 (4-dp[3][2]), and the answer is `(()))`.  
So the answer is `()(())`.  
Be care of BigInteger, as dp[i][j] may exceed 2^64.  
Check [D.java](D.java) for more details.
