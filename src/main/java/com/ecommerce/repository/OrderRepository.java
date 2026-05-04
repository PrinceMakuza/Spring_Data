package com.ecommerce.repository;
 
import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
 
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
 
    /**
     * Loads all orders with their user details eagerly (prevents LazyInitializationException).
     */
    @Query(value = "SELECT o FROM Order o LEFT JOIN FETCH o.user",
            countQuery = "SELECT count(o) FROM Order o")
    Page<Order> findAllWithUser(Pageable pageable);
 
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.user ORDER BY o.orderDate DESC")
    List<Order> findAllWithUser();
 
    /**
     * Loads all orders for a specific user with items eagerly loaded.
     */
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.product WHERE o.user = :user ORDER BY o.orderDate DESC")
    List<Order> findByUserWithItems(@Param("user") User user);
 
    /**
     * Gets paginated orders for a specific customer.
     */
    Page<Order> findByUserUserId(int userId, Pageable pageable);
 
    /**
     * Finds orders by their current status.
     */
    List<Order> findByStatus(String status);
 
    /**
     * Finds orders within a date range.
     */
    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
 
    /**
     * Loads an Order together with all its OrderItems and Product details in one query.
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.product WHERE o.orderId = :orderId")
    Optional<Order> findOrderWithItems(@Param("orderId") int orderId);
 
    /**
     * Gets paginated orders for a user, ordered by date descending.
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.user WHERE o.user.userId = :userId ORDER BY o.orderDate DESC")
    Page<Order> getUserOrderHistory(@Param("userId") int userId, Pageable pageable);
 
    /**
     * Generates a weekly sales report (Native SQL).
     */
    @Query(value = "SELECT date_trunc('week', order_date) as week, SUM(total_amount) as total " +
            "FROM orders GROUP BY week ORDER BY week", nativeQuery = true)
    List<Object[]> getWeeklySalesReport();
 
    /**
     * Calculates total revenue for the current month (Native SQL).
     */
    @Query(value = "SELECT SUM(total_amount) FROM orders " +
            "WHERE order_date >= date_trunc('month', current_date)", nativeQuery = true)
    Double getTotalRevenueCurrentMonth();
}
