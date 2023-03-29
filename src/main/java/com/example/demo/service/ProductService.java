package com.example.demo.service;

import com.example.demo.dto.ProductFormDto;
import com.example.demo.dto.ProductImgDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImg;
import com.example.demo.repository.ProductImgRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgService productImgService;
    private final ProductImgRepository productImgRepository;

    public Long saveProduct(ProductFormDto productFormDto,
                            List<MultipartFile> productImgFileList) throws Exception {

        //상품 등록
        Product product = productFormDto.createProduct();
        productRepository.save(product);

        //이미지 등록
        for (int i = 0; i < productImgFileList.size(); i++) {
            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);
            if (i == 0)
                productImg.setRepImgYn("Y");
            else
                productImg.setRepImgYn("N");
            productImgService.saveProductImg(productImg,
                    productImgFileList.get(i));
        }

        return product.getId();
    }

}
