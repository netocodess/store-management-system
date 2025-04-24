package acc.br.summerAcademy.controller;

import acc.br.summerAcademy.model.Stock;
import acc.br.summerAcademy.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Stock> createProduto(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.createProduct(stock));
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllProducts() {
        return ResponseEntity.ok(stockService.findAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateProduct(@PathVariable Long id, @RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.updateStock(id, stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Stock> deleteProduct(@PathVariable Long id) {
        stockService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
