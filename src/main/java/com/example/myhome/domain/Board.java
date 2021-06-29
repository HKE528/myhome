package com.example.myhome.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "tb_board")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotEmpty @Size(min = 2, max = 50, message = "제목은 2글자 이상, 50 글자 미만 이어야 합니다.")
    private String title;

    @Column(length = 10000)
    private String content;
}
