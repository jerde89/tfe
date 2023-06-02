package com.tfe.fournil.service;

import com.tfe.fournil.entity.Product;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.ProductVersion;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.repository.ProductVersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductVersionRepository productVersionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    private Product findProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        Product product;
        if(productOptional.isEmpty()) {
            product = new Product();
        }
        else{
            product = productOptional.get();
        }
        return product;
    }
    public Product updateProduct(Long id, String name, String description,
                              long category, float price, int tax_rate,
                              boolean enable){
        Product product = findProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setTaxRate(tax_rate);
        product.setUpdateAt(new Date());
        product.setEnable(enable);
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(category);
        productCategory.ifPresent(product::setCategory);
        Product productSave = productRepository.save(product);
        updateProductVersion(productSave, price, tax_rate);
        return productSave;
    }

    public void updateProductVersion(Product product, float price, int taxeRate) {
        List<ProductVersion> productVersions = productVersionRepository.findAll();
        productVersions.stream()
                .filter(productVersion -> productVersion.getProduct().getIdProduct() == product.getIdProduct())
                .filter(productVersion -> productVersion.getEndDate() == null)
                .forEach(productVersion -> {
                    productVersion.setEndDate(LocalDate.now());
                    productVersionRepository.save(productVersion);
                });
        ProductVersion productVersion = new ProductVersion();
        productVersion.setPrice(price);
        productVersion.setTaxRate(taxeRate);
        productVersion.setBeginDate(LocalDate.now());
        productVersion.setProduct(product);
        productVersionRepository.save(productVersion);
    }
}
