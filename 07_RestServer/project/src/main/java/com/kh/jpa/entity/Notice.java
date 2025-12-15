package com.kh.jpa.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "NOTICE")


public class Notice extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Long noticeNo;

    @Column(name = "notice_title", length = 30, nullable = false)
    private String noticeTitle;

    @JoinColumn(name = "notice_writer", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member noticeWriter;

    @Column(name = "notice_content", length = 200, nullable = false)
    private String noticeContent;

}
