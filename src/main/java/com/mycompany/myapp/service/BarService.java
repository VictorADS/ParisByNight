package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Bar;
import com.mycompany.myapp.repository.BarRepository;
import com.mycompany.myapp.service.dto.BarDTO;
import com.mycompany.myapp.service.mapper.BarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Bar.
 */
@Service
@Transactional
public class BarService {

    private final Logger log = LoggerFactory.getLogger(BarService.class);

    private final BarRepository barRepository;

    private final BarMapper barMapper;

    public BarService(BarRepository barRepository, BarMapper barMapper) {
        this.barRepository = barRepository;
        this.barMapper = barMapper;
    }

    /**
     * Save a bar.
     *
     * @param barDTO the entity to save
     * @return the persisted entity
     */
    public BarDTO save(BarDTO barDTO) {
        log.debug("Request to save Bar : {}", barDTO);
        Bar bar = barMapper.toEntity(barDTO);
        bar = barRepository.save(bar);
        return barMapper.toDto(bar);
    }

    /**
     *  Get all the bars.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<BarDTO> findAll() {
        log.debug("Request to get all Bars");
        return barRepository.findAll().stream()
            .map(barMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one bar by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public BarDTO findOne(Long id) {
        log.debug("Request to get Bar : {}", id);
        Bar bar = barRepository.findOne(id);
        return barMapper.toDto(bar);
    }

    /**
     *  Delete the  bar by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Bar : {}", id);
        barRepository.delete(id);
    }
}
