package ra.md05hl.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.md05hl.model.dto.request.FormProduct;
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
    public ResponseEntity<List<Products>> getAllProducts(){
        List<Products> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //add
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute FormProduct request) {
        List<String> urls = new ArrayList<>();
        List<MultipartFile> file = new ArrayList<>();
        for (MultipartFile f : file){
            String url = uploadService.uploadFileToServer(f);
            urls.add(url);
        }
        ProductResponse pro = productService.add(request);
        return new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    //update
    @PutMapping
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products products) {
        Products pro = productService.findById(id);
        if (pro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.update(products, id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public void patchProduct(@PathVariable Long id) {
        productService.changeProductStatus(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        Products products = productService.findById(id);
        if (products == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponse(HttpStatus.NOT_FOUND.value(), null, HttpStatus.NOT_FOUND));
        }
        productService.remove(id);
        return ResponseEntity.ok().body(new ProductResponse(HttpStatus.OK.value(), products, HttpStatus.OK));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ProductResponse> getProductByCategoryId(@PathVariable Long id) {
        List<Products> products = productService.findAllProductByCategoryId(id);
        return ResponseEntity.ok().body(new ProductResponse(HttpStatus.OK.value(), products, HttpStatus.OK));
    }

    @GetMapping("/search")
    public Page<Products> searchProducts(@RequestParam("keyword") String keyword, Pageable pageable) {
        return productService.searchProducts(keyword, pageable);
    }
}
