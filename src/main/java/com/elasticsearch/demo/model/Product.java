package com.elasticsearch.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products", shards = 1, versionType = VersionType.INTERNAL, createIndex = false)
@Setting(settingPath = "/elasticsearch/settings.json") // czy potrzebne własne analizery
public class Product {

    public static final String INDEX_NAME = "products";

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Keyword)
    private String ean;
    @Field(type = FieldType.Keyword)
    private String sku;
    @Field(type = FieldType.Keyword)
    private String description;
    @Field(type = FieldType.Double)
    private Double quantity;
    @Field(type = FieldType.Double, name = "tax_rate")
    private Double taxRate;
    @Field(type = FieldType.Double)
    private Double weight;
    //    private List<String> images;
    @Field(name = "delivery_schema_id", type = FieldType.Integer)
    private Long deliverySchemaId;
    @Field(name = "category_id", type = FieldType.Long)
    private Long categoryId;

//    @Field(type = FieldType.Nested, includeInParent = true)
//    private Attributes attributes;
//
//    @Field(type = FieldType.Nested, includeInParent = true)
//    private VariantGroup variantGroup;
}
