package com.alperen.project.service.impl;

import com.alperen.project.model.dto.FileDTO;
import com.alperen.project.model.entity.File;
import com.alperen.project.model.request.file.FileSaveRequest;
import com.alperen.project.model.request.file.FileUpdateRequest;
import com.alperen.project.model.response.file.FileResponse;
import com.alperen.project.repository.FileRepository;
import com.alperen.project.service.interfaces.FileService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    FileRepository fileRepository;
    ModelMapper mapper;
    private final String basePath = "upload/";
    @Override
    public File getById(Long id) {
        return fileRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.findById(id).orElseThrow();
        fileRepository.deleteById(id);
    }

    @Override
    public File dto2entity(FileDTO fileDTO) {
        File file = new File();
        file.setCustomerId(fileDTO.getCustomerId());
        file.setName(fileDTO.getName());
        file.setPath(fileDTO.getPath());
        return file;
    }

    @Override
    public FileDTO entity2dto(File file) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setCustomerId(file.getCustomerId());
        fileDTO.setName(file.getName());
        fileDTO.setPath(file.getPath());
        return fileDTO;
    }

    @Override
    public FileResponse saveFile(FileSaveRequest request){
        try {
            if(!request.getFile().getContentType().contains("jpeg") && !request.getFile().getContentType().contains("pdf")){
                throw new RuntimeException("Dosya uzantınız sadece JPEG veya PDF olabilir.");
            }
            File file = new File();
            file = createFileToRequest(file,request);
            file.setContentType(request.getFile().getContentType());
            Path path = Paths.get(file.getPath());
            Files.write(path,request.getFile().getBytes());
            file = fileRepository.save(file);
            return fileToFileResponse(file);
        }catch (Exception e){
            throw new RuntimeException("Dosya kaydederken hata oluştu.");
        }
    }

    @Override
    public List<FileResponse> getAllFiles() {
        List<File> fileList = fileRepository.findAll();
        return fileListToResponseList(fileList);
    }

    @Override
    public FileResponse getFileById(Long id) {
        try {
            File file = getById(id);
            Path path = Paths.get(file.getPath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            FileResponse response = fileToFileResponse(file);
            response.setResource(resource);
            return response;
        }catch (Exception ex){
            throw new RuntimeException("Dosya getirilirken hata oluştu.");
        }
    }

    @Override
    public FileResponse updateFile(FileUpdateRequest updateRequest) {
        try {
            if(!updateRequest.getFile().getContentType().contains("jpeg")
                    && !updateRequest.getFile().getContentType().contains("pdf")
                    && !updateRequest.getFile().getContentType().contains("jpg")){
                throw new RuntimeException("Dosya uzantınız sadece JPEG veya PDF olabilir.");
            }
            File file = getById(updateRequest.getFileId());
            Path path = Paths.get(file.getPath());
            Files.delete(path);
            path = Paths.get(basePath+updateRequest.getFile().getOriginalFilename());
            file.setPath(path.toString());
            Files.write(path,updateRequest.getFile().getBytes());
            file = fileRepository.save(file);
            return fileToFileResponse(file);
        }catch (Exception e){
            throw new RuntimeException("Dosya kaydederken hata oluştu.");
        }
    }

    @Override
    public List<FileResponse> getAllFilesByCustomerId(Long id) {
        List<File> fileList = fileRepository.findAllByCustomerId(id);
        return fileListToResponseList(fileList);
    }

    public FileResponse fileToFileResponse(File file){
        FileResponse response = new FileResponse();
        response.setPath(file.getPath());
        response.setId(file.getId());
        response.setCustomerId(file.getCustomerId());
        response.setCreatedTime(file.getCreatedTime());
        response.setName(file.getName());
        response.setContentType(file.getContentType());
        return response;
    }

    public File createFileToRequest(File file,FileSaveRequest request){
        if(request.getFile().isEmpty()){ throw new RuntimeException("Dosya eklemelisiniz."); }
        String fileName = StringUtils.cleanPath(request.getFile().getOriginalFilename());
        Path path = Paths.get(basePath + fileName);
        file.setName(fileName);
        file.setContentType(request.getFile().getContentType());
        file.setPath(path.toString());
        file.setCustomerId(request.getCustomerId());
        return file;
    }
    public List<FileResponse> fileListToResponseList(List<File> fileList){
        if(fileList.isEmpty()) {throw new RuntimeException("Kayıtlı dosya bulunamadı."); }
        List<FileResponse> responseList = new ArrayList<>();
        for(File file : fileList){
            FileResponse response = new FileResponse();
            response.setId(file.getId());
            response.setName(file.getName());
            response.setCreatedTime(file.getCreatedTime());
            response.setPath(file.getPath());
            response.setContentType(file.getContentType());
            response.setCustomerId(file.getCustomerId());
            responseList.add(response);
        }
        return responseList;
    }
}
