package com.example.matrix.controller;

import com.example.matrix.core.MatrixConverter;
import com.example.matrix.dto.MatrixRequest;
import com.example.matrix.dto.MatrixResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/matrix")
@AllArgsConstructor
public class MatrixController {

    private MatrixConverter matrixConverter;

    @PostMapping("/mirror")
    public MatrixResponse mirror(@RequestBody @Valid MatrixRequest request) {
        log.debug("Received matrix request: {}", request);
        return matrixConverter.mirror(request);
    }

    @PostMapping("mirror/diagonal")
    public MatrixResponse mirrorDiagonal(@RequestBody @Valid MatrixRequest request) {
        log.debug("Received matrix request: {}", request);
        return matrixConverter.mirrorDiagonal(request);
    }
}
