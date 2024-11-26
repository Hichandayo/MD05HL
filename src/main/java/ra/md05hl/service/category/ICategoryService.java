package ra.md05hl.service.category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.FormCategory;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Categories;

import java.util.List;
@Repository
public interface ICategoryService {
    ResponseDto<List<Categories>> findAll();
    ResponseDto<Categories> findById(Long id)throws NotFoundElementException;
    ResponseDto<Categories> add(FormCategory formCategory);
    Categories update(Categories categories, Long id);
    Page<Categories> searchByName(String name, Pageable pageable);
    void remove(Long id);
}