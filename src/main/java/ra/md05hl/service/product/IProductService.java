package ra.md05hl.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.FormProduct;
import ra.md05hl.model.dto.response.ProductDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Products;

import java.util.List;

public interface IProductService {
    ResponseDto<List<Products>> findAll();
    ResponseDto<Products> findById(Long id)throws NotFoundElementException;
    ResponseDto<Products> add(FormProduct formProduct);
    Products update(Products products, Long id);
    ResponseDto<List<Products>> findAllProductByCategoryId(Long categoryId);
    ResponseDto<List<ProductDto>> getAllByStatusIsTrue();
    Page<Products> searchProducts(String keyword, Pageable pageable);
    void remove(Long id);

}
