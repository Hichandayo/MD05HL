package ra.md05hl.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.md05hl.exception.NotFoundElementException;
import ra.md05hl.model.dto.request.FormProduct;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.dto.response.product.ProductResponse;
import ra.md05hl.model.entity.Products;
import ra.md05hl.service.UploadService;
import ra.md05hl.service.product.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;
    private final UploadService uploadService;

    // lấy tất cả danh sách sp
    @GetMapping()
    public ResponseEntity<ResponseDto<List<Products>>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Products>> getProductById(@PathVariable Long id) throws NotFoundElementException {
        ResponseDto<Products> product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //add
    @PostMapping()
    public ResponseEntity<ResponseDto<Products>> createProducts(@Valid @RequestBody FormProduct request){
        return new ResponseEntity<>(productService.add(request), HttpStatus.CREATED);
    }

    //update
    @PutMapping
    public ResponseEntity<ResponseDto<Products>> updateProduct(@PathVariable Long id, @RequestBody Products products) throws NotFoundElementException {
        ResponseDto<Products> pro = productService.findById(id);
        return new ResponseEntity<>(productService.update(products, id), HttpStatus.OK);
    }

//    @PatchMapping("/{id}/status")
//    public void patchProduct(@PathVariable Long id) {
//        productService.changeProductStatus(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseDto<List<Products>>> getProductByCategoryId(@PathVariable Long id) {
        ResponseDto<List<Products>> products = productService.findAllProductByCategoryId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public Page<Products> searchProducts(@RequestParam("keyword") String keyword, Pageable pageable) {
        return productService.searchProducts(keyword, pageable);
    }
}
