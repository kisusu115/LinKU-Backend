package com.linku.backend.domain.template.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TemplateItemPositionRequest {

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;
}
