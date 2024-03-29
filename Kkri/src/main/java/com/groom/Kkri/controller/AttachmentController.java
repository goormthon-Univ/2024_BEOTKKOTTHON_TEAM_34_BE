package com.groom.Kkri.controller;

import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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


    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "게시판에 이미지를 저장하는 api", description = "테스트용 쓰이진 않을 듯")
    public ResponseEntity<List<String>> storeAttachment(@RequestParam("boardId") Long boardId , @RequestParam("images") List<MultipartFile> multipartFiles) throws IOException {

        try{
            return ResponseEntity.ok(attachmentService.storeImages(multipartFiles, boardId));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "게시판에 이미지를 수정하는 api", description = "테스트용 쓰이진 않을 듯")
    public ResponseEntity<String> updateAttachment(@RequestParam("imageId") Long imageId , @RequestParam("image") MultipartFile image){
        try{
            return ResponseEntity.ok(attachmentService.updateImage(imageId,image));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


}
