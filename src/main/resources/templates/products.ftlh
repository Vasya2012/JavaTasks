<!DOCTYPE html>
<html lang="">
<head>
    <title>
        All products
    </title>
</head>
<body>
<h1>All products</h1>

<p></p><a href="/products">Products </a></p>
<a href="/users"> Users</a>

<form action="/products" method="get">
    Find product by name: <input type="name"><br>
    <input type="submit" value="Find"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.name}</b>${product.price} ua| <a href="/product/${product.id}">Details</a></p>
    </div>
    <#else>
    <h3> No products </h3>
</#list>
<hr>
<h3>Add product</h3>
<form action="/product/create" method="post">
    Название: <input type="text" name="name"/><br><br>
    Цена:     <input type="number" name="price"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
</body>
</html>