

function loadAllUsers() {

    fetch("http://localhost:8085/api/admin/user/all")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {
            const userTable = document.getElementById("all-user-table");
            userTable.innerHTML = "";

            let sortSickDays = document.createElement("button");
            sortSickDays.textContent = "sort by sick-days";
            sortSickDays.onclick = () => sortUsersBySickDays();
            let divSick = document.getElementById("filter");
            divSick.innerHTML = " ";
            divSick.appendChild(sortSickDays);

            data.forEach(userData => {
                const userRow = document.createElement("div");
                userRow.classList.add("user-row");

                const userDataDiv = document.createElement("div");
                userDataDiv.classList.add("user-data");

                for (const [key, value] of Object.entries(userData)) {
                    const keyValueDiv = document.createElement("div");
                    keyValueDiv.textContent = `${key}: ${value}`;
                    userDataDiv.appendChild(keyValueDiv);
                }
                const deleteButton = document.createElement("button");
                deleteButton.style.backgroundColor = "#660000";
                deleteButton.textContent = "Delete";

                deleteButton.addEventListener("click", () => { loadAllUsersDelete(userData)});
                userDataDiv.appendChild(deleteButton);
                userRow.appendChild(userDataDiv);
                userTable.appendChild(userRow);

            });

        })
        .catch(error => {
            console.error('Fetch error:', error);
        });

}

function loadAllUsersDelete(userData) {
    fetch("http://localhost:8085/api/admin/delete", {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({name: userData.name, surname: userData.surname, email: userData.email,
            department: userData.department, password: userData.password
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status: " + response.status);
            }
            alert("user "+userData.email+" was deleted!");
            loadAllUsers();
            return response.json();

        })
        .catch(error => {
            console.log("user could not be deleted! error:  "+error);
        })
}

function sortUsersBySickDays() {

    fetch("http://localhost:8085/api/admin/user/all")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {

            data.sort((a,b) => (a.sickDays < b.sickDays) ? 1 : (b.sickDays < a.sickDays) ? -1 : 0);
            const userTable = document.getElementById("all-user-table");
            userTable.innerHTML = "";

            data.forEach(userData => {

                let divSick = document.getElementById("filter");
                divSick.innerHTML = " ";

                const userRow = document.createElement("div");
                userRow.classList.add("user-row");

                const userDataDiv = document.createElement("div");
                userDataDiv.classList.add("user-data");

                for (const [key, value] of Object.entries(userData)) {
                    const keyValueDiv = document.createElement("div");
                    keyValueDiv.textContent = `${key}: ${value}`;
                    userDataDiv.appendChild(keyValueDiv);
                }
                const deleteButton = document.createElement("button");
                deleteButton.style.backgroundColor = "#660000";
                deleteButton.textContent = "Delete";
                deleteButton.addEventListener("click", () => { loadAllUsersDelete(userData)});

                userDataDiv.appendChild(deleteButton);
                userRow.appendChild(userDataDiv);
                userTable.appendChild(userRow);

            });

        })
        .catch(error => {
            console.error('Fetch error:', error);
        });

}


function makeUsersEditable() {

    let divSick = document.getElementById("filter");
    divSick.innerHTML = " ";

    fetch("http://localhost:8085/api/admin/user/all")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {
            let divSick = document.getElementById("filter");
            let saveButton = document.createElement("button");
            saveButton.textContent = "update Userdata";
            saveButton.onclick = () => updateUserData();
            divSick.appendChild(saveButton);


            const userTable = document.getElementById("all-user-table");
            userTable.innerHTML = "";

            data.forEach(userData => {
                const userRow = document.createElement("div");
                userRow.classList.add("user-row");

                for (const [key, value] of Object.entries(userData)) {

                        const keyValueDiv = document.createElement("div");
                    keyValueDiv.classList.add("key-value-pair");

                    const keySpan = document.createElement("span");

                    keySpan.textContent = `${key}: `;
                    keyValueDiv.appendChild(keySpan);

                    const valueInput = document.createElement("input");
                    valueInput.type = "text";
                    valueInput.value = value;
                    keyValueDiv.appendChild(valueInput);

                    userRow.appendChild(keyValueDiv);

                }

                userTable.appendChild(userRow);
            });
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });

}

