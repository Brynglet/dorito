<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Chessfish</title>

<style>
body {background-color: lightblue;}
</style>

<script src="/js/jquery-3.6.4.js"></script>
<script src="/js/chessfish_js.js"></script>

</head>
<body onload="init()">

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
            <input type="text" id="first_name_id" oninput="oneChar()" style="width:410px"> [One char]
        </td>
        <td>
            &nbsp;
        </td>
    </tr>

    <tr>
        <td>Last name</td>
        <td>
            <input type="text" id="last_name_id" style="width:410px"> [* is wildcard]
        </td>
        <td></td>
    </tr>

    <tr>
        <td>FideId</td>
        <td>
            <input type="text" id="fide_id" name="fide_id_name" style="width:410px"> [Overrides names]
        </td>
        <td>
        </td>
    </tr>

    <tr>
        <td>Color</td>
        <td>
            <select id="color_value_id" name="color_value_name" style="width:204px">
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
            <select id="year_value_id" name="year_value_name" style="width:204px">
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
        <td><input id="titled_tuesday_id" type="checkbox" checked="checked"></td>
        <td></td>
    </tr>

    <tr>
        <td>&nbsp;</td>
        <td>
            <table>
                 <tr>
                    <td>
                        <button id="chess_response_id" type="button" onClick="chessResponse()">Find</button>
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
            <div id="counter_found_id"></div>
        </td>
        <td></td>
    </tr>

    <tr>
        <td>&nbsp;</td>
        <td>
            <textarea id="chess_result_id" style="width:410px" rows="24" cols="1"></textarea>
        </td>
        <td></td>
    </tr>

</table>

</body>
</html>
