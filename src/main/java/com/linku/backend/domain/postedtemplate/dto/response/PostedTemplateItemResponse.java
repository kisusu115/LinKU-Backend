package com.linku.backend.domain.postedtemplate.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostedTemplateItemResponse {

    private Long postedTemplateItemId;
    private String name;
    private String siteUrl;

    private PostedTemplateItemPositionResponse position;
    private PostedTemplateItemSizeResponse size;
    private PostedTemplateItemIconResponse icon;
} 