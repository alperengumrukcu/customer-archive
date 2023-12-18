package com.alperen.project.service.interfaces;

import com.alperen.project.base.BaseService;
import com.alperen.project.model.dto.FileDTO;
import com.alperen.project.model.entity.File;
import com.alperen.project.model.request.file.FileSaveRequest;
import com.alperen.project.model.request.file.FileUpdateRequest;
import com.alperen.project.model.response.file.FileResponse;

import java.util.List;

public interface FileService extends BaseService<File, FileDTO> {
    FileResponse saveFile(FileSaveRequest request);

    List<FileResponse> getAllFiles();

    FileResponse getFileById(Long id);

    FileResponse updateFile(FileUpdateRequest updateRequest);

    List<FileResponse> getAllFilesByCustomerId(Long id);
}
