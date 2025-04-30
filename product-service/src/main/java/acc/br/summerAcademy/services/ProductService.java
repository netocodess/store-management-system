package acc.br.summerAcademy.services;

import acc.br.summerAcademy.model.Product;
import acc.br.summerAcademy.model.Seller;
import acc.br.summerAcademy.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import acc.br.summerAcademy.repository.SellerRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public Product createProduct(Product product) {
        Seller seller = sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + product.getSeller().getId()));
        product.setSeller(seller);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setProductName(updatedProduct.getProductName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setValue(updatedProduct.getValue());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setSeller(updatedProduct.getSeller());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
