<!DOCTYPE html>
<html lang="">
<head>
    <title>
        Product detail
    </title>
</head>

<body>
<p></p><a href="/products">Products </a></p>
<a href="/users"> Users</a>
<h4>Product detail</h4>
<b>Name of product: </b>${product.name}<br>
<b>Price: </b>${product.price}<br>
<b>Users that bought this product: </b>${product.price}<br>
<#--<#list users as user>
    <div>
        <p><b>${user.name}</b>${user.surname} ua| <a href="/user/${user.id}">Details</a></p>
    </div>
<#else>
    <h3> No users </h3>
</#list>-->
<hr>
<h3>Delete product</h3>
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Delete"/>
</form>
</body>
</html>