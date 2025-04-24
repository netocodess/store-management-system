package acc.br.summerAcademy.controller;

import acc.br.summerAcademy.model.Seller;
import acc.br.summerAcademy.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller){
        return ResponseEntity.ok(sellerService.create(seller));
    }


    @GetMapping
    public ResponseEntity<List<Seller>> findAllSellers(){
        return ResponseEntity.ok(sellerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Seller seller = sellerService.findById(id);
        return ResponseEntity.ok(seller);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) {
        Seller deletedSeller = sellerService.deleteSeller(id);
        return ResponseEntity.ok(deletedSeller);
    }
}
