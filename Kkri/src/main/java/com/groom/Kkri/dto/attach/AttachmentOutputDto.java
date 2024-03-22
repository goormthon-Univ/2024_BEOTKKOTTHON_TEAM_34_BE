package com.groom.Kkri.dto.attach;

import com.groom.Kkri.entity.Attachment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentOutputDto {
    private Long imageId;
    private String fileUrl;

    public AttachmentOutputDto(Long imageId, String fileUrl) {
        this.imageId = imageId;
        this.fileUrl = fileUrl;
    }

}
