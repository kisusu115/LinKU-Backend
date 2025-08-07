package com.linku.backend.domain.postedtemplate.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class PostedTemplateResponse {

    private Long postedTemplateId;
    private String name;
    private Long ownerId;
    private String ownerName;
    private Integer height;

    private Integer likesCount;
    private Integer usageCount;

    private List<PostedTemplateItemResponse> items;
} 