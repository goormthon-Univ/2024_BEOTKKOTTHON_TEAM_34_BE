package com.groom.Kkri.service;

import com.groom.Kkri.dto.attach.AttachmentUpdateDto;
import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.entity.Attachment;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.repository.AttachmentRepository;
import com.groom.Kkri.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final BoardRepository boardRepository;

    @Value("${spring.servlet.multipart.location}")
    private String fileDir;


    private String getFullPath(String filename){
        return fileDir + filename;
    }

    @Transactional
    public void storeImages(List<MultipartFile> multipartFiles, Long boardId) throws IOException {
        for(var s : multipartFiles){
            store(s,boardId);
        }
    }

    public List<AttachmentOutputDto> getImages(Long boardId){
        List<AttachmentOutputDto> result = new ArrayList<>();
        for (Attachment attachment : attachmentRepository.findByBoardId(boardId)) {
            result.add(new AttachmentOutputDto(attachment));
        }
        return result;
    }

    @Transactional
    public void updateImage(Long imageId,MultipartFile image) throws IOException {
        Attachment attachment = attachmentRepository.findById(imageId).get();

        attachment.setStoreFileName(image.getOriginalFilename());

        File file = new File(getFullPath(attachment.getUploadFileName()));
        image.transferTo(file);
    }

    private void store(MultipartFile image,Long boardId) throws IOException {
        Board board = boardRepository.findById(boardId).get();
        String originalFilename = image.getOriginalFilename();
        String uploadFileName = createStoreFilename(originalFilename);

        Attachment attachment = Attachment.builder()
                .board(board)
                .storeFileName(originalFilename)
                .uploadFileName(uploadFileName)
                .build();

        File uploadedFile = new File(getFullPath(uploadFileName));
        image.transferTo(uploadedFile);

        attachmentRepository.save(attachment);
    }

    private String createStoreFilename(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos+1);
        String uuid = UUID.randomUUID().toString();

        return uuid+"."+ext;
    }
}
