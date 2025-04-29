package com.linku.backend.domain.alert;

import com.linku.backend.domain.BaseEntity;
import com.linku.backend.domain.page.Page;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
public class Alert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    private String url;

    private String title;

    private LocalDateTime postTime; // created_at과 다른 게시글 올라온 시간 -> 크롤링 시에 치환해주는 로직 필요

    private String content;
}
