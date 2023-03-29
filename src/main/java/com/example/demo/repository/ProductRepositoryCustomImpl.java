package com.example.demo.repository;

import com.example.demo.constant.ProductSellStatus;
import com.example.demo.dto.ProductSearchDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.QProduct;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public ProductRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ProductSellStatus searchSellStatus){
        return searchSellStatus == null ? null :
                QProduct.product.productSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType){

        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QProduct.product.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if(StringUtils.equals("productName", searchBy)){
            return QProduct.product.productName.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return QProduct.product.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Product> getAdminItemPage(ProductSearchDto productSearchDto, Pageable pageable) {
        List<Product> content = queryFactory
                .selectFrom(QProduct.product)
                .where(regDtsAfter(productSearchDto.getSearchDateType()),
                        searchSellStatusEq(productSearchDto.getSearchSellStatus()),
                        searchByLike(productSearchDto.getSearchBy(),
                                productSearchDto.getSearchQuery()))
                .orderBy(QProduct.product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count).from(QProduct.product)
                .where(regDtsAfter(ProductSearchDto.getSearchDateType()),
                        searchSellStatusEq(ProductSearchDto.getSearchSellStatus()),
                        searchByLike(ProductSearchDto.getSearchBy(), ProductSearchDto.getSearchQuery()))
                .fetchOne()
                ;

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression productNmLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null :
                QProduct.product.productName.like(
                        "%" + searchQuery + "%");
    }

//    @Override
//    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
//        QItem item = QItem.item;
//        QItemImg itemImg = QItemImg.itemImg;
//
//        List<MainItemDto> content = queryFactory
//                .select(
//                        new QMainItemDto(
//                                item.id,
//                                item.itemNm,
//                                item.itemDetail,
//                                itemImg.imgUrl,
//                                item.price)
//                )
//                .from(itemImg)
//                .join(itemImg.item, item)
//                .where(itemImg.repimgYn.eq("Y"))
//                .where(itemNmLike(itemSearchDto.getSearchQuery()))
//                .orderBy(item.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        long total = queryFactory
//                .select(Wildcard.count)
//                .from(itemImg)
//                .join(itemImg.item, item)
//                .where(itemImg.repimgYn.eq("Y"))
//                .where(itemNmLike(itemSearchDto.getSearchQuery()))
//                .fetchOne()
//                ;
//
//        return new PageImpl<>(content, pageable, total);
//    }

}
