const url = "http://localhost:8080/"

document.getElementById("loginButton").onclick = login

document.getElementById("register").onclick = register;

async function register() {
    let firstName = document.getElementById("R_firstName").value;
    let lastName = document.getElementById("R_lastName").value;
    let username = document.getElementById("R_username").value;
    let password = document.getElementById("R_password").value;

    if (firstName.trim().length === 0 || lastName.trim().length === 0 || username.trim().length === 0 || password.trim().length === 0) {
        alert("All fields must be filled");
        return;
    }
    
    if (/\s/.test(firstName) || /\s/.test(lastName) || /\s/.test(username) || /\s/.test(password)) {
        alert("Fields cannot contain white space");
        return;
    }


    let registerDTO = {
        firstName: firstName,
        lastName: lastName,
        username: username,
        password: password
    };

    console.log(registerDTO);

    try {
        const response = await fetch(url + "auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(registerDTO)
        });

        let responseData = await response.text(); 

        if (!response.ok) {
            throw new Error("Registration failed with status: " + response.status);
        }

        let data;
        try {
            data = JSON.parse(responseData); 
        } catch (error) {
            // Handle non-JSON response
            console.log(responseData);
            if (responseData.startsWith("User success")) {
                console.log("Registration successful");
            } else {
                console.log("Unexpected response format");
            }
            return;
        }

        console.log(data);
        console.log(data.accessToken);
        console.log(parseJwt(data.accessToken));
    } catch (error) {
        console.error("Registration error:", error);
        document.getElementById("header").innerHTML = "Register Failed!! Try again...";
    } finally {
        
    }
}

async function login(){

    let username = document.getElementById("username").value
    let password = document.getElementById("password").value

    let loginDTO = {
        username:username,
        password:password
    }


    console.log(loginDTO) 

    await fetch(url + "auth/login", {

        method: "POST", 
        headers: {"Content-Type":"application/json"}, 
        body: JSON.stringify(loginDTO) 

    })

    .then((response) => response.json()) 
    .then((data) => { 
        console.log(data)
        console.log(data.accessToken)

        console.log(parseJwt(data.accessToken))

        document.cookie = data.accessToken
       
        console.log(document.cookie)

        if(parseJwt(data.accessToken).Role === "Employee"){
            window.location.href = "Employee.html"
        } else {
            window.location.href = "Finance_Manager.html"
        }

    })
    .catch((error) => {document.getElementById("header").innerHTML = "Login Failed! Try again..."})
    .finally()

}



function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}