//collects edited user-data
function saveUserData() {

    const userTable = document.getElementById("all-user-table");
    const editedUsers = [];

    userTable.querySelectorAll(".user-row").forEach(userRow => {
        const editedUser = {};
        userRow.querySelectorAll(".key-value-pair input").forEach(input => {
            const key = input.previousSibling.textContent.replace(": ", "");
            editedUser[key] = input.value;
        });

        editedUsers.push(editedUser);
    });
    return editedUsers;

}

//saves new user-data to database
function updateUserData() {

    const updatedUserData = saveUserData();

    fetch("http://localhost:8085/api/admin/saveAll",{
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedUserData),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to update user data");
            }
            alert("user-data was successfully updated")
        })
        .catch(error => {
            console.error("Error updating user data:", error);
        });


}

function createUser() {

    let divSick = document.getElementById("filter");
    divSick.innerHTML = " ";

    const userTable = document.getElementById("all-user-table");
    userTable.innerHTML = "";

    let saveNewUserButton = document.createElement("button");
    saveNewUserButton.textContent = "save new user";
    saveNewUserButton.onclick = () => saveNewUser();
    divSick.appendChild(saveNewUserButton);

    let createUserDiv = document.createElement("div");
    createUserDiv.id = "createUserForm";

    const fields = [
        {label: "Name", id: "name", type: "text", defaultValue: ""},
        {label: "Surname", id: "surname", type: "text", defaultValue: ""},
        {label: "Email", id: "email", type: "text", defaultValue: ""},
        {label: "Department", id: "department", type: "text", defaultValue: ""},
        {label: "Password", id: "password", type: "password", defaultValue: ""}
    ];

    fields.forEach(field => {
        const container = document.createElement("div");
        container.style.marginBottom = "15px";

        const label = document.createElement("span");
        label.textContent = field.label;


        const input = document.createElement("input");
        input.type = field.type;
        input.id = field.id;
        input.value = field.defaultValue;


        container.appendChild(label);
        container.appendChild(input);
        createUserDiv.appendChild(container);
        userTable.appendChild(createUserDiv);
    });


}

function saveNewUser() {

    fetch("http://localhost:8085/api/admin/add", {
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(getNewUserData()),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to add new user");
            }
            alert("user was successfully saved")
            createUser();
            return response.json();
        })
        .catch(error => {
            console.error("Error adding user:", error);
        });



}

//collect user data from user-registration form
function getNewUserData() {

    const userTable = document.getElementById("all-user-table");

    const editedUser = {};

    userTable.querySelectorAll("input").forEach(input => {
        const fieldName = input.id;
        editedUser[fieldName] = input.value;
    });

    return editedUser;
}

function viewReportPage() {
    window.location.href = "AdminReportPage.html";
}
function loadReports() {

    const reportBox = document.querySelector(".Report-Box");
    reportBox.innerHTML = " ";

    fetch("http://localhost:8085/api/admin/getAllReports")
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {
            let dataFiltered = data.filter(report => report.done === false);
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
                <button class="reply-button" onclick="replyReport(${report.id})">reply</button>
            `;

                reportBox.appendChild(reportDiv);
            })
        }).catch(error => {
        console.log("error retrieving reports: "+error);
    })
}

function replyReport(reportId) {

    const reportBox = document.querySelector(".Report-Box");
    reportBox.innerHTML = " ";

    fetch("http://localhost:8085/api/admin/getReport/"+reportId)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
            return response.json();
        })
        .then(data => {

            let reportDiv = document.createElement("div");
            reportDiv.className = "single-report";

            reportDiv.innerHTML = `
                <h3>${data.senderEmail}</h3>
                <p>${data.department}</p>
                <p>${data.title}</p>
                <p>${data.dateTime}</p>
                <hr>
                <p>${data.problem}</p>
                <hr>
                <textarea id="report-reply" rows="4" required></textarea>
                <button id="report-reply-button" onclick="sendReportReply(${data.id})">send-reply</button>
 
            `;

            reportBox.appendChild(reportDiv);

        }).catch(error => {
        console.log("error retrieving reports: "+error);
    })


}

async function sendReportReply(reportId) {

    let reply = document.getElementById("report-reply").value;

    await fetch("http://localhost:8085/api/admin/ReportReply/"+reportId,{
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(reply),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status:" +response.status);
            }
        })
        .catch(error => {
            console.log("error updating report: "+error);
        })

    alert("user got your reply!")
    window.location.href = "adminDashboard.html";
}