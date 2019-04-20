/*
 * Round A - Kick Start 2019
 * Problem A Training
 * Test set 1 passed
 * Test set 2 passed
 * */

const fs= require('fs'),
      input= fs.readFileSync(0,'utf8').trim().split('\n').slice(1),
      np= input.filter((_,i)=>i%2===0).map(e=>+e.split(' ')[1]),
      sk= input.filter((_,i)=>i%2).map(e=>e.split(' ').map(Number));

console.log(np.map((e,i)=>`Case #${i+1}: ${solve(e, sk[i])}`).join('\n'));

function solve(n,arr) {
  arr.sort((a,b)=>a-b);
  let lowSkill= arr[0],
      highSkill= arr[n-1],
      highLowDiff= highSkill-lowSkill,
      hours= arr.slice(0,n).reduce((r,e)=>r+highSkill-e,0),
      minHours= hours;
  for (let i=1; i<=arr.length-n; i++) {
    hours += (arr[i+n-1]-highSkill)*(n-1) - highLowDiff;
    highSkill= arr[i+n-1];
    lowSkill= arr[i];
    highLowDiff= highSkill-lowSkill;
    if (hours<minHours) minHours= hours;
  }
  return minHours;
}
