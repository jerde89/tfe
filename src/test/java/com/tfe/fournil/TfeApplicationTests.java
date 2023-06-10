package com.tfe.fournil;

import com.tfe.fournil.entity.Product;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.OrderRepository;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.CategoryService;
import com.tfe.fournil.service.OrderService;
import com.tfe.fournil.service.ProductService;
import com.tfe.fournil.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tfe.fournil.entity.OrderStatus.DONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TfeApplicationTests {


    //MockBean = simulation des répo
    @MockBean
    UserRepository userRepository;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    ProductCategoryRepository productCategoryRepository;

    @MockBean
    OrderRepository orderRepository;

    //importe les services
    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    // Important pour tester tes vue
    @Autowired
    private WebApplicationContext wac;

    // Important pour les vues
    private MockMvc mockMvc;

    //préparation des objets pour tes tests
    ProductCategory productCategory = ProductCategory.builder()
            .countProduct(1)
            .createdAt(new Date(System.currentTimeMillis()))
            .description("test")
            .id(1)
            .enable(true)
            .name("test")
            .updateAt(new Date(System.currentTimeMillis()))
            .build();

    Product product = Product.builder()
            .category(productCategory)
            .idProduct(1L)
            .createdAt(new Date(System.currentTimeMillis()))
            .description("test")
            .enable(true)
            .img("testUrl")
            .name("test")
            .price(3.6f)
            .taxRate(6)
            .build();

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    //Test
    @Test
    void checkIfCategoryIsUniqueTest() throws Exception {

        // On simule ce cas avec un retourne null
        when(productCategoryRepository.findByName("test")).thenReturn(null);

        // test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(true, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfCategoryIsUniqueFailedTest() throws Exception {

        // On simule ce cas avec un retourn "TRUE"
        when(productCategoryRepository.findByName("test")).thenReturn(Stream.of(productCategory).collect(Collectors.toList()));

        // tu test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(false, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfEmailExistTest () {

        when(userRepository.findByUsername("test")).thenReturn(null);

        assertEquals(false, userService.checkIfEmailExist("test"));
    }


    /*******
     *
     */

    //Test
    @Test
    void checkIfCategoryIsUniqueTest2() throws Exception {

        // On simule ce cas avec un retourne null
        when(productCategoryRepository.findByName("test")).thenReturn(null);

        // test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(true, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfCategoryIsUniqueFailedTest2() throws Exception {

        // On simule ce cas avec un retourn "TRUE"
        when(productCategoryRepository.findByName("test")).thenReturn(Stream.of(productCategory).collect(Collectors.toList()));

        // tu test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(false, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfEmailExistTest2 () {

        when(userRepository.findByUsername("test")).thenReturn(null);

        assertEquals(false, userService.checkIfEmailExist("test"));
    }


    //Test
    @Test
    void checkIfCategoryIsUniqueTest3() throws Exception {

        // On simule ce cas avec un retourne null
        when(productCategoryRepository.findByName("test")).thenReturn(null);

        // test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(true, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfCategoryIsUniqueFailedTes3() throws Exception {

        // On simule ce cas avec un retourn "TRUE"
        when(productCategoryRepository.findByName("test")).thenReturn(Stream.of(productCategory).collect(Collectors.toList()));

        // tu test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(false, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfEmailExistTest3 () {

        when(userRepository.findByUsername("test")).thenReturn(null);

        assertEquals(false, userService.checkIfEmailExist("test"));
    }

    //Test
    @Test
    void checkIfCategoryIsUniqueTest4() throws Exception {

        // On simule ce cas avec un retourne null
        when(productCategoryRepository.findByName("test")).thenReturn(null);

        // test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(true, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfCategoryIsUniqueFailedTest4() throws Exception {

        // On simule ce cas avec un retourn "TRUE"
        when(productCategoryRepository.findByName("test")).thenReturn(Stream.of(productCategory).collect(Collectors.toList()));

        // tu test le retour. ( premiere variable est la réponse attendue, 2é est la focntion)
        assertEquals(false, categoryService.checkIfCategoryIsUnique("test"));
    }

    @Test
    void checkIfEmailExistTest4 () {

        when(userRepository.findByUsername("test")).thenReturn(null);

        assertEquals(false, userService.checkIfEmailExist("test"));
    }

}
