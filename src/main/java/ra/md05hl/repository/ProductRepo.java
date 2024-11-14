package ra.md05hl.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.md05hl.model.entity.Product;

import java.util.List;

@Repository
@Transactional
public class ProductRepo implements IProductRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = entityManager.createQuery("from Product where id = :id", Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            Product pro = findById(product.getId());
            pro.setName(product.getName());
            pro.setStatus(product.getStatus());
            pro.setStock(product.getStock());
            entityManager.merge(pro);
        }
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
