package code.repositories;

import code.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.userId=:userId AND o.status=:status")
    List<Order> findOrdersByUserIdAndStatus(@Param("userId")Long userId, @Param("status")String status);

    @Query("SELECT o FROM Order o WHERE o.userId=:userId AND NOT o.status=:status")
    List<Order> findOrdersByUserIdAndNotStatus(@Param("userId")Long userId, @Param("status")String status);
}
