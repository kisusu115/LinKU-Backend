package com.linku.backend.domain.common.template;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class IconSnapshot {
    private String iconUrl;
    private String iconName;
    private Long originalIconId;    // 추적을 해야할까?
}