package com.alperen.project.repository;

import com.alperen.project.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findAllByCustomerId(Long customerId);
}
