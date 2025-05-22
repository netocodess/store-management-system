package acc.br.orders.service;

import acc.br.orders.dtos.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceClient {

    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public ProductServiceClient(
            RestTemplate restTemplate,
            @Value("${product-service.base-url}") String productServiceUrl
    ) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
        System.out.println(">>> PRODUCT-SERVICE-URL = " + this.productServiceUrl);
    }

    public ProductResponseDTO getProductById(Long productId) {
        String url = productServiceUrl + "/products/" + productId;
        return restTemplate.getForObject(url, ProductResponseDTO.class);
    }
}
