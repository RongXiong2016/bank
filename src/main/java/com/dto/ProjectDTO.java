package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/2/19 0019 19:24.
 */
@Getter
@Setter
public class ProjectDTO {

    private Long id;

    private String name;

    private String description;

    private Long createAdminId;

    private String createAdminName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
