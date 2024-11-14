package ra.md05hl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.ProductCreateDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.repository.IProductRepository;
import ra.md05hl.model.entity.Product;

import java.util.List;
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    public final IProductRepository productRepository;

    @Override
    public ResponseDto<List<Product>> getAllByStatusIsTrue() {
       List<Product> product = productRepository.findAllProductsByStatusIsTrue();
       return new  ResponseDto<>(200,HttpStatus.OK,product);
    }

    @Override
    public ResponseDto<Product> createProduct(ProductCreateDto product) {
        return null;
    }

    @Override
    public ResponseDto<Product> getById(Long id) throws NotFoundElementException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundElementException("Product Not Found!!"));
        return new ResponseDto<>(200, HttpStatus.OK,product);
    }

    @Override
    public ResponseDto<List<Product>> findAll() {
        List<Product> product = productRepository.findAll();
        return new ResponseDto<>(200,HttpStatus.OK,product);
    }
}
