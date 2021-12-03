package com.coranavirus.coronavirus.DTO.news;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class NewResponse {
    private String newId;
    private String title;
    private String shortDescription;
    private Timestamp time;
    private String linkAvatar;
    private String linkDetail;
}
