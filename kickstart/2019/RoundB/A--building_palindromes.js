/*
    Kick Start 2019
    Round B
    Problem A: Building Palindromes
    Solved: both test sets
*/

// blueprint
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim();
const chars= input.match(/[A-Z]+/g);

const questions= input.split(/\d+\s\d+\n[A-Z]+/)
.slice(1)
.map(e=>e.split('\n').filter(Boolean));

console.log(chars
    .map((e, i) =>
      `Case #${i + 1}: ${solve(e, questions[i])}`)
    .join('\n'));

// fast solution 
function solve(str, ranges) {
  let positiveAnswers= 0;
  const list= [];
  const frequency= Array(26).fill(0);
  for (let c of str) {
    frequency[c.charCodeAt()-65]++;
    list.push([...frequency]);
  }
 
  ranges.forEach((range)=> {
    const [start, end] = range.split(' ').map(Number);
    let answer= 1;
    let odds= 0;

    for (let i=0; i<26; i++) {
      let occurrencies = list[end-1][i] - (start>1 ? list[start-2][i] : 0);
      if(occurrencies%2 && ++odds>1) { answer= 0; break; }
    }
    positiveAnswers+=answer;
  });
  return positiveAnswers;
}

// slow solution (sufficient olnly for Test set 1)
function solve(str, ranges) {
  let positiveAnswers= 0;
  for (let range of ranges) {
    const [start, end]= range.split(' ');
    let counter= Array(26).fill(0);

    for (let i=start-1; i<end; i++) {
        counter[str[i].charCodeAt()-65]++;
    }
    let answer= 1;
    let odds= 0;

    for (let i= 0; i<26; i++) {  
       if (counter[i]%2) {
           if (++odds>1) {
               answer= 0; 
               break;
           }
       }       
    }
    positiveAnswers += answer;
  }
  return positiveAnswers;
}