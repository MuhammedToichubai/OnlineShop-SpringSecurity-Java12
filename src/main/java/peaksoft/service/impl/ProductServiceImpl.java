package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.AddColourRequest;
import peaksoft.dto.request.ProductInnerPageResponse;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.enums.Role;
import peaksoft.model.Product;
import peaksoft.model.User;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Mukhammed Asantegin
 */
@Service
@RequiredArgsConstructor
@Slf4j  // Simple logging facade java
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final UserRepository userRepository;

    private void checkLoginUser(Long loginID) {
        User loginUser = userRepository.findById(loginID).orElseThrow(() ->
                new NoSuchElementException("User with id: " + loginID + "not found"));
        if (!loginUser.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Forbidden 403");
        }
    }

    @Override
    public SimpleResponse save(Long loginID, Category category, ProductRequest productRequest) {
        checkLoginUser(loginID);
        System.out.println("productRequest.getName() = " + productRequest.getName());
        Product buildProduct = productRequest.build();
        buildProduct.setCategory(category);
        productRepo.save(buildProduct);
        return new SimpleResponse(HttpStatus.OK, "Successfully saved product with name: " + buildProduct.getName());
    }

    @Override
    @Transactional
    public SimpleResponse addColours(Long loginID, Long productID, AddColourRequest addColourRequest) {
        checkLoginUser(loginID);
        Product product = productRepo.findById(productID).orElseThrow(() -> new NoSuchElementException("Product with id: " + productID + "not found"));
        for (String colour : addColourRequest.colours()) {
            product.addColour(colour);
        }
        return new SimpleResponse(HttpStatus.OK, "Success add colours");

    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepo.findAllProducts();

    }

    @Override
    @Transactional
    public SimpleResponse addOrRemoveFav(Long loginId, Long productId) {
        User loginUser = userRepository.findById(loginId).orElseThrow(() -> new NoSuchElementException("User with id: " + loginId + "not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new NoSuchElementException("Product with id: " + productId + "not found"));

        if (loginUser.getFavoriteProducts().contains(product)) {
            loginUser.getFavoriteProducts().remove(product);
            return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED).message("Product removed from favorite").build();
        }
        loginUser.getFavoriteProducts().add(product);
        return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED).message("Product added to favorite").build();
    }

    @Override
    public List<ProductResponse> findAllFavProducts(Long loginId) {
        return productRepo.findAllFavProducts(loginId);
    }

    @Override
    public ProductInnerPageResponse findById(Long productId) {
        productRepo.findProductBId(productId);
        ProductInnerPageResponse product = productRepo.findProudctById(productId);
        product.setSizes(productRepo.getSizes(productId));
        return product;

    }

    @Override
    public PaginationResponse findAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page -1 , size);
        Page<Product> productPage = productRepo.findAll(pageable);
        return PaginationResponse.builder()
                .page(productPage.getNumber() + 1)
                .size(productPage.getTotalPages())
                .productList(productPage.getContent())
                .build();
    }
}
