package ra.md05hl.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ra.md05hl.exception.DupplicateException;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.FormCategory;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Categories;
import ra.md05hl.repository.ICategoryRepository;

import java.util.List;


@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public ResponseDto<List<Categories>> findAll() {
        List<Categories> category = categoryRepository.findAll();
        return new ResponseDto<>(200, HttpStatus.OK,category);
    }

    @Override
    public ResponseDto<Categories> add(FormCategory request) {
        if (categoryRepository.existsByName(request.getName())){
            throw  new DupplicateException("Tên danh mục đã tồn tại");
        }
        Categories categories = Categories.builder()
                .name(request.getName())
                .build();
        Categories categoryInfo = categoryRepository.save(categories);

        return new ResponseDto<>(201, HttpStatus.CREATED,categoryInfo);

    }

    @Override
    public ResponseDto<Categories> findById(Long id) throws NotFoundElementException {
        Categories category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundElementException("không tìm thấy danh mục!!"));
        return new ResponseDto<>(200, HttpStatus.OK,category);
    }


    @Override
    public Categories update(Categories categories, Long id) {
        categories.setCategoryId(id);
        return categoryRepository.save(categories);
    }

    @Override
    public Page<Categories> searchByName(String name, Pageable pageable) {
        return categoryRepository.searchByName(name, pageable);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }


}
