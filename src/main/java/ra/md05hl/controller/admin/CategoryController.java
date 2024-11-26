package ra.md05hl.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.FormCategory;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.dto.response.category.CategoryResponse;
import ra.md05hl.model.entity.Categories;
import ra.md05hl.service.category.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    @GetMapping
    public ResponseEntity<ResponseDto<List<Categories>>> getAllCategories() {
        ResponseDto<List<Categories>> categories = categoryService.findAll();
        return new ResponseDto<List<Categories>>(200,HttpStatus.OK,categories);

    }
    // find by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Categories>> getCategoryById(@PathVariable Long id) throws NotFoundElementException {
        return ResponseEntity.ok(categoryService.findById(id));

    }
    //add
    @PostMapping
    public  ResponseEntity<ResponseDto<Categories>> addCategory(@RequestBody FormCategory request) {
        return new ResponseEntity<>(categoryService.add(request), HttpStatus.CREATED);

    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody Categories categories) throws NotFoundElementException {
        Categories cate = categoryService.findById(id);
        if (cate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.update(categories,id), HttpStatus.OK);
    }
//    //change status
//    @PatchMapping("/{id}/status")
//    public void patchCategory(@PathVariable Long id) {
//        categoryService.changeCategoryStatus(id);
//    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {
        Categories categories = categoryService.findById(id);
        if (categories == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryResponse( HttpStatus.NOT_FOUND.value(),null,HttpStatus.NOT_FOUND));
        }
        categoryService.remove(id);
        return ResponseEntity.ok().body(new CategoryResponse(HttpStatus.OK.value(),categories,HttpStatus.OK));

    }
    @GetMapping("/search")
    public Page<Categories> searchCategories(@RequestParam("name") String name, Pageable pageable) {
        return categoryService.searchByName(name, pageable);
    }
}


