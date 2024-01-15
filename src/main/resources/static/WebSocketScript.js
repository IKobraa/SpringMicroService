let stompClient = null;

$(document).ready(function() {
    console.log("Index page is ready");
    connect();

    $("#send").click(function() {
        sendMessage();
    });

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

});

function connect() {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/public', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/user/topic/private', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

async function sendMessage() {
    console.log("sending message");
    let msg = $("#message").val();
    stompClient.send("/app/message", {}, JSON.stringify({'messageContent': "Admin message: "+msg}));
    await saveMessages("Admin message: "+msg,"Admin","Admin");

}

async function sendPrivateMessage() {
    try {
        const input = document.getElementById("private-message-id");
        const message = document.getElementById("private-message");
        const senderEmail = document.querySelector(".profile-email#user-email").innerText;
        const email = input.value;
        const msg = message.value;

        if (!email) {
            console.error("Recipient email is required.");
            return;
        }

        showMessage("You sent to " + email + ": " + msg);
        await saveMessages(msg, email, senderEmail);
        console.log("Sender Email:", senderEmail);

        const response = await fetch("http://localhost:8085/send-private-message/" + email, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ messageContent: "Message received from " + senderEmail + ": " + msg }),
        });

        if (!response.ok) {
            console.error("Error sending private message");
        } else {
            console.log("Private message sent successfully!");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

async function saveMessages(msg,receiverEmail,senderEmail) {
    try {
    const response = await fetch("http://localhost:8085/api/user/saveUserMessage", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({receiverEmail: receiverEmail, senderEmail: senderEmail, message: msg }),
    });

    if (!response.ok) {
        console.error("Error saving user message");
    } else {
        console.log("User message saved successfully!");
    }
    } catch (error) {
        console.error("Error saving user message:", error);
}
}
