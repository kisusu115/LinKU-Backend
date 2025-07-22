package com.linku.backend.domain.postedtemplate;

import com.linku.backend.domain.common.BaseEntity;
import com.linku.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posted_templates")
@Getter @Setter
public class PostedTemplate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postedTemplateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    private String name;

    private Integer height;

    private Integer likesCount = 0;
    private Integer usageCount = 0;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "postedTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostedTemplateItem> items = new ArrayList<>();
}
