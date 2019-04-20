/*
 * APAC 2015 (University Graduates Test)
 * Round D
 * Problem C: Sort a scrambled itinerary
 * Passed: both tests
 */
const fs= require('fs'),
  input= fs.readFileSync(0,'utf8').trim().split(/^\d+\n/m).filter(Boolean)
  .map(e=>e.trim().split('\n'));
console.log(input.map((e,i)=>`Case #${i+1}: ${solve(e)}`).join('\n'));

function solve(arr){
    let dep= arr.filter((_,i)=>i%2===0);
    let dest= arr.filter((_,i)=>i%2);
    let first= dep.findIndex(e=>!dest.includes(e));
    let tickets= dep.map((e,i)=>e+'-'+dest[i]);
    let r= tickets.splice(first,1);
    while (tickets.length) {
      let dep= r[r.length-1].slice(-3);
      let idx= tickets.findIndex(e=>e.slice(0,3)===dep);
      r.push(tickets.splice(idx,1)[0]);
    }
    return r.join(' ');
}
