<!DOCTYPE html>
<html lang="">
<head>
    <title>
        All users
    </title>
</head>
<body>
<h1>All users</h1>
<p></p><a href="/products">Products </a></p>
<a href="/users"> Users</a>
<form action="/users" method="get">
    Find User by product: <input type="name"><br>
    <input type="submit" value="Find"/>
</form>
<#list users as user>
    <div>
        <p><b>${user.name} </b>${user.surname}: <b>${user.balance}</b> ua| <a href="/user/${user.id}">Details</a></p>
    </div>
<#else>
    <h3> No Users </h3>
</#list>
<hr>
<h3>Create User</h3>
<form action="/user/create" method="post">
    Name   : <input type="text" name="name"/><br><br>
    Surname: <input type="text" name="surname"/><br><br>
    Balance: <input type="number" name="balance"/><br><br>
    <input type="submit" value="Add user"/>
</form>
</body>
</html>