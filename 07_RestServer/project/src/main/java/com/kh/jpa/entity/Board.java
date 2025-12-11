package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private long boardNo;

    @Column(name = "board_title", length = 100, nullable = false)
    private String boardTitle;

    @JoinColumn(name = "board_writer", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member userId;

    @Column(name = "board_content", nullable = false)
    private String boardContent;

    @Column(name = "origin_name", length = 100)
    private String originName;

    @Column(name = "change_name", length = 100)
    private String changeName;

    @Column
    private int count;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(length = 1)
    private String status;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> reply = new ArrayList<>();
}
