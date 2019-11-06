package com.leyou.item.service;

import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;
    public Specification queryById(Long id) {
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        return specification;
    }

    public int  saveSpecification(Specification specification) {
      int i=  specificationMapper.insert(specification);
        if(i==1){
            return 1;
        }else {
            return 0;
        }
    }

    public int updateSpecification(Specification specification) {
       int i=specificationMapper.updateByPrimaryKey(specification);
       if(i==1){
           return 1;
       }else {
           return 0;
       }
    }


}
