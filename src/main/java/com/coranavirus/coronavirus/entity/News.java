package com.coranavirus.coronavirus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    private String newId;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String shortDescription;
    private Timestamp time;
    private String linkAvatar;
    private String linkDetail;
}
