package lesson24.touragency.order.controller;

import lesson24.touragency.common.business.application.ServiceSupplier;
import lesson24.touragency.common.business.controller.BaseController;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.dto.OrderDtoConverter;
import lesson24.touragency.order.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(name = "ViewDetailedOrder", urlPatterns = "/vieworder")

public class ViewDetailsOrderController extends BaseController {
    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (isParamExists(req, "id")) {
                long id = Long.parseLong(req.getParameter("id"));

                Optional<Order> fullOrder = orderService.getFullOrder(id);
                if (fullOrder.isPresent()) {
                    req.setAttribute("order", OrderDtoConverter.convertToDto(fullOrder.get()));
                } else {
                    redirectTo404("No order with id '" + id + "'", resp);
                }
            }
            forwardToPage(req, resp, "order.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500(e.getMessage(), resp);
        }
    }
}
