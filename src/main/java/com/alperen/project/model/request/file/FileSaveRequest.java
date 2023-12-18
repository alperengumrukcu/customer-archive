package com.alperen.project.model.request.file;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class FileSaveRequest {
    @NotNull
    private MultipartFile file;
    @NotNull
    private Long customerId;
}
