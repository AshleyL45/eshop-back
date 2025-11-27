package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.domain.rules.FavoriteRules;

import com.greta.eshop_api.exposition.dtos.Favorite.FavoriteRequestDTO;
import com.greta.eshop_api.exposition.dtos.Favorite.FavoriteResponseDTO;
import com.greta.eshop_api.exposition.mappers.FavoriteMapper;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.entities.FavoriteEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import com.greta.eshop_api.persistence.repositories.FavoriteRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository repo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;

    public FavoriteService(FavoriteRepository repo, CustomerRepository customerRepo, ProductRepository productRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    public List<FavoriteResponseDTO> getByCustomer(Long customerId) {
        FavoriteRules.mustHaveValidCustomer(customerRepo, customerId);

        return repo.findByCustomerId(customerId)
                .stream()
                .map(FavoriteMapper::toResponseDTO)
                .toList();
    }

    public FavoriteResponseDTO addFavorite(Long customerId, FavoriteRequestDTO dto) {
        CustomerEntity customer = FavoriteRules.mustHaveValidCustomer(customerRepo, customerId);
        ProductEntity product = FavoriteRules.mustHaveValidProduct(productRepo, dto.getProductId());

        if (repo.existsByCustomerIdAndProductId(customerId, dto.getProductId())) {
            throw new IllegalStateException("Ce produit est déjà dans les favoris du client " + customerId);
        }


        FavoriteEntity entity = new FavoriteEntity();
        entity.setCustomer(customer);
        entity.setProduct(product);

        repo.save(entity);

        return FavoriteMapper.toResponseDTO(entity);
    }

    public void deleteFavorite(Long customerId, Long favoriteId) {

        FavoriteEntity entity = repo.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException("Favori avec l'ID : " + favoriteId + " n'existe pas"));

        if (!entity.getCustomer().getId().equals(customerId)) {
            throw new ResourceNotFoundException("Ce favori n'appartient pas au client " + customerId);
        }

        repo.delete(entity);
    }
}
