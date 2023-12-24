package com.example.domain;

import com.example.exception.ApiError;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.utility.Common.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CommonTest {


    @Test
    public void testNrOfTotalDashes() {


        /*

        Doritpgame with 3 boxes * 3 boxes has 24 possible dashes. Srart and end are excluded
        1: 4
2: 12
3: 24
4: 40

+4 +8 +12 + 16
         */

        int dorLen = 1;
        double nrOfDashes = 0;
        for (int k = 1; k <=dorLen ; k++) {
            nrOfDashes +=  (k * 4);
        }
        System.out.println("nrOfDashes:" + nrOfDashes);

        nrOfDashes = 0;
        dorLen = 2;
        for (int k = 1; k <=dorLen ; k++) {
            nrOfDashes +=  (k * 4);
        }
        System.out.println("nrOfDashes:" + nrOfDashes);

        nrOfDashes = 0;
        dorLen = 3;
        for (int k = 1; k <=dorLen ; k++) {
            nrOfDashes +=  (k * 4);
        }
        System.out.println("nrOfDashes:" + nrOfDashes);

        nrOfDashes = 0;
        dorLen = 4;
        for (int k = 1; k <=dorLen ; k++) {
            nrOfDashes +=  (k * 4);
        }
        System.out.println("nrOfDashes:" + nrOfDashes);

        int nrOfBlackBoxes = 4;
        int rows = (nrOfBlackBoxes * 2) + 1;

        Box[][] boxes = new Box[rows][rows];

        for (int i = 0; i < rows; i++) {
            System.out.println("kallei");
        }
    }



    @Test
    public void testgm() {

        //System.out.println("kalle1");
        int nrOfBlackBoxes = 8;
        int rows = (nrOfBlackBoxes * 2) + 1;

        Box[][] boxes = new Box[rows][rows];

        int columns = rows;
        // System.out.println("kalle2");

        for (int i = 0; i < rows; i++) {
            // 4 blackboxes ger rows=9 ...=9 körningar
            //System.out.println("kalle3");
            for (int k = 0; k < columns; k++) {
                //System.out.println("kalle4");
                Box box = new Box();
                box.setColor(getTheColor(i, k));
                boxes[i][k] = box;
            }

        }
        System.out.println("");

        //System.out.println("kalle5");
        for (int i = rows-1; i >= 0; i--) {
            // 4 blackboxes ger rows=9 ...=9 körningar
            //System.out.println("kalle6");
            System.out.println("");
            for (int k = 0; k <columns ; k++) {
                //System.out.println("kalle7");
                //System.out.println("row" + i + " column" + k);
                System.out.print(" " + boxes[i][k].getColor());
            }

        }
        System.out.println("");
        System.out.println("");

    }

private String getTheColor(int i, int k) {
        if (i%2 != 0 && k%2 != 0) {
        return "b";
        }

        if (i%2 == 0 && k%2 == 0) {
        return "g"; //beslutsruta
        }

        return "c"; //faktiska strecken
        }
}
/*

    @Test
    public void test4NumbersWithRegexOk() {

        String number1 = "1";
        String number2 = "12.3";
        String number3 = "-1";
        String number4 = "-1.34534";

        String number5 = "";
        String number6 = "12..3";
        String number7 = "- 1";
        String number8 = "A";

        assertTrue(NUMBER_PATTERN.matcher(number1).matches());
        assertTrue(NUMBER_PATTERN.matcher(number2).matches());
        assertTrue(NUMBER_PATTERN.matcher(number3).matches());
        assertTrue(NUMBER_PATTERN.matcher(number4).matches());

        assertFalse(NUMBER_PATTERN.matcher(number5).matches());
        assertFalse(NUMBER_PATTERN.matcher(number6).matches());
        assertFalse(NUMBER_PATTERN.matcher(number7).matches());
        assertFalse(NUMBER_PATTERN.matcher(number8).matches());

        Integer randomInt1 = ThreadLocalRandom.current().nextInt(1, 100000000);
        Integer randomInt2 = ThreadLocalRandom.current().nextInt(1, 100000000);
        Integer randomInt3 = ThreadLocalRandom.current().nextInt(1, 100000000);
        Integer randomInt4 = ThreadLocalRandom.current().nextInt(1, 100000000);
        Integer randomInt5 = ThreadLocalRandom.current().nextInt(1, 100000000);

        assertNotNull(randomInt1);
        assertNotNull(randomInt2);
        assertNotNull(randomInt3);
        assertNotNull(randomInt4);
        assertNotNull(randomInt5);
    }

    @Test
    public void testStuff() {

        String line = "[Event \"World Fischer Random GpA\"]";
        boolean b1 = line.substring(0,7).equalsIgnoreCase(START_OF_GAME_EVENT);
        assertTrue(b1);

        line = "[White \"Livaic, Leon (CRO)\"]";
        boolean b2 = line.substring(0,7).equalsIgnoreCase(WHITE_PLAYER_ENG);
        assertTrue(b2);

        line ="[Black \"Wadsworth, Matthew (ENG)\"]";
        boolean b3 = line.substring(0,7).equalsIgnoreCase(BLACK_PLAYER_ENG);
        assertTrue(b3);
    }

    @Test
    public void testStuff2() {

        String line = "[White \"Raja, Rithvik (IND)\"]";

        String lastName = "X";
        int startPosLastName = line.indexOf("\"") + 1;
        int endPosLastName = line.indexOf(",");
        if (startPosLastName > 0 && endPosLastName > 0 && endPosLastName > startPosLastName) {
            lastName = line.substring(startPosLastName, endPosLastName);
        }
        assertTrue("Raja".equalsIgnoreCase(lastName));

        String firstName = "X";
        int startPosFirstName = endPosLastName + 1;
        int endPosFirstName = line.lastIndexOf("\"");
        if (startPosFirstName > 0 && endPosFirstName > 0 && endPosFirstName > startPosFirstName) {
            firstName = line.substring(startPosFirstName, endPosFirstName).trim();
        }
        assertTrue("Rithvik (IND)".equalsIgnoreCase(firstName));
    }

    @Test
    public void testStuff3() {

        String line = "[Date \"2016.03.20\"]";
        boolean b1 = line.substring(0,6).equalsIgnoreCase(DATE_ENG);
        assertTrue(b1);
        int startPosLastName = line.indexOf("\"") + 1;
        int endPosLastName = line.indexOf(".");
        String theYear1 = line.substring(startPosLastName, endPosLastName);
        assertEquals("2016", theYear1);

        line = "[Date \"????.??.??\"]";
        boolean b2 = line.substring(0,6).equalsIgnoreCase(DATE_ENG);
        assertTrue(b2);
        startPosLastName = line.indexOf("\"") + 1;
        endPosLastName = line.indexOf(".");
        String theYear2 = line.substring(startPosLastName, endPosLastName);
        assertEquals("????", theYear2);

        line = "[Event \"Titled Tuesday sxcvl\"]";
        int startPos = line.indexOf("\"") + 1;

        String tt = line.substring(startPos, startPos + TITLED_TUESDAY.length());
        assertEquals("Titled Tue", tt);
    }

    @Test
    public void testStuff4() {

        String namesComma1 = "Andersson, Frist Frost, Nilsson, Smith";
        List<String> names1 = Arrays.asList(namesComma1.split(","));
        assertEquals(4, names1.size());

        String namesComma2 = "Andersson";
        List<String> names2 = Arrays.asList(namesComma2.split(","));
        assertEquals(1, names2.size());
    }

    @Test
    public void testStuff5() {

        String line = "[Date \"2023.04.27\"]";

        int startPosYear = line.indexOf("\"") + 1;
        int endPosYear = line.indexOf(".");

        int startPosMonth = endPosYear + 1;
        int endPosMonth = line.lastIndexOf(".");

        int startPosDay = endPosMonth + 1;
        int endPosDay = line.lastIndexOf("\"");

        String year = line.substring(startPosYear, endPosYear);
        String month = line.substring(startPosMonth, endPosMonth);
        String day = line.substring(startPosDay, endPosDay);

        ZonedDateTime zdt1 = ZonedDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0,0,0,0, ZonedDateTime.now().getZone());
        ZonedDateTime zdt2 = ZonedDateTime.of(Integer.parseInt("1999"), Integer.parseInt(month), Integer.parseInt(day),0,0,0,0, ZonedDateTime.now().getZone());

        Long ms1 =  zdt1.toInstant().toEpochMilli();
        Long ms2 =  zdt2.toInstant().toEpochMilli();

        assertTrue(ms1 > ms2);
    }

    @Test
    public void testStuff6() {

        List<DoritoGame> games = new ArrayList<>();

        Long ms = ZonedDateTime.now().toInstant().toEpochMilli() - 100000000;

        for (int k = 0; k < 100; k++) { //k<3200000 also works
            ms++;
            DoritoGame doritoGame = new DoritoGame();
            doritoGame.setDateInMilliSeconds(ms);
            doritoGame.setYear("2022");

            doritoGame.setBlackLastName("BL");
            doritoGame.setBlackFirstName("BF");

            doritoGame.setWhiteLastName("WL");
            doritoGame.setWhiteFirstName("WF");

            doritoGame.setGameString(getTheLines(ms));

            games.add(doritoGame);
        }

        assertNotNull(games);
    }

    @Test
    public void testStuff7() {

        Integer aa = HttpStatus.INTERNAL_SERVER_ERROR.value();
        HttpStatusCode bb = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String cc = HttpStatus.INTERNAL_SERVER_ERROR.name();

        assertNotNull(aa);
        assertNotNull(bb);
        assertNotNull(cc);
    }

    private String getTheLines(Long ms) {

        String res = "";
        ms++;

        for (int k = 0; k < 25; k++) { // 25 is average per game
            res += k;
            res += "aaaaaabbbbbbbbbcccccc_";
            res += ms + "\n";
        }

        return res;
    }


    @Test
    public void testStuff8() {

        long aaa = ZonedDateTime.now().toInstant().toEpochMilli();

        System.out.println("aaa:" + aaa);
        assertNotNull(aaa);

        aaa = ZonedDateTime.now().toInstant().toEpochMilli();

        System.out.println("aaa:" + aaa);
        assertNotNull(aaa);

        aaa = ZonedDateTime.now().toInstant().toEpochMilli();

        System.out.println("aaa:" + aaa);
        assertNotNull(aaa);
    }

    @Test(expected = ApiError.class)
    public void testStuff9() {

        String version = "  v1";
        String firstName = " ; ";
        String badChar1 = ";";

        if ((!StringUtils.isEmpty(version) && version.contains(badChar1)) || (!StringUtils.isEmpty(firstName) && firstName.contains(badChar1)) ) {
            throw new ApiError(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }
    }

    @Test
    public void testStuff10() {
        String day = "02";
        day = isANumber(day) ? day : ZERO_ONE;
        assertEquals(2, Integer.parseInt(day));
    }

    @Test
    public void testStuff11() {

        String line = "[Date \"2021.07.26\"]";

        int startPosYear = line.indexOf("\"") + 1;
        int endPosYear = line.indexOf(".");

        int startPosMonth = endPosYear + 1;
        int endPosMonth = line.lastIndexOf(".");

        int startPosDay = endPosMonth + 1;
        int endPosDay = line.lastIndexOf("\"");

        String year = line.substring(startPosYear, endPosYear);
        year = isANumber(year) ? year : YEAR_1200;

        String month = line.substring(startPosMonth, endPosMonth);
        month = isANumber(month) ? month : ZERO_ONE;

        String day = line.substring(startPosDay, endPosDay);
        day = isANumber(day) ? day : ZERO_ONE;

        Long inMilliSeconds1 = ZonedDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0,0,0,0, ZonedDateTime.now().getZone())
                .toInstant().toEpochMilli();

        assertEquals("2021",year);
        assertEquals("07",month);
        assertEquals("26",day);

        line = "[Date \"??.??.??\"]";

        startPosYear = line.indexOf("\"") + 1;
        endPosYear = line.indexOf(".");

        startPosMonth = endPosYear + 1;
        endPosMonth = line.lastIndexOf(".");

        startPosDay = endPosMonth + 1;
        endPosDay = line.lastIndexOf("\"");

        year = line.substring(startPosYear, endPosYear);
        year = isANumber(year) ? year : YEAR_1200;

        month = line.substring(startPosMonth, endPosMonth);
        month = isANumber(month) ? month : ZERO_ONE;

        day = line.substring(startPosDay, endPosDay);
        day = isANumber(day) ? day : ZERO_ONE;

        Long inMilliSeconds2 = ZonedDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0,0,0,0, ZonedDateTime.now().getZone())
                .toInstant().toEpochMilli();

        assertEquals("1200",year);
        assertEquals("01",month);
        assertEquals("01",day);

        line = "[Date \"2021.??.??\"]";

        startPosYear = line.indexOf("\"") + 1;
        endPosYear = line.indexOf(".");

        startPosMonth = endPosYear + 1;
        endPosMonth = line.lastIndexOf(".");

        startPosDay = endPosMonth + 1;
        endPosDay = line.lastIndexOf("\"");

        year = line.substring(startPosYear, endPosYear);
        year = isANumber(year) ? year : YEAR_1200;

        month = line.substring(startPosMonth, endPosMonth);
        month = isANumber(month) ? month : ZERO_ONE;

        day = line.substring(startPosDay, endPosDay);
        day = isANumber(day) ? day : ZERO_ONE;

        assertEquals("2021",year);
        assertEquals("01",month);
        assertEquals("01",day);

        Long inMilliSeconds3 = ZonedDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0,0,0,0, ZonedDateTime.now().getZone())
                .toInstant().toEpochMilli();

        assertTrue(inMilliSeconds1 > inMilliSeconds3 && inMilliSeconds3 > inMilliSeconds2);
    }

    @Test
    public void testStuff12() {

        String name = "jamesson";

        String filterName = "Jame*";
        int starPos = filterName.indexOf('*');
        filterName = filterName.substring(0, starPos);

        name = name.substring(0, filterName.length());

        assertTrue(filterName.equalsIgnoreCase(name));
        assertTrue(filterName.equalsIgnoreCase("jame"));
    }

    @Test
    public void testStuff13() {

        String fideId = "";
        String line = "[BlackFideId \"4901339\"]";

        if (line.length() > 12 && BLACK_FIDEID_ENG.equalsIgnoreCase(line.substring(0,13))) {
            int startPos = line.indexOf("\"") + 1;
            int endPos = line.lastIndexOf("\"");
            if (startPos > 0 && endPos > 0 && endPos > startPos) {
                fideId = line.substring(startPos, endPos);
            }
        }
        assertTrue(fideId.equalsIgnoreCase("4901339"));
    }

    private boolean isANumber(String value) {
        return !ObjectUtils.isEmpty(value) && NUMBER_PATTERN.matcher(value).matches();
    }
 */