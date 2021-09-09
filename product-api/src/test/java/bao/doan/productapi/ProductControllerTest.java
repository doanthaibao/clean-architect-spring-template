package bao.doan.productapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import bao.doan.productapi.exception.CustomResponseEntityExceptionHandler;
import bao.doan.productapi.product.ProductController;
import bao.doan.productdomain.Product;
import bao.doan.productusecase.AddProductUseCase;
import bao.doan.productusecase.GetProductUseCase;
import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest(classes = {ProductController.class, CustomResponseEntityExceptionHandler.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetProductUseCase getProductUseCase;

  @MockBean
  private AddProductUseCase addProductUseCase;

  private final ObjectMapper objectMapper = new ObjectMapper();

  private static final String ID = "12345";

  private Product product;

  @BeforeEach
  void setup() {
    product = Product.builder().name("Plan").id(ID).build();
  }

  @Test
  public void getResultFromGetProduct() throws Exception {
    given(getProductUseCase.getProduct(ID)).willReturn(product);
    this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/{id}", ID))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getNoResultWithId() throws Exception {
    doThrow(EntityNotFoundException.class).when(getProductUseCase).getProduct(anyString());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/{id}", "33"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void addNewProduct() throws Exception {
    given(addProductUseCase.addProduct(product)).willReturn(product);
    this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void returnExistExceptionWhenCreateProduct() throws Exception {
    doThrow(EntityAlreadyExistException.class).when(addProductUseCase).addProduct(any(Product.class));
    this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
        .andExpect(MockMvcResultMatchers.status().isConflict());
  }

}
