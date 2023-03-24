package com.example.commerce.serviece;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.dto.ProductImgDto;
import com.example.commerce.entity.Product;
import com.example.commerce.entity.ProductImg;
import com.example.commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgService productImgService;

    public Long saveProduct(ProductDto productDto,
                            List<MultipartFile> productImgFileList) throws Exception {

        // 상품 등록
        Product product = productDto.createProduct();
        productRepository.save(product);

        // 이미지 등록
        for (int i = 0; i < productImgFileList.size(); i++) {
            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);
            if (i == 0) {
                productImg.setRepImgYn("Y");
            } else {
                productImg.setRepImgYn("N");
            }
            productImgService.saveProductImg(productImg,
                    productImgFileList.get(i));
        }

        return product.getProductId();
    }

    @Transactional(readOnly = true)
    public ProductDto getProductDtl(Long productId) {

        List<ProductImg> productImgList = new ArrayList<>();

        List<ProductImgDto> productImgDtoList = new ArrayList<>();

        for (ProductImg productImg : productImgList) {
            ProductImgDto productImgDto = ProductImgDto.of(productImg);
            productImgDtoList.add(productImgDto);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);

        ProductDto productDto = ProductDto.of(product);
        productDto.setProductImgDtoList(productImgDtoList);
        return productDto;
    }
}
