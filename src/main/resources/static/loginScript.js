
function loginToService() {

     let email = document.getElementById('email').value;
     let password = document.getElementById('password').value;

    fetch("http://localhost:8085/api/user/login",{
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({email:email, password:password}),
    })
        .then(res => res.json())
        .then(data => {
            console.log("Server response:", data);
            if (data.status) {
                window.location.href = "userDashboard.html?email="+ encodeURIComponent(email);
            } else {
                console.error("Login failed. Server response:", data);
                alert("Login failed. Please check your credentials.");
            }
        })
        .catch(error => {
            console.error("Error during login:", error);
            alert("Login failed. Please try again.");
        });

}

async function logout() {

    let email = document.getElementById("user-email").innerText;


    fetch("http://localhost:8085/api/user/logout",{
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({email:email}),
    }) .then(res => res.json())
        .then(data => {
            console.log("Server response:", data);
            if (data.status) {

                alert(data.message);
                window.location.href = "index.html";
            } else {
                console.error("Logout failed. Server response:", data);
                alert("Logout failed!");
            }
        })
        .catch(error => {
            console.error("Error during logout:", error);
            alert("Logout failed!");
        });
}


function loginToAdminService() {

    let email = document.getElementById("email-admin").value;
    let password = document.getElementById("password-admin").value;
    fetch("http://localhost:8085/api/admin/"+email+"/"+password)
        .then(res => {
            if(!res.ok) {
                throw new Error("HTTP error! Status:" +res.status);
            }
            else
            window.location.href = "adminDashboard.html";
        })
        .catch(error => {

            console.error("Fetch error:", error);
        });

}
