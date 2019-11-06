package com.leyou.item.web;

import com.leyou.item.pojo.Specification;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @GetMapping("{id}")
    public ResponseEntity<String> querySpecificationByCategoryId(@PathVariable("id")Long id){
       Specification specification= specificationService.queryById(id);

       if(specification==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

        return ResponseEntity.ok(specification.getSpecifications());
    }
    @PostMapping
    public ResponseEntity<String> saveSpecification(Specification specification){
        //没有就选择插入
      int i= specificationService.saveSpecification(specification);
         if (i==1){
             return ResponseEntity.status(HttpStatus.CREATED).build();
         }else {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
             }
         }
         @PutMapping
    public ResponseEntity<String> updateSpecification(Specification specification){
          int i=specificationService.updateSpecification(specification);
             if (i==1){
                 return ResponseEntity.status(HttpStatus.CREATED).build();
             }else {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
             }
         }
}
