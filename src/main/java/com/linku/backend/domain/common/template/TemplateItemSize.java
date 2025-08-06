package com.linku.backend.domain.common.template;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class TemplateItemSize {
    private Integer width;
    private Integer height;
}