package com.example.matrix.core;

import com.example.matrix.dto.MatrixRequest;
import com.example.matrix.dto.MatrixResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class MatrixConverterTest {
    private final MatrixConverter matrixConverter = new MatrixConverter();

    @Test
    void mirrorTest() {
        MatrixResponse result = matrixConverter.mirror(convert());
        MatrixResponse expectedResult = convertResponse();

        assertArrayEquals(expectedResult.getMap().keySet().toArray(), result.getMap().keySet().toArray());
    }


    @Test
    void mirrorDiagonalTest() {
        MatrixResponse result = matrixConverter.mirrorDiagonal(convert());
        MatrixResponse expectedResult = convertResponseDiagonal();

        assertArrayEquals(expectedResult.getMap().keySet().toArray(), result.getMap().keySet().toArray());
    }


    private MatrixRequest convert() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(1, 2, 3, 4));
        map.put(1, List.of(5, 6, 7, 8));
        map.put(2, List.of(9, 10, 11, 12));
        map.put(3, List.of(13, 14, 15, 16));

        return new MatrixRequest(map);
    }

    private MatrixResponse convertResponse() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(4, 3, 2, 1));
        map.put(1, List.of(8, 7, 6, 5));
        map.put(2, List.of(12, 11, 10, 9));
        map.put(3, List.of(16, 15, 14, 13));

        return new MatrixResponse(map);
    }


    private MatrixResponse convertResponseDiagonal() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(1, 5, 9, 13));
        map.put(1, List.of(2, 6, 10, 14));
        map.put(2, List.of(3, 7, 11, 15));
        map.put(3, List.of(4, 8, 12, 16));

        return new MatrixResponse(map);
    }
}