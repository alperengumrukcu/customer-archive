package com.alperen.project.model.response.file;

import lombok.Data;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;
@Data
public class FileResponse {
    private Long id;
    private String name;
    private String path;
    private LocalDateTime createdTime;
    private Long customerId;
    private Resource resource;
    private String contentType;
}
