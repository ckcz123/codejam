/*
   KickStart 2017
   Practice Round
   Problem A: Country Leader
   Both solved
*/
const fs= require('fs');
const input= fs.readFileSync(0,'utf8').trim().split(/\d+\s+/).filter(Boolean);

console.log(input.map((e,i)=>`Case #${i+1}: ${solve(e)}`).join('\n'));

function solve(strOfNames) {
    const allNames= strOfNames.split('\n');
    const longestNames= new Set;
    let max= 0;
    allNames.forEach((name)=>{
        const uniqueChars= new Set(name);
        uniqueChars.delete(' ');
        if(uniqueChars.size>max) {
            max= uniqueChars.size;
            longestNames.clear();
        }
        if (uniqueChars.size===max) longestNames.add(name);
    });
    return [...longestNames].sort()[0];
}
