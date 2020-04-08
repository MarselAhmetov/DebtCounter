<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="../static/css/styles.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Debt</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

</nav>
<br>
<br>
<div class="container">

    <div class="row text-center">
        <table class="table table-hover">
            <thead>
            <tr class="table-warning">
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Go to</th>
            </tr>
            </thead>
            <tbody>
            <#if friends??>
                <#list friends as friend>
                    <tr>
                        <form action="/profile" method="get">
                            <td>${friend.username}</td>
                            <td>${friend.email}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" name="userId"
                                        value="${friend.id}">${friend.username}</button>
                            </td>
                        </form>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>