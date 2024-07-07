package com.vai.vmcapi.service;


import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.document.DocumentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDocumentService {
    DocumentVO createDocument(MultipartFile file, Long dossierId);

    PageableResponse<DocumentVO> getDocuments(int page, int pageSize, Long dossierId, String keyword);
}
