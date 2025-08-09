package com.linku.backend.domain.common.template;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class TemplateItemPosition {
    private Integer x;
    private Integer y;
}