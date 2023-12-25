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

<table>
    <tr>
        <td></td>
        <td style="width:1210px">
            <div id="chess_result_id" class="cbreplay"><%= (String)request.getAttribute("gameStr") %></div>
        </td>
        <td></td>
    </tr>
</table>

<script>

    var pageForm = document.forms['form1'];

    function init() {
        alert("init1");
    }

</script>

</body>
</html>
