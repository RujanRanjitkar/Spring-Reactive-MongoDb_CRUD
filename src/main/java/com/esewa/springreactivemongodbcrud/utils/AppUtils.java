package com.esewa.springreactivemongodbcrud.utils;

import com.esewa.springreactivemongodbcrud.dto.ProductDto;
import com.esewa.springreactivemongodbcrud.model.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto modelTODto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToModel(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
