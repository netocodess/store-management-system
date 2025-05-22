package acc.br.sellers.service;

import acc.br.sellers.model.Seller;
import acc.br.sellers.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller create(Seller seller) {
        return sellerRepository.save(seller);
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller findById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller Not Found!" + id));
    }

    public Seller deleteSeller(Long id) {
        Seller seller = sellerRepository.findById(id) // Busca o seller
                .orElseThrow(() -> new EntityNotFoundException("Seller not found!" + id));
        sellerRepository.delete(seller);
        return seller;
    }
}
