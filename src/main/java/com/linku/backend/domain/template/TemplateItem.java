package com.linku.backend.domain.template;

import com.linku.backend.domain.BaseEntity;
import com.linku.backend.domain.icon.Icon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "template_items")
@Getter
@Setter
public class TemplateItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateItemId;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @ManyToOne
    @JoinColumn(name = "icon_id")
    private Icon icon;

    private String name;

    private String siteUrl;

    private String location;

    @Embedded
    private TemplateItemSize size;
}
