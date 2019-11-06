package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),
    CATEGORY_NOT_FOND(404,"没有查到分类信息"),
    BRAND_NOT_FOUND(404,"没有找到商品"),
    BRAND_SAVE_ERROR(500,"新增商品失败"),
    ;
    private int code;
    private String msg;
}
