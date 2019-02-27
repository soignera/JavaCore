package touragency.order.repo;

import touragency.common.business.repo.BaseRepo;
import touragency.order.domain.Order;
import touragency.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {
    void add(Order order);

    Order findById(long id);

    List[Order] search(OrderSearchCondition searchCondition);
}
