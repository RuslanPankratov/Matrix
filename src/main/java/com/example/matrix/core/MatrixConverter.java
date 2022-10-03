package com.example.matrix.core;

import com.example.matrix.dto.MatrixRequest;
import com.example.matrix.dto.MatrixResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class MatrixConverter {

    private static final Integer INTERVAL = 4;

    public MatrixResponse mirror(MatrixRequest request) {
        Map<Integer, List<Integer>> result = new HashMap<>();

        for (int i = 0; i < request.getMatrixMap().size(); i++) {
            result.put(i, convertListMirror(request.getMatrixMap().get(i)));
        }
        log.debug("Mirror result: {}", result);
        return new MatrixResponse(result);
    }


    public MatrixResponse mirrorDiagonal(MatrixRequest request) {
        List<Integer> arr = convertList(request);
        List<Integer> arrList = arrList(request, arr);
        Map<Integer, List<Integer>> map = convertMapList(request, arrList);
        return new MatrixResponse(map);
    }

    private List<Integer> convertListMirror(List<Integer> arr) {
        List<Integer> result = new ArrayList<>();

        for (int i = arr.size() - 1; i > -1; i--) {
            result.add(arr.get(i));
        }
        log.debug("List result: {}", result);
        return result;
    }


    private List<Integer> convertList(MatrixRequest request) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> matrixMap = request.getMatrixMap();

        for (int i = 0; i < matrixMap.size(); i++) {
            list.addAll(matrixMap.get(i));
        }
        log.debug("List result: {}", list);
        return list;
    }


    private List<Integer> arrList(MatrixRequest request, List<Integer> arr) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> matrixMap = request.getMatrixMap();

        for (int i = 0; i < matrixMap.size(); i++) {
            int flag = i;
            for (int j = 0; j < matrixMap.get(i).size(); j++) {
                list.add(arr.get(flag));
                flag += INTERVAL;
            }
        }
        log.debug("List result: {}", list);
        return list;
    }


    private Map<Integer, List<Integer>> convertMapList(MatrixRequest request, List<Integer> arrList) {
        Map<Integer, List<Integer>> map = convertMap(request.getMatrixMap().size());
        int count = 0;

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.size(); j++) {
                map.get(i).add(arrList.get(count));
                count++;
            }
        }
        log.debug("Map result: {}", map);
        return map;
    }

    private Map<Integer, List<Integer>> convertMap(Integer num) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < num; i++) {
            map.put(i, new ArrayList<>());
        }
        log.debug("Map result: {}", map);
        return map;
    }


}