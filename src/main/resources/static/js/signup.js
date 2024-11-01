const signUpBtn = document.getElementById("signup_btn");
const username = document.getElementById("username");
const password = document.getElementById("password");



signUpBtn.addEventListener("click", () =>{
    let data = {
        username : username.vlaue,
        password : password.vlaue
    }
   
    fetch("signup", {
        method : "POST",
        headers : {"Content-type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(res => {
        console.log(res.ok);
    })
    .then(res => {
    
       
    })
    .catch(error => {
        console.log(error);
    })
})

