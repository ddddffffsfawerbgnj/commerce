package com.example.commerce.controller;

import com.example.commerce.dto.ProductDto;
import com.example.commerce.entity.Product;
import com.example.commerce.model.ProductParam;
import com.example.commerce.serviece.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController extends BaseController{
    private final ProductService productService;

    @GetMapping("/product/list")
    public String list(Model model, ProductParam parameter){

        parameter.init();
        List<ProductDto> products = productService.list(parameter);

        long totalCount = 0;
        if (products != null && products.size() > 0) {
            totalCount = products.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", products);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "product/list";
    }

}
