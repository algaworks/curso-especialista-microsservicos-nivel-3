package com.algaworks.algashop.product.catalog.contract.base;

import com.algaworks.algashop.product.catalog.application.product.management.ProductInput;
import com.algaworks.algashop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.algaworks.algashop.product.catalog.application.product.query.*;
import com.algaworks.algashop.product.catalog.presentation.ProductController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@WebMvcTest(controllers = ProductController.class)
public class ProductBase {

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private ProductQueryService productQueryService;

    @MockitoBean
    private ProductManagementApplicationService productManagementApplicationService;

    public static final UUID validProductId = UUID.fromString("fffe6ec2-7103-48b3-8e4f-3b58e43fb75a");

    public static final UUID createdProductId = UUID.fromString("21651a12-b126-4213-ac21-19f66ff4642e");

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8).build());

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

        mockValidOrderFindById();
        mockFilterProducts();
        mockCreateProduct();
    }

    private void mockCreateProduct() {
        Mockito.when(productManagementApplicationService.create(Mockito.any(ProductInput.class)))
                .thenReturn(createdProductId);

        Mockito.when(productQueryService.findById(createdProductId))
                .thenReturn(ProductDetailOutputTestDataBuilder.aProduct().inStock(false).build());
    }

    private void mockFilterProducts() {
        Mockito.when(productQueryService.filter(
                Mockito.anyInt(), Mockito.anyInt()))
                .then((answer)-> {
                    Integer size = answer.getArgument(0);

                    return PageModel.<ProductDetailOutput>builder()
                            .number(0)
                            .size(size)
                            .totalPages(1)
                            .totalElements(2)
                            .content(
                                    List.of(
                                            ProductDetailOutputTestDataBuilder.aProduct().build(),
                                            ProductDetailOutputTestDataBuilder.aProductAlt1().build()
                                    )
                            ).build();
                });
    }

    private void mockValidOrderFindById() {
        Mockito.when(productQueryService.findById(validProductId))
                .thenReturn(ProductDetailOutputTestDataBuilder.aProduct().id(validProductId).build());
    }

}
