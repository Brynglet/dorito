<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Chessfish2</title>

<style>
body {background-color: lightblue;}
</style>

<link rel="stylesheet" type="text/css" href="https://pgn.chessbase.com/CBReplay.css">
<script src="https://pgn.chessbase.com/jquery-3.0.0.min.js"></script>
<script src="https://pgn.chessbase.com/cbreplay.js"></script>

</head>
<body onload="init()">
<form id="form1" method="GET" action="/chessfish2">
<table>

    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>

    <tr>
        <td style="width:176px">Games in DB</td>
        <td>
            <div id="counter_total_gamesindb_id"> </div>
        </td>
        <td></td>
    </tr>

    <tr>
        <td>&nbsp;</td>
    </tr>

    <tr>
        <td>First name</td>
        <td>
            <input type="text" id="first_name_id" name="first_name_name" oninput="oneChar()" style="width:440px"> [One char]
        </td>
        <td>
        </td>
    </tr>

    <tr>
        <td>Last name</td>
        <td>
            <input type="text" id="last_name_id" name="last_name_name" style="width:440px"> [* is wildcard]
        </td>
        <td>
        </td>
    </tr>

    <tr>
        <td>FideId</td>
        <td>
            <input type="text" id="fide_id" name="fide_id_name" style="width:440px"> [Overrides names]
        </td>
        <td>
        </td>
    </tr>

    <tr>
        <td>Color</td>
        <td>
            <select id="color_id" name="color_name" style="width:204px">
                <option value="" selected="selected">All</option>
                    <option value="white">White</option>
                    <option value="black">Black</option>
            </select>
        </td>
        <td></td>
    </tr>

    <tr>
        <td>Year >= </td>
        <td>
            <select id="year_id" name="year_name" style="width:204px">
                <option value="" selected="selected">All</option>
                <option value="2023">2023</option>
                <option value="2022">2022</option>
                <option value="2021">2021</option>
                <option value="2020">2020</option>
                <option value="2019">2019</option>
                <option value="2018">2018</option>
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
                <option value="2009">2009</option>
                <option value="2008">2008</option>
                <option value="2007">2007</option>
                <option value="2006">2006</option>
                <option value="2005">2005</option>
            </select>
        </td>
        <td></td>
    </tr>

    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>

    <tr>
        <td>Include Titled Tuesday</td>
        <td><input id="titled_tuesday_id" name="titled_tuesday_name" type="checkbox"></td>
        <td></td>
    </tr>

    <tr>
        <td>&nbsp;</td>
        <td>
            <table>
                 <tr>
                    <td>
                        <button id="find_games_id" type="button" onClick="myGames(true)">Find</button>
                    </td>
                    <td>
                        <button id="clear_all_id" type="button" onClick="clearAll()">Clear</button>&nbsp;
                    </td>
                </tr>
            </table>
        </td>
        <td></td>
    </tr>

    <tr>
        <td></td>
        <td>&nbsp;</td>
        <td></td>
    </tr>

    <tr>
        <td>Games found</td>
        <td>
            <div id="counter_found_id"><%= (String)request.getAttribute("nrOfGames") %></div>
        </td>
        <td>
        </td>
    </tr>

    <tr>
        <td></td>
        <td style="width:1210px">
            <div id="chess_result_id" class="cbreplay"><%= (String)request.getAttribute("gameStr") %></div>
        </td>
        <td></td>
    </tr>

</table>

</form>

<script>

    var pageForm = document.forms['form1'];

    function init() {

		$.ajax({
			url: "/counterTotalGamesInDb",
			type: "GET",
			dataType: "text",
			success: function(obj) {
			$('#counter_total_gamesindb_id')[0].innerHTML = obj;
			},
			error: function(obj) {
			    $('#counter_total_gamesindb_id')[0].innerHTML = "err...";
		    }
		});

        $('#titled_tuesday_id')[0].checked = '<%= (String)request.getAttribute("titled_tuesday_name") %>';
        $('#color_id')[0].value = '<%= (String)request.getAttribute("color_name") %>';
        $('#first_name_id')[0].value = '<%= (String)request.getAttribute("first_name_name") %>';
        $('#last_name_id')[0].value = '<%= (String)request.getAttribute("last_name_name") %>';
        $('#fide_id')[0].value = '<%= (String)request.getAttribute("fide_id") %>';
        $('#year_id')[0].value = '<%= (String)request.getAttribute("year_name") %>';
        $("#find_games_id").prop("disabled",false);
    }

    function myGames(mustHaveLastNameOrFideId) {

        var theFideId = $('#fide_id')[0].value.trim();

        if (isNaN(theFideId)) {
            alert('<%= (String)request.getAttribute("fideid_must_be_a_number") %>');
            return false;
        }

        if (mustHaveLastNameOrFideId) {
            if ($('#last_name_id')[0].value.trim() == "" && theFideId == "") {
                alert('<%= (String)request.getAttribute("must_provide_last_name_or_fideid") %>');
                return false;
            }
        }

        $("#find_games_id").prop("disabled",true);

    	pageForm.submit();
    }

    function clearAll() {

        $('#color_id')[0].value = "";
        $('#first_name_id')[0].value = "";
        $('#last_name_id')[0].value = "";
        $('#fide_id')[0].value = "";
        $('#year_id')[0].value = "";
        $('#titled_tuesday_id')[0].checked = true;
        $('#counter_found_id')[0].innerHTML = "0";
        $("#find_games_id").prop("disabled",false);

        myGames(false);
    }

    function oneChar() {
        if ($('#first_name_id')[0].value && $('#first_name_id')[0].value.trim().length > 1) {
            $('#first_name_id')[0].value = $('#first_name_id')[0].value.trim().substring(0,1);
        }
    }

</script>

</body>
</html>
