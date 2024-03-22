package com.groom.Kkri.dto.attach;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class AttachmentUpdateDto {
    Long id;
    MultipartFile multipartFile;

    public AttachmentUpdateDto(Long id, MultipartFile multipartFile){
        this.id = id;
        this.multipartFile = multipartFile;
    }

}
