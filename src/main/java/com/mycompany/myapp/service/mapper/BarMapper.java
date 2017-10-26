package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bar and its DTO BarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BarMapper extends EntityMapper<BarDTO, Bar> {

    

    

    default Bar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bar bar = new Bar();
        bar.setId(id);
        return bar;
    }
}
