package acc.br.summerAcademy.services;

import acc.br.summerAcademy.model.Seller;
import acc.br.summerAcademy.model.Stock;
import acc.br.summerAcademy.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Stock createStock(Stock stock){
        Long sellerId = stock.getSeller().getId();
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + sellerId));

        stock.setSeller(seller);
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id: " + id));
    }

    public Stock updateStock(Long id, Stock updatedStock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingStock.setProductName(updatedStock.getProductName());
        existingStock.setStockQuantity(updatedStock.getStockQuantity());
        existingStock.setSeller(updatedStock.getSeller());

        return stockRepository.save(existingStock);
    }

    public void deleteStock(Long id){
        stockRepository.deleteById(id);
    }
}
