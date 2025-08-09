package com.linku.backend.domain.template.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class TemplateUpdateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer height;

    @Valid
    @NotNull
    private List<TemplateItemUpdateRequest> items;
}
