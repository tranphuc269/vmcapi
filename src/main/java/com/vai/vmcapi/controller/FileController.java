package com.vai.vmcapi.controller;


import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.service.impl.FileService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseDTO<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseDTO.success(fileService.uploadFile(file));
    }
}
