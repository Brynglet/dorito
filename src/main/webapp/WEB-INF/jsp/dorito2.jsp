<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Chessfish2</title>

<style>
body {background-color: lightblue;}
</style>

<script src="https://pgn.chessbase.com/jquery-3.0.0.min.js"></script>

</head>
<body onload="init()">

<table width="400px" height="400px" border="1">
    <%= (String)request.getAttribute("doritoTable") %>
</table>

<script>

    var pageForm = document.forms['form1'];

    function init() {
        //alert("init1");
    }

</script>

</body>
</html>
