package com.yiyuplatform.entity.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.sql.Date;

/**
 * @description: 帖子搜索数据
 * @author: Xue Qianyi
 * @date: 2021-12-07 1:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "post-item")
public class ItemForESPost {
    @Id
    private String pId; //帖子ID

    @Field(type = FieldType.Keyword)
    private String ciAvatarturl; //头像,不需要分词

    @Field(type = FieldType.Keyword)
    private String pTitle;//标题，需要分词

    @Field(type = FieldType.Keyword)
    private String pLocation;//地点，不需要分词

    @Field(type = FieldType.Keyword)
    private String createTime; //日期，不需要分词

    @Field(type = FieldType.Keyword)
    private String ciNkname; //昵称，需要分词

    @Field(type = FieldType.Keyword)
    private String imageUrl; //图片本地路径（不带前缀）

}
