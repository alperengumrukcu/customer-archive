package com.alperen.project.model.request.file;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class FileUpdateRequest {
    @NotNull
    private Long fileId;
    @NotNull
    private MultipartFile file;
}
