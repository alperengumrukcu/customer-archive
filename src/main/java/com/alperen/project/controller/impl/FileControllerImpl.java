package com.alperen.project.controller.impl;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.controller.interfaces.FileController;
import com.alperen.project.model.request.file.FileSaveRequest;
import com.alperen.project.model.request.file.FileUpdateRequest;
import com.alperen.project.model.response.file.FileResponse;
import com.alperen.project.service.interfaces.FileService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FileControllerImpl implements FileController {
    FileService fileService;

    @Override
    public ResponseEntity<BaseResponse<List<FileResponse>>> getAllFiles() {
        List<FileResponse> responseList = fileService.getAllFiles();
        return BaseResponse.ok(responseList,HttpStatus.OK,"Tüm dosyaların listesi");
    }

    @Override
    public ResponseEntity<BaseResponse<FileResponse>> saveFile(FileSaveRequest request){
        FileResponse response = fileService.saveFile(request);
        return BaseResponse.ok(response, HttpStatus.OK,"Kayıt Başarılı");
    }

    @Override
    public ResponseEntity<?> getFileById(Long id){
        FileResponse response = fileService.getFileById(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(response.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getName() + "\"")
                .body(response.getResource());
    }

    @Override
    public ResponseEntity<BaseResponse<FileResponse>> updateFile(FileUpdateRequest updateRequest) {
        FileResponse response = fileService.updateFile(updateRequest);
        return BaseResponse.ok(response);
    }

    @Override
    public ResponseEntity<BaseResponse<List<FileResponse>>> getAllFileByCustomerId(Long id) {
        List<FileResponse> responseList = fileService.getAllFilesByCustomerId(id);
        return BaseResponse.ok(responseList,HttpStatus.OK,"Tüm dosyaların listesi");
    }
    @Override
    public ResponseEntity<BaseResponse<?>> deleteByFileId(Long id) {
        fileService.deleteById(id);
        return BaseResponse.ok(HttpStatus.OK);
    }
}
