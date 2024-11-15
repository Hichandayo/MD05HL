package ra.md05hl.service;

import lombok.RequiredArgsConstructor;
import ra.md05hl.model.entity.Product;
import ra.md05hl.repository.IProductRepository;

import java.util.List;
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    public final IProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
