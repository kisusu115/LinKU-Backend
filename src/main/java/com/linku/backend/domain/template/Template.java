package com.linku.backend.domain.template;

import com.linku.backend.domain.common.BaseEntity;
import com.linku.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "templates")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Template extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    private String name;

    private Integer height; // Template의 width는 6으로 고정

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateItem> items = new ArrayList<>();
}