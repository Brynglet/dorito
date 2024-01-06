package com.example.domain;

import com.example.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Common {

    public static final Integer MAX_BLACK_BOXES = 36;
    public static final String INVALID_INPUT = "Invalid input. Must be a square nr and below " + MAX_BLACK_BOXES;

    public static void validateInput(String nrOfBlackBoxes) throws ApiError{

        int nrOfBlackBoxesInt = 0;

        try {
            nrOfBlackBoxesInt = Integer.parseInt(nrOfBlackBoxes);

            if (nrOfBlackBoxesInt <= 0  || nrOfBlackBoxesInt > MAX_BLACK_BOXES) {
                throw new ApiError(HttpStatus.BAD_REQUEST, INVALID_INPUT);
            }
        } catch (Exception e) {
            throw new ApiError(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }

        double rootExact = Math.sqrt(nrOfBlackBoxesInt);

        int rootAppr = (int) Math.sqrt(nrOfBlackBoxesInt);

        double res = rootExact - rootAppr;

        boolean isValid = res == 0.0;

        if (!isValid) {
            throw new ApiError(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }
    }
}
