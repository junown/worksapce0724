package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_name", length = 30, nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tagId", cascade = CascadeType.ALL)
    private List<Board_Tag> boardTags = new ArrayList<>();
}
