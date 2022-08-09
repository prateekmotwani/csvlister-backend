package com.example.interview.iface;

import com.example.interview.dto.DetailDTO;

import java.util.List;
import java.util.Map;

public interface IReadDataFromCsvs {

    public Map<String,String> readDataFromCSVContainingNameAndLink(String file);

    public List<DetailDTO> readDataFromDatabase();

}
