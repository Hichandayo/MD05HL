package ra.md05hl;

import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    public final IProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
