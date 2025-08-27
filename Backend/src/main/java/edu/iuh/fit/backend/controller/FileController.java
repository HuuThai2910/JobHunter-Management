/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.controller;

import edu.iuh.fit.backend.dto.response.UploadFileResponse;
import edu.iuh.fit.backend.service.FileService;
import edu.iuh.fit.backend.util.annotaion.ApiMessage;
import edu.iuh.fit.backend.util.error.StorageException;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class FileController {
    private final FileService fileService;
    @Value("${thai.upload-file.base-uri}")
    private String baseURI;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")
    @ApiMessage("Upload single file successfully")
    public ResponseEntity<UploadFileResponse> upload(@RequestParam(name = "file", required = false) MultipartFile file,
                                                     @RequestParam("folder") String folder) throws URISyntaxException, IOException, StorageException {
//        Validate
        if(file == null || file.isEmpty()){
            throw new StorageException("File is empty. Please upload a file");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(item);
        });
        if(!isValid)
            throw new StorageException("Invalid file extension. Only allows: " + allowedExtensions.toString());
//        Tao mot thu muc chua file neu no chua ton tai
        this.fileService.createUploadFolder(baseURI + folder);
//        Store file
        String uploadFile = this.fileService.store(file, folder);
        UploadFileResponse uploadFileResponse = new UploadFileResponse(uploadFile, Instant.now());
        return ResponseEntity.ok(uploadFileResponse);
    }
}
