const paramEmail = new URLSearchParams(window.location.search);
const userEmail = paramEmail.get('email')
document.getElementById("absender-report").value = userEmail;

const paramDep = new URLSearchParams(window.location.search);
const userDep = paramDep.get('department')
document.getElementById("abteilung-report").value = userDep;

function submitForm() {

    const email = document.getElementById("absender-report").value;
    const department = document.getElementById("abteilung-report").value;
    const title = document.getElementById("title-report").value;
    const dateTime = document.getElementById("dateTime-report").value;
    const problem = document.getElementById("problem-report").value;


    fetch("http://localhost:8085/api/user/submitReport",{
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({senderEmail: email, department: department, title: title, dateTime: dateTime,
            problem: problem
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to save report");
            }
            alert("Report was submitted!")
        })
        .catch(error => {
            alert("failed to save report")
            console.error("Error saving report:", error);
        });
}