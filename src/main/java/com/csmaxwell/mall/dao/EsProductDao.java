package com.csmaxwell.mall.dao;

import com.csmaxwell.mall.nosql.elasticsearch.document.EsProduct;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/4/24.
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
