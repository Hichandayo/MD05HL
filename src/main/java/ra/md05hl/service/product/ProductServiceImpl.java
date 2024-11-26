package ra.md05hl.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ra.md05hl.exception.DupplicateException;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.FormProduct;
import ra.md05hl.model.dto.response.ProductDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Products;
import ra.md05hl.repository.IProductRepository;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Override
    public ResponseDto<List<Products>> findAll() {
        List<Products> product = productRepository.findAll();
        return new ResponseDto<>(200,HttpStatus.OK,product);
    }

    @Override
    public  ResponseDto<Products> findById(Long id) throws NotFoundElementException {
        Products product = productRepository.findById(id).orElseThrow(() -> new NotFoundElementException("Product id not found"));
        return new ResponseDto<>(200, HttpStatus.OK,product);
    }

    @Override
    public Page<Products> searchProducts(String keyword, Pageable pageable) {
        return productRepository.searchByNameOrDescription(keyword, pageable);
    }

    @Override
    public ResponseDto<Products> add(FormProduct request) {
        if (productRepository.existsByName(request.getName())){
            throw  new DupplicateException("Tên sản phẩm đa tôn tại");
        }
        // biên đổi thành entity
        Products product = Products.builder()
                .name(request.getName())
                .stock(request.getStock())
                .image(request.getImage().toString())
                .price(request.getPrice())
                .description(request.getDescription())
                .status(true)
                .build();
        Products newProduct = productRepository.save(product);
        return new ResponseDto<>(201, HttpStatus.CREATED,newProduct);
    }

    @Override
    public Products update(Products products, Long id) {
        products.setProductId(id);
        return productRepository.save(products);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ResponseDto<List<Products>>  findAllProductByCategoryId(Long categoryId) {
        return productRepository.findAllProductByCategoryId(categoryId);
    }


    @Override
    public ResponseDto<List<ProductDto>> getAllByStatusIsTrue() {
        List<ProductDto> product = productRepository.findAllProductsByStatusIsTrue();
        return new  ResponseDto<>(200,HttpStatus.OK,product);
    }
}
