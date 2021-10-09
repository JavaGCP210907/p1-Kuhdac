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


    console.log(response.status);

    if(response.status === 200){
        localStorage["currentUser"] = usern;
        let role_id = await getUser()
        console.log(role_id)
        if(role_id == "1"){
         window.location.replace("http://127.0.0.1:5501/manager.html")
        } else{
        window.location.replace("http://127.0.0.1:5501/employee.html")
        }
        
    } else{
        document.getElementById("login-row").innerText="Login failed! Do better."

    }
}

async function getUser(){

    let cUser = localStorage["currentUser"]
    
    let response = await fetch(url + "role",{
        method: "POST",
        body: JSON.stringify(cUser),
        credentials:"include"
    })

    if (response.status === 200){
        console.log(response.status);
        let data =  await response.json()
        localStorage["user_id"] = data.user_id
        return data.role.role_id
    } else{
        console.log("No luck")
    }
    



}