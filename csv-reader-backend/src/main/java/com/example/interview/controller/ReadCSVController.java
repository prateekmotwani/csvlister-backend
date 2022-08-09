package com.example.interview.controller;

import com.example.interview.dto.DetailDTO;
import com.example.interview.entity.LinkNameEntity;
import com.example.interview.iface.IReadDataFromCsvs;

import com.example.interview.mapper.DTOToEntityMapper;
import com.example.interview.repository.NameLinkRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/csvdata")
@CrossOrigin(origins = "http://localhost:8080")
public class ReadCSVController {

    @Autowired
    IReadDataFromCsvs readDataFromCsvs;

    @Autowired
    NameLinkRepository nameLinksRepository;

    private DTOToEntityMapper dtoToEntityMapper
            = Mappers.getMapper(DTOToEntityMapper.class);

    final static String CSV_FILE_Constant = "people.csv";

    @GetMapping
    public List<DetailDTO> getAccounts() {

        return readDataFromCsvs.readDataFromDatabase();



    }


}
