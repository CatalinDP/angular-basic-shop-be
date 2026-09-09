package com.angular.be.shop.controllers;

import com.angular.be.shop.service.ShopService;
import com.angular.be.shop.dto.AddToCartRequest;
import com.angular.be.shop.models.CartItem;
import com.angular.be.shop.models.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ShopController {
    private final ShopService shopService;


    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/catalog")
    public List<Product> getCatalog() {
        return shopService.getAllProducts();
    }

    @PostMapping("/cart")
    public ResponseEntity<List<CartItem>> addToCart(@Valid @RequestBody AddToCartRequest request) {
        Optional<Product> product = shopService.getProductById(request.productId());
        if (product.isPresent()) {
            shopService.addToCart(new CartItem(product.get(), request.quantity()));
            return ResponseEntity.status(HttpStatus.CREATED).body(shopService.getCartItems());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cart")
    public List<CartItem> getCart() {
        return shopService.getCartItems();
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<List<CartItem>> removeFromCart(@PathVariable Long productId) {
        if (shopService.deleteFromCart(productId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(shopService.getCartItems());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/cart/{productId}/reduce")
    public ResponseEntity<List<CartItem>> reduceFromCart(@PathVariable Long productId) {
        if (shopService.reduceFromCart(productId)) {
            return ResponseEntity.status(HttpStatus.OK).body(shopService.getCartItems());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/cart")
    public ResponseEntity<List<CartItem>> emptyCart() {
        shopService.emptyCart();
        return ResponseEntity.ok().body(shopService.getCartItems());
    }
}
