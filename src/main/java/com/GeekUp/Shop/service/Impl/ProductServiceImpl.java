package com.GeekUp.Shop.service.Impl;

import com.GeekUp.Shop.dto.request.CreateProductRequest;
import com.GeekUp.Shop.dto.response.BaseProductResponse;
import com.GeekUp.Shop.entity.Category;
import com.GeekUp.Shop.entity.Product;
import com.GeekUp.Shop.entity.ProductVariant;
import com.GeekUp.Shop.entity.ProductVariantOption;
import com.GeekUp.Shop.repository.ProductRepository;
import com.GeekUp.Shop.repository.ProductVariantRepository;
import com.GeekUp.Shop.repository.VariantOptionRepository;
import com.GeekUp.Shop.service.IProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductVariantRepository productVariantRepository;
    private final VariantOptionRepository variantOptionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ProductVariantRepository productVariantRepository, VariantOptionRepository variantOptionRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.productVariantRepository = productVariantRepository;
        this.variantOptionRepository = variantOptionRepository;
    }

//    @Override
//    @Transactional
//    public BaseProductResponse createProduct(CreateProductRequest createProductRequest) {
//
//        List<ProductVariant> productVariants = createProductRequest.getVariants()
//                .stream().map(variant -> {
//                    ProductVariant productVariant = new ProductVariant();
//
//                    productVariant.setSku(variant.getSku());
//                    productVariant.setStock(variant.getStockQuantity());
//                    productVariant.setPrice(variant.getPrice());
//                    List<ProductVariantOption> variantOptions = variant.getAttributes().entrySet().stream().map(entrySet -> {
//                        ProductVariantOption productVariantOption = new ProductVariantOption(entrySet.getKey(), entrySet.getValue());
////                        productVariant.getProductVariantOptions().add(productVariantOption);
//                        return productVariantOption;
//                    }).toList();
//
//
//                    productVariant.setProductVariantOptions(variantOptions);
//
//
//                    return productVariant;
//                })
//                .toList();
//
////        List<ProductVariant> productVariants1 = productVariantRepository.saveAll(productVariants);
//
//        Category category = new Category();
//        category.setId(createProductRequest.getCategoryId());
//
//        Product product = new Product();
//
//        product.setName(createProductRequest.getName());
//        product.setDescription(createProductRequest.getDescription());
//        product.setProductVariants(productVariants);
//        product.setBasePrice(createProductRequest.getBasePrice());
//        product.setCategory(category);
//        product.setImageUrl(createProductRequest.getImageUrl());
//
//        Product savedProduct = productRepository.save(product);
//
//        return modelMapper.map(savedProduct, BaseProductResponse.class);
//    }


//    @Override
//    @Transactional
//    public BaseProductResponse createProduct(CreateProductRequest createProductRequest) {
//
//
////        List<ProductVariant> productVariants1 = productVariantRepository.saveAll(productVariants);
//
//        Category category = new Category();
//        category.setId(createProductRequest.getCategoryId());
//
//        Product product = new Product();
//        product.setName(createProductRequest.getName());
//        product.setDescription(createProductRequest.getDescription());
//        product.setBasePrice(createProductRequest.getBasePrice());
//        product.setCategory(category);
//        product.setImageUrl(createProductRequest.getImageUrl());
//
//        List<ProductVariant> productVariants = new ArrayList<>();
//
//        product.setProductVariants(productVariants);
//        for (var variantReq : createProductRequest.getVariants()) {
//            ProductVariant variant = new ProductVariant();
//            variant.setSku(variantReq.getSku());
//            variant.setStock(variantReq.getStockQuantity());
//            variant.setPrice(variantReq.getPrice());
//            variant.setProduct(product); // important
//
//            List<ProductVariantOption> options = new ArrayList<>();
//            for (var entry : variantReq.getAttributes().entrySet()) {
//                ProductVariantOption option = new ProductVariantOption(entry.getKey(), entry.getValue());
//                option.setProductVariant(variant);
//                options.add(option);
//            }
//
//            variant.setProductVariantOptions(options);
//            productVariants.add(variant);
//        }
//
////        productVariantRepository.saveAll(productVariants);
////        product.setProductVariants(productVariants);
//
//        // Only this call needed
//        Product savedProduct = productRepository.save(product);
////        entityManager.persist(product);
//
//        return modelMapper.map(savedProduct, BaseProductResponse.class);
//
//    }

@Override
@Transactional
public BaseProductResponse createProduct(CreateProductRequest createProductRequest) {
    // Create Category stub
    Category category = new Category();
    category.setId(createProductRequest.getCategoryId());

    // Build Product
    Product product = new Product();
    product.setName(createProductRequest.getName());
    product.setDescription(createProductRequest.getDescription());
    product.setBasePrice(createProductRequest.getBasePrice());
    product.setCategory(category);
    product.setImageUrl(createProductRequest.getImageUrl());
    product.setProductVariants(new ArrayList<>());

    // Persist Product
    entityManager.persist(product);

    // Build and persist ProductVariants and ProductVariantOptions
    for (var variantReq : createProductRequest.getVariants()) {
        ProductVariant variant = new ProductVariant();
        variant.setSku(variantReq.getSku());
        variant.setStock(variantReq.getStockQuantity());
        variant.setPrice(variantReq.getPrice());
        variant.setProduct(product);
        variant.setProductVariantOptions(new ArrayList<>());

        for (var entry : variantReq.getAttributes().entrySet()) {
            ProductVariantOption option = new ProductVariantOption(entry.getKey(), entry.getValue());
            option.setProductVariant(variant);
            variant.getProductVariantOptions().add(option);
        }

        entityManager.persist(variant);
        variant.getProductVariantOptions().forEach(entityManager::persist);
        product.getProductVariants().add(variant);
    }

    // Flush to batch all inserts
    entityManager.flush();
    entityManager.clear(); // Clear to reduce memory usage

    return modelMapper.map(product, BaseProductResponse.class);
}

    @Override
    public List<ProductVariant> findAllProductVariantByVariantId(List<Long> variantIds) {
        return productVariantRepository.findAllById(variantIds);
    }

    @Override
    public void saveAllProductVariant(List<ProductVariant> productVariants) {
        try{
            productVariantRepository.saveAll(productVariants);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
