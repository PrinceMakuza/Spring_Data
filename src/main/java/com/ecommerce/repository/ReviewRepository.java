package com.ecommerce.repository;

import com.ecommerce.model.Review;
import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProduct(Product product);

    /**
     * Gets all reviews for a product.
     */
    List<Review> findByProductProductId(int productId);

    /**
     * Gets all reviews written by a specific user.
     */
    List<Review> findByUserUserId(int userId);

    /**
     * Counts reviews by rating value.
     */
    long countByRating(int rating);

    /**
     * Calculates the average rating for a given product.
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.productId = :productId")
    Double getAverageRatingByProductId(@Param("productId") int productId);

    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.product LEFT JOIN FETCH r.user")
    List<Review> findAllWithUserAndProduct();
}
