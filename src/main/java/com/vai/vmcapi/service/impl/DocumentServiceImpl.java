package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.document.DocumentVO;
import com.vai.vmcapi.repo.entity.DocumentEntity;
import com.vai.vmcapi.repo.jpa.JpaDocumentRepository;
import com.vai.vmcapi.service.IDocumentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DocumentServiceImpl implements IDocumentService {

    @Resource
    private JpaDocumentRepository documentRepository;

    @Override
    public DocumentVO createDocument(MultipartFile file, Long dossierId) {
        return null;
    }

    @Override
    public PageableResponse<DocumentVO> getDocuments(int page,
                                                     int pageSize,
                                                     Long dossierId,
                                                     String keyword) {

        Page<DocumentEntity> entityPage = documentRepository
                .findByDossierIdAndNameContainingOrderByCreatedAt(dossierId, keyword, PageRequest.of(page, pageSize));
        return PageableResponse.<DocumentVO>builder()
                .page(page)
                .pageSize(pageSize)
                .totalSize(entityPage.getTotalPages())
                .list(entityPage.get().map(DocumentEntity::toVO).toList())
                .build();
    }
}
