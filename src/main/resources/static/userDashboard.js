function showProfile() {
    let emailU = document.getElementById("user-email").innerText;

    fetch("http://localhost:8085/api/admin/user/email/"+emailU)
        .then(res => res.json())
        .then(data => {

            window.location.href = "detailedUser.html?name=" + encodeURIComponent(data.name)
                + "&surname=" + encodeURIComponent(data.surname)
                + "&email=" + encodeURIComponent(data.email)
                + "&department=" + encodeURIComponent(data.department)
                + "&salary=" + encodeURIComponent(data.salary)
                + "&sickDays=" + encodeURIComponent(data.sickDays)
                + "&holidays=" + encodeURIComponent(data.holidays)
                + "&hourlyWage=" + encodeURIComponent(data.hourlyWage)
                + "&workHours=" + encodeURIComponent(data.workHours)
                + "&workState=" + encodeURIComponent(data.workState)
                + "&lastLoginTime=" + encodeURIComponent(data.lastLoginTime);

        })
        .catch(error => {
            console.error("Error during login:", error);
            alert("Login failed!");
        });


}

function adminLogin() {
    window.location.href = "adminLogin.html";
}
function showAvailableUsers(){
    const cont = document.getElementById("available-users");
    cont.innerHTML = "";


    fetch("http://localhost:8085/api/admin/user/all")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {
            const filteredData = data.filter(user => user.workState === true)
            //style in css
            const headLine = document.createElement("h1");
            headLine.innerText = "User online:"
            headLine.style.textAlign = "center";
            headLine.style.border = "1px solid #ccc";
            headLine.style.borderRadius = "8px";
            headLine.style.padding = "10px";
            headLine.style.marginBottom = "10px";
            headLine.style.color = "white";
            cont.appendChild(headLine);

            filteredData.forEach(userData => {
                const par = document.createElement("p");
                par.innerHTML = `Email: ${userData.email}`;
                par.style.border = "1px solid #ccc";
                par.style.borderRadius = "8px";
                par.style.padding = "10px";
                par.style.marginBottom = "10px";
                par.style.textAlign = "center";
                par.style.color = "#39e75f";


                cont.appendChild(par);
            });
        })
        .catch(error => {
            console.log("error retrieving available users: "+error);
        })
}

function showOldMessages() {

    const myEmail = document.querySelector(".profile-email#user-email").innerText;

    fetch("http://localhost:8085/api/user/getAllMessages")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {

            data.forEach(data => {
                if(data.receiverEmail === myEmail) {
                    $("#messages").append("<tr><td>" + "Message received from "+data.senderEmail+": " +data.message + "</td></tr>");
                }
                else if(data.receiverEmail !== myEmail && data.senderEmail === myEmail){
                    $("#messages").append("<tr><td>" + "You sent to " +data.receiverEmail+": " +data.message + "</td></tr>");
                }else if(data.receiverEmail === "Admin" && data.senderEmail === "Admin"){
                    $("#messages").append("<tr><td>" + data.message + "</td></tr>");
                }
            })


        }).catch(error => {
        console.log("error retrieving available users: "+error);
    })


}

function clearOldMessages() {
    $("#messages").empty();
}

async function loadReportPage() {

    const myEmail = document.querySelector(".profile-email#user-email").innerText;
    let department = " ";

    await fetch("http://localhost:8085/api/admin/user/email/"+myEmail)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {
            department = data.department;
        }).catch(error => {
            console.log("error retrieving  user: "+error);
        })

    window.location.href = "reportPage.html?email=" + encodeURIComponent(myEmail)
        + "&department=" + encodeURIComponent(department);
}
function loadReportReplyPage() {
    const myEmail = document.querySelector(".profile-email#user-email").innerText;
    window.location.href = "userReportPage.html?email=" + encodeURIComponent(myEmail);
}

function loadReportReplies() {

    const replyBox = document.querySelector(".Reply-Box");
    const myEmail = document.getElementById("email-reply-report").innerText;
    replyBox.innerHTML = " ";

    fetch("http://localhost:8085/api/admin/getAllReports")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {
            let dataFiltered = data.filter(report => report.done === true && report.senderEmail === myEmail);
            dataFiltered.forEach(report => {

                let reportDiv = document.createElement("div");
                reportDiv.className = "single-report";

                reportDiv.innerHTML = `
                <h3>${report.senderEmail}</h3>
                <p>${report.department}</p>
                <p>${report.title}</p>
                <p>${report.dateTime}</p>
                <hr>
                <p>${report.problem}</p>
                <hr>
                <p>${report.reply}</p>
                <button class="done-button" onclick="doneReport(${report.id})">done and delete</button>
            `;

                replyBox.appendChild(reportDiv);
            })
        }).catch(error => {
        console.log("error retrieving reports: "+error);
    })
}

function doneReport(reportId) {

    fetch("http://localhost:8085/api/user/deleteReport/"+reportId, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status: ${response.status}");
            }
            alert("report finished");
        })
        .catch(error => {
            console.error("Error deleting report:", error);
        });
}