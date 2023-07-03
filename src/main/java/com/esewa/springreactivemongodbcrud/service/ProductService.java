package com.esewa.springreactivemongodbcrud.service;

import com.esewa.springreactivemongodbcrud.dto.ProductDto;
import com.esewa.springreactivemongodbcrud.repo.ProductRepo;
import com.esewa.springreactivemongodbcrud.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public Flux<ProductDto> getProducts(){
        return productRepo.findAll().map(AppUtils::modelTODto);
    }

    public Mono<ProductDto> getProduct(String id){
        return  productRepo.findById(id).map(AppUtils::modelTODto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max){
        return productRepo.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(AppUtils::dtoToModel)
                .flatMap(productRepo::insert)
                .map(AppUtils::modelTODto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return productRepo.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToModel))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepo::save)
                .map(AppUtils::modelTODto);
    }

     public Mono<Void> deleteProduct(String id){
        return productRepo.deleteById(id);
     }
}
