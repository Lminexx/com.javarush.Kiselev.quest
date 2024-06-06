<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<h1><%= "Пролог" %></h1>
<br/>
<pre>    Ты стоишь в космическом порту и готов подняться на борт своего корябля.
    Разве ты не об этом мечтал? Стать капитаном галактического судна с экипажем,
    который будет совершать подвиги под твоим командованием.
    Так что вперед.</pre>

<h1><%="Знакомсто с экипажем"%></h1>
<pre>    Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках
    - Здравствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе
    наш штурман - сержант с перегаром, под штурвалом спит  - Черный Богдан.
    А как к вам обращаться?</pre>

<form action="hello-servlet" method="post">
    <input type="text" name="username">
    <button type="submit">Представиться</button>
</form>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>