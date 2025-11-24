package com.kh.spring.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("mgisToiletPoi")
public class ToiletListResponse {
    @JsonProperty("list_total_count")
    private int listTotalCount;

    private List<Toilet> row;
}
