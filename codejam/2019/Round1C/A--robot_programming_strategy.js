/*
  Round 1C 2019 - Google Code Jam 2019
  Problem A: Robot Programming Strategy
  Test set passed: Both
*/

function solve(arr) {
    const r=[];
    let len=0
    arr.forEach(str=>{if (str.length>len) len=str.length;});

    arr=arr.map(str=>{
        while (str.length<len) str+=str;
        return str.slice(0,len)+str[0];
    });

    for (let i=0; i<=len; i++) {
      const set=new Set();

      arr.forEach(str=>set.add(str[i]));

      if (set.size===3) return 'IMPOSSIBLE';

      if (set.size===1) {
        r.push(set.has('P')?'S':set.has('R')?'P':'R');
        return r.join('');
      }

      r.push(set.has('P')&&set.has('R')?'P':set.has('P')&&set.has('S')?'S':'R');
      const latest=r[r.length-1];
      arr=arr.filter(str=>str[i]===latest);
    }
    return 'IMPOSSIBLE';
}

// an input handling/parsing
const fs=require('fs');
const input=fs.readFileSync(0,'utf8').trim();
const tests=input.split(/\d+\s/).filter(Boolean);
console.log(tests.map((t,i)=> {
    const arg= t.split('\n').filter(Boolean);
    return `Case #${i+1}: ${solve(arg)}`;
}).join('\n'));