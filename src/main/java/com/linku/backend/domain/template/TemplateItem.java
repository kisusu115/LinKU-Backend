package com.linku.backend.domain.template;

import com.linku.backend.domain.common.BaseEntity;
import com.linku.backend.domain.common.template.IconSnapshot;
import com.linku.backend.domain.common.template.TemplateItemPosition;
import com.linku.backend.domain.common.template.TemplateItemSize;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "template_items")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TemplateItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    private String name;

    private String siteUrl;

    @Embedded
    private TemplateItemPosition position;

    @Embedded
    private TemplateItemSize size;

    @Embedded
    private IconSnapshot icon;

    private LocalDateTime deletedAt;
}