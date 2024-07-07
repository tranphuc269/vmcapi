package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.document.DocumentVO;
import com.vai.vmcapi.service.IDocumentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/documents")
public class DocumentController {

    @Resource
    private IDocumentService documentService;
    @PostMapping
    public ResponseDTO<DocumentVO> createDocument(@RequestParam(name = "file") MultipartFile file,
                                                  @RequestParam(name = "dossierId") Long dossierId) {
        return ResponseDTO.success(documentService.createDocument(file, dossierId));
    }

    @GetMapping(params = { "page", "pageSize" })
    public ResponseDTO<PageableResponse<DocumentVO>> getDocuments(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                      @RequestParam("dossierId") Long dossierId,
                                                      @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        return ResponseDTO.success(documentService.getDocuments(page, pageSize, dossierId, keyword));
    }
}
