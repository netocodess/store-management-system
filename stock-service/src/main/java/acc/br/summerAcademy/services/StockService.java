package acc.br.summerAcademy.services;

import acc.br.summerAcademy.model.Seller;
import acc.br.summerAcademy.model.Stock;
import acc.br.summerAcademy.repository.StockRepository;
import org.springframework.stereotype.Service;
import acc.br.summerAcademy.repository.SellerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final SellerRepository sellerRepository;

    public StockService(StockRepository stockRepository, SellerRepository sellerRepository) {
        this.stockRepository = stockRepository;
        this.sellerRepository = sellerRepository;
    }

    public Stock createProduct(Stock stock){
        Long sellerId = stock.getSeller().getSellerId();
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + sellerId));

        stock.setSeller(seller); // agora sim, está gerenciado
        return stockRepository.save(stock);
    }

    public List<Stock> findAllProducts(){
        return stockRepository.findAll();
    }

    public Stock findProductById(Long id){
        Optional<Stock> product = stockRepository.findById(id);
        return product.get();
    }
    public Stock updateStock(Long id, Stock updatedStock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingStock.setProductName(updatedStock.getProductName());
        existingStock.setStockQuantity(updatedStock.getStockQuantity());
        existingStock.setSeller(updatedStock.getSeller());

        return stockRepository.save(existingStock);
    }

    public void deleteProduct(Long id){
        stockRepository.deleteById(id);
    }
}
