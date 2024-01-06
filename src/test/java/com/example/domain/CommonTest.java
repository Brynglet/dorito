package com.example.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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

    }

    @Test
    public void testrand() {
        for (int k = 0; k < 100; k++) {
            int randNumber = (int) (Math.random() * 10);
            System.out.println(randNumber);
        }
    }
    @Test
    public void testgm() {

        int nrOfBlackBoxes = 8;
        int rows = (nrOfBlackBoxes * 2) + 1;

        Box[][] boxes = new Box[rows][rows];

        int columns = rows;

        for (int i = 0; i < rows; i++) {
            // 4 blackboxes ger rows=9 ...=9 korningar
            for (int k = 0; k < columns; k++) {
                Box box = new Box();
                box.setColorEnum(getInitTheColor(i, k));
                boxes[i][k] = box;
            }

        }

        for (int i = rows-1; i >= 0; i--) {
            // 4 blackboxes ger rows=9 ...=9 korningar
            for (int k = 0; k <columns ; k++) {
                System.out.print(" " + boxes[i][k].getColorEnum());
            }

        }

    }

    private ColorEnum getInitTheColor(int i, int k) {
        if (i % 2 != 0 && k % 2 != 0) {
            return ColorEnum.BLACK;
        }
        if (i % 2 == 0 && k % 2 == 0) {
            return ColorEnum.GREY;
        }
        return ColorEnum.CORAL;
    }

    @Test
    public void tesMs() {
        Long tot = 2555L;
        double totInSec = tot.doubleValue()/1000;
        System.out.println("totInSec:" + totInSec);
    }
}