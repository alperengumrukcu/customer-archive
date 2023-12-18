package com.alperen.project.base;

public interface BaseService<ENTITY, DTO> {


    ENTITY getById(Long id);
    void deleteById(Long id);
    ENTITY dto2entity(DTO dto);
    DTO entity2dto(ENTITY entity);
}
