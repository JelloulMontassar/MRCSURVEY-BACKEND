package com.crm.organizecrm.controller;

import com.crm.organizecrm.dto.ProductDTO;
import com.crm.organizecrm.exception.ProductNotFoundException;
import com.crm.organizecrm.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/scan/{qrCode}")
    public ResponseEntity<ProductDTO> getProductByQRCode(@PathVariable String qrCode) {
        ProductDTO productDTO = productService.getAllProducts().stream()
                .filter(p -> p.getQrCode().equals(qrCode))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found with QR code: " + qrCode));
        return ResponseEntity.ok(productDTO);
    }
}
