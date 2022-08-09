package com.example.interview.mapper;

import com.example.interview.dto.DetailDTO;
import com.example.interview.entity.LinkNameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DTOToEntityMapper {


  List<LinkNameEntity>  convertListOfDTOTOEntity(List<DetailDTO> detailDTOS);

  List<DetailDTO>  convertListOfEntityToDTO(List<LinkNameEntity> linkNameEntities);

  DetailDTO  convertEntityToDTO(LinkNameEntity linkNameEntity);

  LinkNameEntity convertDTOTOEntity(DetailDTO detailDTOS);

}
