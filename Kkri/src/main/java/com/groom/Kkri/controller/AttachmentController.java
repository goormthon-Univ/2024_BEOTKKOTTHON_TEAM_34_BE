package com.groom.Kkri.controller;

import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.dto.attach.AttachmentUpdateDto;
import com.groom.Kkri.repository.AttachmentRepository;
import com.groom.Kkri.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "이미지 관련 api")
@RequestMapping("/api/attach")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping
    @Operation(summary = "게시판에 관련된 이미지를 가져오는 api", description = "")
    public ResponseEntity<List<AttachmentOutputDto>> getAttachment(@RequestParam("boardId") Long boardId){
        return ResponseEntity.ok(attachmentService.getImages(boardId));
    }

//    @PostMapping(value = "/test",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//    @Operation(summary = "test")
//    public void test(@RequestPart("file") MultipartFile file) {
//        System.out.println(file.getOriginalFilename());
//    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "게시판에 이미지를 저장하는 api", description = "테스트용 쓰이진 않을 듯")
    public ResponseEntity<String> storeAttachment(@RequestParam("boardId") Long boardId , @RequestParam("images") List<MultipartFile> multipartFiles) throws IOException {
        System.out.println(boardId);
        for(var s : multipartFiles){
            log.info(s.getInputStream().toString());
        }
        try{
            attachmentService.storeImages(multipartFiles,boardId);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "게시판에 이미지를 수정하는 api", description = "테스트용 쓰이진 않을 듯")
    public ResponseEntity<String> updateAttachment(@RequestParam("imageId") Long imageId , @RequestParam("image") MultipartFile image){
        try{
            attachmentService.updateImage(imageId,image);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


}
