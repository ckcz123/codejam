/*
    KickStart 2019 Practice Round
    Problem B. Mural
    Passed: Small dataset and Large dataset
*/
const fs= require('fs');

const input= fs.readFileSync(0,'utf8').trim().split('\n').slice(1)
.filter((_,i)=>i%2);

console.log(input.map((e,i)=>`Case #${i+1}: ${solve(e)}`).join('\n'));

function solve(str) {
    const len= str.length,
          waste= Math.floor(len/2);
    let score = 0;
    for (let i= waste; i<len; i++) score+= +str[i];
    let maxScore= score;
    for (let i= 1; i<=waste; i++) {
        score+= +str[waste-i] - str[len-i];
        if (score > maxScore) maxScore= score;
    }
    return maxScore;
}
