package acc.br.summerAcademy.service;

import acc.br.summerAcademy.model.Seller;
import acc.br.summerAcademy.repository.SellerRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("Seller Not Found!"));
    }

    public Seller deleteSeller(Long id) {
        Seller seller = sellerRepository.findById(id) // Busca o seller
                .orElseThrow(() -> new EntityNotFoundException("Seller not found!"));
        sellerRepository.delete(seller); // Deleta o seller
        return seller; // Retorna o seller deletado
    }
}
