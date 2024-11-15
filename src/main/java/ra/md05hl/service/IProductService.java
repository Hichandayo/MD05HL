package ra.md05hl.service;

import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.ProductCreateDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    List<Product> getAllByStatusIsTrue();
    ResponseDto<Product> createProduct(ProductCreateDto request);
    ResponseDto<Product> getById(Long id) throws NotFoundElementException;
}
