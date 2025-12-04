package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.OrderEntity;
import com.greta.eshop_api.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomer_Id(Long customerId);

    @Query("""
           SELECT o FROM OrderEntity o
           WHERE 
                CAST(o.id AS string) LIKE %:q%
                OR LOWER(o.customer.firstName) LIKE LOWER(CONCAT('%', :q, '%'))
                OR LOWER(o.customer.lastName) LIKE LOWER(CONCAT('%', :q, '%'))
                OR LOWER(o.customer.email) LIKE LOWER(CONCAT('%', :q, '%'))
           """)
    List<OrderEntity> searchOrders(@Param("q") String query);

    List<OrderEntity> findByStatus(OrderStatus status);
}
