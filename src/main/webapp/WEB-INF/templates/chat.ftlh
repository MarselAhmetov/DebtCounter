<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script>
        function sendMessage(sender, text, receiver) {
            let body = {
                sender: sender,
                text: text,
                receiver: receiver
            };

            $.ajax({
                url: "/messages",
                method: "POST",
                data: JSON.stringify(body),
                contentType: "application/json",
                dataType: "json",
                complete: function () {
                    if (text === 'Entering in the chat') {
                        receiveMessage(sender)
                    }
                }
            });
        }

        // LONG POLLING
        function receiveMessage(username) {
            $.ajax({
                url: "/messages?username=" + username,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $('#messages').first().after('<li>' + response[0]['sender'] + ' :=: ' + response[0]['text'] + '</li>');
                    receiveMessage(username);
                }
            })
        }

        function sendOnClose(username) {
            $.ajax({
                url: "/deleteMessages",
                method: "GET",
                data: JSON.stringify(username),
                dataType: "json",
                contentType: "application/json",
            })
        }
    </script>
</head>
<body onload="sendMessage('${currentUser.username}', 'Entering in the chat'); receiveMessage(${currentUser.username})">
<h1>Your are - ${currentUser.username}</h1>
<div>
    <input id="message" placeholder="Ваше сообщение">
    <select id="receiver">
        <#list users as user>
            <option value="${user.username}">
                <#if user.role = "SUPPORT">
                    ${user.role}
                <#else>
                    ${user.username}
                </#if>
            </option>
        </#list>
    </select>
    <button onclick="sendMessage('${currentUser.username}',
            $('#message').val(), $('#receiver').val())">Send
    </button>
</div>
<div>
    <ul id="messages">

    </ul>
    <#list messages as message>
        <li>${message.sender.username} :=: ${message.text}</li>
    </#list>
</div>
</body>
</html>