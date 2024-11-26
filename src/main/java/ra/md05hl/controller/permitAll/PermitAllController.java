package ra.md05hl.controller.permitAll;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.md05hl.model.dto.request.FormLogin;
import ra.md05hl.model.dto.request.FormRegister;
import ra.md05hl.model.dto.response.JwtResponse;
import ra.md05hl.model.dto.response.ProductDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Products;
import ra.md05hl.service.auth.IAuthenticationService;
import ra.md05hl.service.product.ProductServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api.myservice.com/v1/public")
@RequiredArgsConstructor
public class PermitAllController {
    private final IAuthenticationService authenticationService;
    private final ProductServiceImpl productService;

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, Object>> register(@RequestBody FormRegister request){
        authenticationService.register(request);
        Map<String, Object> map = new HashMap<>();
        map.put("code",201);
        map.put("message","Register Successfully");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<Map<String,Object>> login(@RequestBody FormLogin request){
        JwtResponse response = authenticationService.login(request);
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","Login Successfully");
        map.put("data",response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // lấy tất cả danh sách sp có trạng = true
    @GetMapping("/active")
    public ResponseEntity<ResponseDto<List<ProductDto>>> getProductsByStatusIsTrue(){
        ResponseDto<List<ProductDto>> products = productService.getAllByStatusIsTrue();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}