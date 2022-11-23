// p[0].innerHTML = "1 대답";
// document.getElementById("ppp").style.display = 'block';
// // document.getElementById("ppp").style.display = 'none';

const div1 = document.getElementById("aaaaa");
const div2 = document.getElementById("bbbbb");

const createHTML1 = document.getElementById("createHTML1");
const ques = document.getElementById("ques");

const createHTML2 = document.getElementById("createHTML2");
const ques2 = document.getElementById("ques2");

const creatediv1 = document.createElement("div");
const creatediv2 = document.createElement("div");

let count1 = 1;
let count2 = 1;

let a = document.getElementById("p1").innerText;
let b = document.getElementById("p2").innerText;

div1.addEventListener("click", function () {
    ques.setAttribute('class', 'box wlfans1');
    ques.innerHTML = a;

    creatediv1.setAttribute('class', 'box');
    creatediv1.innerHTML = "질문 1의 대답" + count1;

    count1++;
    createHTML1.appendChild(ques);
    createHTML1.appendChild(creatediv1);
})

div2.addEventListener("click", function () {
    ques2.setAttribute('class', 'box wlfans2');
    ques2.innerHTML = b;

    creatediv2.setAttribute('class', 'box');
    creatediv2.innerText = "질문 2의 대답" + count2;

    count2++;
    createHTML2.appendChild(creatediv2);
})