package com.linku.backend.domain.postedtemplate;

import com.linku.backend.domain.BaseEntity;
import com.linku.backend.domain.template.IconSnapshot;
import com.linku.backend.domain.template.TemplateItemPosition;
import com.linku.backend.domain.template.TemplateItemSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posted_template_items")
@Getter @Setter
public class PostedTemplateItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postedTemplateItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_template_id", nullable = false)
    private PostedTemplate postedTemplate;

    private String name;

    private String siteUrl;

    @Embedded
    private TemplateItemPosition position;

    @Embedded
    private TemplateItemSize size;

    @Embedded
    private IconSnapshot icon;
}
