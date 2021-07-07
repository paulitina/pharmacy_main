package code.repositories;

import code.entities.OrderProduct;
import code.entities.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductDao extends JpaRepository<OrderProduct, OrderProductPK> {
}
