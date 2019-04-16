let fs= require('fs');
let input= fs.readFileSync(0,'utf8').trim().split('\n').slice(1);
console.log(input.map((e,i)=>`Case #${i+1}: ${solve(e)}`).join('\n'));

function solve(str){
    let up= '', down= '';
    for (let i=0; i<str.length; i++) {
        if (str[i]%2) {

            str[i]!=='9'?
            up+= (+str[i]+1+'').padEnd(str.length-i,'0')
            :up+= (str[i]-1+'').padEnd(str.length-i,'8');

            break;
        }
        up+= str[i];
    };

    for (let i=0; i<str.length; i++) {
        if (str[i]%2) {
            down+= (str[i]-1+'').padEnd(str.length-i,'8');
            break;
        }
        down+= str[i];
    };

    if (Number.isSafeInteger(+str)) {
      return Math.min(Math.abs(str-up), Math.abs(str-down));
    }

    return [getDiff(str,up), getDiff(str,down)]
    .sort()[0].replace(/^0+(?=\d)/,'');
}

/*

 JS can't handle very big numbers natively
 (check out Number.MAX_SAFE_INTEGER)... yet (BigInt and other stuff is coming)
 so in order to solve A-large subproblem I implemented the function
 getDiff which determines a difference between two numbers represented
 as strings: for example getDiff('13','2')-> 11, getDiff('5','8')-> 3 and so on

*/
function getDiff(a, b)
{
  const len= Math.max(a.length, b.length);
  let r= '', c= 0, d, diff;
  [a,b]= [a,b].map(e=>e.padStart(len,'0'));
  if (a < b) [a,b]= [b,a];

  for (let i=len-1; i>=0; i--)
  {
    d= +b[i] + c;

    +a[i]>=d ?
    (c= 0, diff= a[i]-d)
    :(c= 1, diff= '1'+a[i]-d)

    r = diff + r;
  }
  return r;
}
