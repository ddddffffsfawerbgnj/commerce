package com.example.commerce.controller;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.model.ProductParam;
import com.example.commerce.serviece.ProductListService;
import com.example.commerce.serviece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController extends BaseController {
    private final ProductListService productListService;

    private final ProductService productService;


    @GetMapping(value = "/admin/product/add.do")
    public String productForm(ProductDto productDto, Model model) {

        model.addAttribute("productDto", productDto);
        return "admin/product/add";
    }

    @PostMapping("/admin/product/add.do")
    public String productAdd(@Valid ProductDto productDto,
                             BindingResult bindingResult, Model model,
                             @RequestParam(name = "productImgFile")List<MultipartFile> productImgFileList) {
        if (bindingResult.hasErrors()) {
            return "admin/product/add";
        }

        if (productImgFileList.get(0).isEmpty() && productDto.getProductId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "admin/product/add";
        }

        try {
            productService.saveProduct(productDto, productImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
            return "admin/product/add";
        }

        return "redirect:/";
    }

    /* 상품목록-관리자 */
    @GetMapping("/admin/product/list.do")
    public String list(Model model, ProductParam parameter) {

        parameter.init();
        List<ProductDto> products = productListService.list(parameter);

        long totalCount = 0;
        if (products != null && products.size() > 0) {
            totalCount = products.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", products);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/product/list";
    }

    /*상품상세*/


}
