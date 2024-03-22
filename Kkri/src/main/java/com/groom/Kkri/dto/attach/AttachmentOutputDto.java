package com.groom.Kkri.dto.attach;

import com.groom.Kkri.entity.Attachment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentOutputDto {
    private Long id;
    private String storeFileName;

    public AttachmentOutputDto(Attachment attachment) {
        this.id = attachment.getId();
        this.storeFileName = attachment.getStoreFileName();
    }

}
