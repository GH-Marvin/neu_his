package com.edu.neu.dto;

import lombok.Data;

import java.util.List;

@Data
public class WestDiagnoseDTO<T> {
    private String time;
    private List<T> data;
}
