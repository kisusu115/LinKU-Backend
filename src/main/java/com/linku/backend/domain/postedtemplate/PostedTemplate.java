package com.linku.backend.domain.postedtemplate;

import com.linku.backend.domain.common.BaseEntity;
import com.linku.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posted_templates")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    private Integer likesCount = 0;

    @Builder.Default
    private Integer usageCount = 0;

    @Builder.Default
    @OneToMany(mappedBy = "postedTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostedTemplateItem> items = new ArrayList<>();

    private LocalDateTime deletedAt;
}
