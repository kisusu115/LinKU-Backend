package com.linku.backend.domain.common.template;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TemplateItemSize {

    private Integer width;

    private Integer height;
}