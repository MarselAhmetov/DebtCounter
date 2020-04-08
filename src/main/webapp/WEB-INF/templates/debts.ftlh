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
    <div class="row">
        <div class="col-md-6 text-center">
            <h1>My money</h1>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Debtor</th>
                    <th scope="col">Description</th>
                    <th scope="col">Debt count</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list debts as debt>
                    <tr>
                        <form action="/person">
                            <#if !debt.debtor??>
                                <td>${debt.debtorName}</td>
                            <#else>
                                <td>${debt.debtor.username}</td>
                            </#if>
                            <td>${debt.description}</td>
                            <td>${debt.debtCount}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" value="${debt.id}">Delete</button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-6 text-center">
            <h1>My debts</h1>
            <table class="table table-hover">
                <thead>
                <tr class="table-warning">
                    <th scope="col">Owner</th>
                    <th scope="col">Description</th>
                    <th scope="col">Debt count</th>
                    <th scope="col">Go to</th>
                </tr>
                </thead>
                <tbody>
                <#list mydebts as mydebt>
                    <tr>
                        <form action="/person">
                            <#if !mydebt.debtor??>
                                <td>${mydebt.owner}</td>
                            <#else>
                                <td>${mydebt.owner.username}</td>
                            </#if>
                            <td>${mydebt.description}</td>
                            <td>${mydebt.debtCount}</td>
                            <td>
                                <button class="btn btn-warning" type="submit" value="${mydebt.id}">Delete</button>
                            </td>
                        </form>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>