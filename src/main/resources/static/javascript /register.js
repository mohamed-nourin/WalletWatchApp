const registerForm = document.getElementById('form')
const registerUsername = document.getElementById('username')
const registerPassword = document.getElementById('password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/users'

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }
    console.log(bodyObj)
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    console.log(response.status);

    const responseArr = await response.json()

    console.log(response)
    if(responseArr[0] === "http://localhost:8080/login.html"){
        window.location.replace("http://localhost:63342/WalletWatch/WalletWatch/src/main/resources/static/html/login.html?_ijt=omnik32ko60fadck10fuc06pvj&_ij_reload=RELOAD_ON_SAVE")
    }
    else if(response.redirected == false){
        alert("Username is already taken")
    } else {
        alert("Registration successful. You can now log in.");
    }
}

registerForm.addEventListener("submit", handleSubmit)
