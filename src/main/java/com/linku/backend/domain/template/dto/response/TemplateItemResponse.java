package com.linku.backend.domain.template.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TemplateItemResponse {

    private Long templateItemId;
    private String name;
    private String siteUrl;

    private TemplateItemPositionResponse position;
    private TemplateItemSizeResponse size;
    private TemplateItemIconResponse icon;
}
