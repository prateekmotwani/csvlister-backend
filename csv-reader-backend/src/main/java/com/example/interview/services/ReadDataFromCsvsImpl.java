package com.example.interview.services;

import com.example.interview.dto.DetailDTO;
import com.example.interview.entity.LinkNameEntity;
import com.example.interview.iface.IReadDataFromCsvs;
import com.example.interview.mapper.DTOToEntityMapper;
import com.example.interview.repository.NameLinkRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReadDataFromCsvsImpl implements IReadDataFromCsvs {


    @Autowired
    NameLinkRepository nameLinksRepository;

    private DTOToEntityMapper dtoToEntityMapper
            = Mappers.getMapper(DTOToEntityMapper.class);




    private File getResourceFile(final String fileName)
    {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        if(url == null) {
            throw new IllegalArgumentException(fileName + " is not found expected place");
        }

        File file = new File(url.getFile());

        return file;
    }

    @Override
    public Map<String, String> readDataFromCSVContainingNameAndLink(String file) {

        log.info("Reading Configuration from csv file after server startup");

        Map<String, String> mapOfNameAndLinks=new HashMap<>();
            try {
                // Create an object of file reader
                // class with CSV file as a parameter.
                FileReader filereader = new FileReader(getResourceFile(file));

                // create csvReader object and skip first Line
                CSVReader csvReader = new CSVReaderBuilder(filereader)
                        .withSkipLines(1)
                        .build();
                List<String[]> allData = csvReader.readAll();
                log.info("using csv reader to read the data from csv and is of size ",allData.size());

                // print Data
                for (String[] row : allData) {

                    mapOfNameAndLinks.put(row[0],row[1]);

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        log.info("map of name and links created and is of size ",mapOfNameAndLinks.size());

            return mapOfNameAndLinks;
        }

    @Override
    public List<DetailDTO> readDataFromDatabase() {

      List<LinkNameEntity> linkNameEntities=  nameLinksRepository.findAll();

        log.info("entries retrived from database and fed to mapper ",linkNameEntities.size());

        return dtoToEntityMapper.convertListOfEntityToDTO(linkNameEntities);
    }

}
