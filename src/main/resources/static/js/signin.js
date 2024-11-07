const btn = document.getElementById("signin_btn");
const username = document.getElementById("username");
const password = document.getElementById("password");

function setCookie(name, value) {

    // 쿠키 설정
    document.cookie = name + "=" + value + "; path=/"; // path=/로 설정하면 사이트 전체에서 유효
}

btn.addEventListener("click", () => {  
    let data = {
        username : username.value,
        password : password.value
    }

    fetch("signin", {
        method : "POST",
        headers : {"Content-type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(res => {
        if(!res.ok) {
            throw new Error("로그인 에러");
        }
        return res.json();
    })
    .then(res => {
        location.href = "http://localhost:8080/"; 
        
    })
    .catch(error => {
        console.log("에러내용 :" + error);
    })
})