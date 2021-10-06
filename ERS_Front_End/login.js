const url = "http://localhost:8090/";


// Login functionality
document.getElementById("loginBtn").addEventListener("click", loginFunc);

async function loginFunc(){

    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username:usern,
        password:userp
    }

    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    })

    console.log(response.status)
    console.log(response.text)

    if(response.status === 200){
        localStorage["currentUser"] = usern;
        window.location.replace("http://127.0.0.1:5501/index.html");
    } else{
        document.getElementById("login-row").innerText="Login failed! Do better."

    }
}