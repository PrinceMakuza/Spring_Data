package com.ecommerce.repository;
 
import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
 
    /**
     * Finds products by category name with pagination.
     */
    Page<Product> findByCategoryName(String name, Pageable pageable);
 
    /**
     * Finds products within a price range.
     */
    List<Product> findByPriceBetween(double min, double max);
 
    /**
     * Case-insensitive product name search.
     */
    List<Product> findByNameContainingIgnoreCase(String name);
 
    /**
     * Finds low stock products.
     */
    List<Product> findByStockQuantityLessThan(int quantity);
 
    /**
     * Combined category and price filtering.
     */
    List<Product> findByCategoryCategoryIdAndPriceBetween(int categoryId, double min, double max);
 
    /**
     * Custom JPQL search with optional parameters.
     */
    @Query(value = "SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE :name) AND " +
            "(:categoryId IS NULL OR p.category.categoryId = :categoryId) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice)",
            countQuery = "SELECT count(p) FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE :name) AND " +
            "(:categoryId IS NULL OR p.category.categoryId = :categoryId) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    Page<Product> searchProducts(
            @Param("name") String name,
            @Param("categoryId") Integer categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);
 
    /**
     * Load products with category info in a single query (JOIN FETCH).
     */
    @Query("SELECT p FROM Product p JOIN FETCH p.category")
    List<Product> findAllWithCategory();
 
    /**
     * Native query to count products per category.
     */
    @Query(value = "SELECT c.name, COUNT(p.product_id) FROM categories c " +
            "LEFT JOIN products p ON c.category_id = p.category_id " +
            "GROUP BY c.name", nativeQuery = true)
    List<Object[]> countProductsPerCategory();
 
    /**
     * Native query to find the top 5 most expensive products.
     */
    @Query(value = "SELECT * FROM products ORDER BY price DESC LIMIT 5", nativeQuery = true)
    List<Product> findTop5MostExpensiveProducts();
}
