package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key){
        PageResult<Brand> result=brandService.queryBrandByPageAndSort(page,rows,sortBy,desc, key);
        if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam(value = "categories")List<Long> cids){
        brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

@GetMapping("delete")
    public ResponseEntity<String>  deleteItemById(@RequestParam("id")Long id){
        brandService.deleteItemById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //我的商品列表--->>新增商品
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> selectBrandByCategory(@PathVariable("cid") Long cid){
       List<Brand> list= brandService.selectBrandByCatetoryId(cid);
       if(list!=null&&list.size()>0){
           return ResponseEntity.ok(list);
       }else {
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

}
