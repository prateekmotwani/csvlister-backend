package com.example.interview.services;

import com.example.interview.dto.DetailDTO;
import com.example.interview.entity.LinkNameEntity;
import com.example.interview.iface.IReadDataFromCsvs;
import com.example.interview.mapper.DTOToEntityMapper;
import com.example.interview.repository.NameLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DumpDataIntoDatabaseTableOnServerStartImpl {

    @Autowired
    IReadDataFromCsvs readDataFromCsvs;

    @Autowired
    NameLinkRepository nameLinksRepository;

    private DTOToEntityMapper dtoToEntityMapper
            = Mappers.getMapper(DTOToEntityMapper.class);

    final static String CSV_FILE_Constant = "people.csv";

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        log.info("Reading Configuration from csv file after server startup");
        try {
            Map<String, String> dataProcessed = readDataFromCsvs.readDataFromCSVContainingNameAndLink(CSV_FILE_Constant);
            List<DetailDTO> list = new ArrayList<>();
            for (Map.Entry entry : dataProcessed.entrySet()) {
                DetailDTO dto = new DetailDTO();
                dto.setName(String.valueOf(entry.getKey()));
                dto.setLink(String.valueOf(entry.getValue()));
                list.add(dto);
            }
            List<LinkNameEntity> listToSave = dtoToEntityMapper.convertListOfDTOTOEntity(list);
            nameLinksRepository.saveAll(listToSave);
        } catch (Exception exception) {
            log.error(" Configuration not loaded successfully in the database due to following errors  ", exception);
        }
        log.info(" Configuration loaded from  csv file and saved in database ");

    }
}


