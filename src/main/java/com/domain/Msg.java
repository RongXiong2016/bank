package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author 范正荣
 * @Date 2018/3/24 0024 12:38.
 */
@Entity
@Getter
@Setter
@ToString
public class Msg {
        private String title;
        private String content;
        private String etraInfo;

    public Msg(String title, String content, String etraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }
}
