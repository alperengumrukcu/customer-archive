package com.alperen.project.controller.interfaces;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.model.request.file.FileSaveRequest;
import com.alperen.project.model.request.file.FileUpdateRequest;
import com.alperen.project.model.response.file.FileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/file")
public interface FileController {
    @GetMapping("/all")
    ResponseEntity<BaseResponse<List<FileResponse>>> getAllFiles();
    @PostMapping("/upload")
    ResponseEntity<BaseResponse<FileResponse>> saveFile(FileSaveRequest request);
    @GetMapping("/{id}")
    ResponseEntity<?> getFileById(@PathVariable("id") Long id);
    @PostMapping("/update")
    ResponseEntity<BaseResponse<FileResponse>> updateFile(FileUpdateRequest updateRequest);
    @GetMapping("/customer_id")
    ResponseEntity<BaseResponse<List<FileResponse>>> getAllFileByCustomerId(@RequestParam("customerId") Long id);
}
