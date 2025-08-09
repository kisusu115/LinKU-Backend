package com.linku.backend.domain.postedtemplate.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostedTemplateListResponse {

    private Long postedTemplateId;
    private String name;
    private Long ownerId;
    private String ownerName;
    private Integer height;

    private Integer likesCount;
    private Integer usageCount;

    private Integer items;
}

