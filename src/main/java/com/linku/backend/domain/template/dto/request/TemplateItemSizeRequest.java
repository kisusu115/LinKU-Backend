package com.linku.backend.domain.template.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TemplateItemSizeRequest {

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;
}
