package com.jin.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_movie
 */
@TableName(value ="t_movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String movieName;

    /**
     * 
     */
    private String movieType;

    /**
     * 
     */
    private String actor;

    /**
     * 
     */
    private String director;

    /**
     * 
     */
    private Date mins;

    /**
     * 
     */
    private Date moviedate;

    /**
     * 
     */
    private String filePath;

    /**
     * 
     */
    private Integer newsId;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String createUser;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateUser;

    /**
     * 0表示未被删除，1表示已经被删除
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}