package com.groom.Kkri.dto.attach;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class AttachmentUpdateDto {
    private Long imageId;
    private MultipartFile image;

}
