package com.jin.common;

import cn.hutool.core.codec.Base64;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadGiteeImgBed {

    /**
     * 码云私人令牌
     */
    public static final String ACCESS_TOKEN = "e4c40df3b9bf4022d6e42c51d43b3937";

    /**
     * 码云个人空间名
     */
    public static final String OWNER = "jinbug";

    /**
     * 上传指定仓库
     */
    public static final String REPO = "mvmanager";


    /**
     * 上传时指定存放图片路径
     */
    public static final String PATH = "/uploadimg/"+ DateUtils.getYearMonth()+"/"; //使用到了日期工具类


    /**
     * 用于提交描述
     */
    public static final String ADD_MESSAGE = "add img";
    public static final String DEL_MESSAGE = "DEL img";

    //API
    /**
     * 新建(POST)、获取(GET)、删除(DELETE)文件：()中指的是使用对应的请求方式
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    public static final String API_CREATE_POST = "https://gitee.com/api/v5/repos/"+OWNER+"/"+REPO+"/contents/"+PATH;

    public static final String API_CREATE = "https://gitee.com/"+OWNER+"/"+REPO+"/blob/master/"+PATH;


    /**
     * 生成创建(获取、删除)的指定文件路径
     * @param originalFilename
     * @return
     */
    public static String createUploadFileUrl(String originalFilename){
        //填充请求路径
        String url = String.format(UploadGiteeImgBed.API_CREATE_POST,
                UploadGiteeImgBed.OWNER,
                UploadGiteeImgBed.REPO,
                UploadGiteeImgBed.PATH+originalFilename);
        return url;
    }

    public static String uploadFileUrl(String originalFilename){
        //填充请求路径
        String url = String.format(UploadGiteeImgBed.API_CREATE,
                UploadGiteeImgBed.OWNER,
                UploadGiteeImgBed.REPO,
                UploadGiteeImgBed.PATH+originalFilename);
        return url;
    }

    /**
     * 获取创建文件的请求体map集合：access_token、message、content
     * @param multipartFile 文件字节数组
     * @return 封装成map的请求体集合
     */
    public static Map<String,Object> getUploadBodyMap(byte[] multipartFile){
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token",UploadGiteeImgBed.ACCESS_TOKEN);
        bodyMap.put("message", UploadGiteeImgBed.ADD_MESSAGE);
        bodyMap.put("content", Base64.encode(multipartFile));
        return bodyMap;
    }

}
