package com.greta.eshop_api.integration;

import com.greta.eshop_api.domain.services.ProductService;
import com.greta.eshop_api.exposition.dtos.BotanicalInfo.BotanicalInfoRequestDTO;
import com.greta.eshop_api.exposition.dtos.CareInfo.CareInfoRequestDTO;
import com.greta.eshop_api.exposition.dtos.Products.ProductRequestDTO;
import com.greta.eshop_api.exposition.dtos.Products.ProductResponseDTO;
import com.greta.eshop_api.persistence.entities.CategoryEntity;
import com.greta.eshop_api.persistence.repositories.CategoryRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("integration")
class ProductServiceIntegrationTest {

    @Autowired private ProductService productService;
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    @Transactional
    void shouldCreateProductWithBotanicalAndCareInfo() {

        CategoryEntity category = new CategoryEntity();
        category.setName("Plantes");
        category = categoryRepository.save(category);

        ProductRequestDTO dto = new ProductRequestDTO(
                "Aloe Vera",
                "Aloe barbadensis",
                "Aloe médicinale",
                "Plante très facile d’entretien",
                19.99,
                "https://example.com/aloe.png",
                50,
                4.5,
                true,
                0.0,
                "Mettez-la près d’une fenêtre",
                category.getId(),
                new BotanicalInfoRequestDTO(
                        "Asphodelaceae",
                        "Afrique du Nord",
                        "vivace",
                        "non-toxique",
                        "facile"
                ),
                new CareInfoRequestDTO(
                        "modéré",
                        "lumière indirecte",
                        "sol drainant",
                        "mensuel"
                )
        );

        ProductResponseDTO response = productService.create(dto);

        assertThat(productRepository.findAll()).hasSize(1);

        assertThat(response.name()).isEqualTo("Aloe Vera");
        assertThat(response.price()).isEqualTo(19.99);
        assertThat(response.active()).isTrue();
        assertThat(response.categoryName()).isEqualTo("Plantes");

        assertThat(response.botanicalInfo().family()).isEqualTo("Asphodelaceae");
        assertThat(response.botanicalInfo().origin()).isEqualTo("Afrique du Nord");

        assertThat(response.careInfo().watering()).isEqualTo("modéré");
        assertThat(response.careInfo().sunlight()).isEqualTo("lumière indirecte");
    }
}
