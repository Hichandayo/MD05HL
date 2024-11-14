package ra.md05hl.service;

import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.ProductCreateDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Product;

import java.util.List;

public interface IProductService {
    ResponseDto<List<Product>> findAll();
    ResponseDto<List<Product>> getAllByStatusIsTrue();
    ResponseDto<Product> createProduct(ProductCreateDto product);
    ResponseDto<Product> getById(Long id) throws NotFoundElementException;
}
