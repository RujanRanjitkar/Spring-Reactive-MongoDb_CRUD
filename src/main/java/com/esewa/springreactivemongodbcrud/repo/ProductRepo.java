package com.esewa.springreactivemongodbcrud.repo;

import com.esewa.springreactivemongodbcrud.dto.ProductDto;
import com.esewa.springreactivemongodbcrud.model.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
