<!DOCTYPE html>
<html lang="">
<head>
    <title>
        User details
    </title>
</head>
<p></p><a href="/products">Products </a></p>
<a href="/users"> Users</a>
<body>
<h4>User details</h4>
<b>Name:    </b>${user.name}<br>
<b>Surname: </b>${user.surname}<br>
<b>Balance: </b>${user.balance}<br>

<#--<#list products as product>
    <div>
        <p><b>${product.name}</b>${product.price} | <a href="/product/${product.id}">Details</a></p>
    </div>
<#else>
    <h3> No products </h3>
</#list>-->
<b></b>
<hr>
<h3>Add product to user</h3>
<form action="/user/${user.id}/buy" method="post">
    ID of product  : <input type="number" name="id"/><br><br>
    <input type="submit" value="Edit"/>
</form>
<hr>
<h3>Delete User</h3>
<form action="/user/delete/${user.id}" method="post">
    <input type="submit" value="Delete"/>
</form>
</body>
</html>