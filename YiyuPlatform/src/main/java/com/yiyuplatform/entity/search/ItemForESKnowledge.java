package com.yiyuplatform.entity.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

/**
 * @description: 知识搜索数据
 * @author: Xue Qianyi
 * @date: 2021-12-06 0:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "knowledge-item")
public class ItemForESKnowledge {
    @Id
    private Integer knoCardId;

    //标题，需要分词
    @Field(type = FieldType.Keyword)
    private String knoCardTitle;

    //类别不需要分词
    @Field(type = FieldType.Keyword)
    private String categoryName;

    //图片不需要分词
    @Field(type = FieldType.Keyword)
    private String knoCardIcon;

    //文章内容，可以分词
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String knoCardContent;

    //作者可以分词
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String knoCardAuthor;

    @Field(type = FieldType.Keyword)
    private Integer knoCardRead;

    @Field(type = FieldType.Keyword)
    private Integer knoCardLikes;

    @Field(type = FieldType.Keyword)
    private Integer knoIshot;
}
