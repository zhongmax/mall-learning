package com.csmaxwell.mall.service;

import com.csmaxwell.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/4/23
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
