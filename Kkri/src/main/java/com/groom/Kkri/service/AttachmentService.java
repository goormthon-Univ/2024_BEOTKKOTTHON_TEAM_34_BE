package com.groom.Kkri.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.groom.Kkri.config.S3Config;
import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.dto.attach.AttachmentUpdateDto;
import com.groom.Kkri.entity.Attachment;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.repository.AttachmentRepository;
import com.groom.Kkri.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final BoardRepository boardRepository;
    private final S3Config s3Config;

    @Value("${spring.servlet.multipart.location}")
    private String fileDir;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    private String getFullPath(String filename){
        return fileDir + filename;
    }

//    @Transactional
    public List<String> storeImages(List<MultipartFile> multipartFiles, Long boardId) throws IOException {
        List<String> result = new ArrayList<>();
        for(var s : multipartFiles){
            result.add(store(s,boardId));
        }
        return result;
    }

    public List<AttachmentOutputDto> getImages(Long boardId){

        List<AttachmentOutputDto> result = new ArrayList<>();
        for (Attachment attachment : attachmentRepository.findByBoardId(boardId)) {
            result.add(new AttachmentOutputDto(attachment.getId(),attachment.getS3url()));
        }

        return result;
    }
    public AttachmentOutputDto getImage(Long boardId){

        List<Attachment> attachments = attachmentRepository.findByBoardId(boardId);

        if(attachments.isEmpty()){
            return null;
        }

        Attachment attachment = attachments.get(0);

        return new AttachmentOutputDto(attachment.getId(), attachment.getS3url());
    }

    public List<String> updateImages(Map<String,MultipartFile> images) throws IOException {
        List<String> result = new ArrayList<>();
        for(var s :images.keySet()){
            Long key = Long.parseLong(s);
            result.add(updateImage(key,images.get(s)));
        }
        return result;
    }
//    @Transactional
    public String updateImage(Long imageId,MultipartFile image) throws IOException {
        Attachment attachment = attachmentRepository.findById(imageId).get();

        attachment.setStoreFileName(image.getOriginalFilename());

        File file = new File(getFullPath(attachment.getUploadFileName()));
        image.transferTo(file);

        String s3Url = getS3Url(attachment.getUploadFileName(), file);

        file.delete();

        attachment.setS3url(s3Url);
        return s3Url;
    }

    private String store(MultipartFile image,Long boardId) throws IOException {
        Board board = boardRepository.findById(boardId).get();
        String originalFilename = image.getOriginalFilename();
        String uploadFileName = createStoreFilename(originalFilename);

        File uploadedFile = new File(getFullPath(uploadFileName));
        image.transferTo(uploadedFile);

        String s3Url = getS3Url(uploadFileName, uploadedFile);

        uploadedFile.delete();


        Attachment attachment = Attachment.builder()
                .board(board)
                .storeFileName(originalFilename)
                .uploadFileName(uploadFileName)
                .s3url(s3Url)
                .build();

        attachmentRepository.save(attachment);

        return s3Url;
    }

    private String getS3Url(String uploadFileName, File uploadedFile) {
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uploadFileName, uploadedFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uploadFileName).toString();
        return s3Url;
    }

    private String createStoreFilename(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos+1);
        String uuid = UUID.randomUUID().toString();

        return uuid+"."+ext;
    }
}
