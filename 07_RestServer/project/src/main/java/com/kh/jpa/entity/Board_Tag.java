package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "BOARD_TAG")
public class Board_Tag {
    @Id
    @JoinColumn(name = "board_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board boardNo;

    @Id
    @JoinColumn(name = "tag_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tagId;

}
