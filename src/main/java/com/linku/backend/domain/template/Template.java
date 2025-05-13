package com.linku.backend.domain.template;

import com.linku.backend.domain.BaseEntity;
import com.linku.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "templates")
@Getter
@Setter
public class Template extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String deletedAt;
}
