package com.example.commerce.serviece;

import com.example.commerce.entity.ProductImg;
import com.example.commerce.repository.ProductImgRepository;
import com.example.commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgService {

    @Value("${productImgLocation}")
    private String productImgLocation;

    private final ProductImgRepository productImgRepository;
    private final FileService fileService;

    public void saveProductImg(ProductImg productImg,
                               MultipartFile productImgFile) throws Exception {
        String oriImgName = productImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(productImgLocation, oriImgName,
                    productImgFile.getBytes());
            imgUrl = "/images/product" + imgName;
        }

        //상품 이미지 정보 저장
        productImg.updateProductImg(oriImgName, imgName, imgUrl);
        productImgRepository.save(productImg);
    }

//    public void updateProductImg(Long productImgId,
//                                 MultipartFile productImgFile) throws Exception {
//
//        //상품 이미지를 수정했다면
//        if (!productImgFile.isEmpty()) {
//            ProductImg saveProductImg = productImgRepository.findById(productImgId)
//                    .orElseThrow(EntityNotFoundException::new);
//
//            //기존 이미지 파일이 존재한다면 삭제
//            if (!StringUtils.isEmpty(saveProductImg.getImgName())){
//                fileService.deleteFile(productImgLocation + "/" + saveProductImg.getImgName());
//            }
//
//            String oriImgName = productImgFile.getOriginalFilename();
//            String imgName = fileService.uploadFile(productImgLocation,
//                    oriImgName, productImgFile.getBytes());
//            String imgUrl = "/images/product/" + imgName;
//            saveProductImg.updateProductImg(oriImgName, imgName, imgUrl);
//        }

}
