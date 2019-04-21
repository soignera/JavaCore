package lesson24.touragency.order.controller;

import lesson24.touragency.common.business.application.ServiceSupplier;
import lesson24.touragency.common.business.controller.BaseController;
import lesson24.touragency.order.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteOrder", urlPatterns = "/deleteorder")

public class DeleteOrderController extends BaseController {
    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            if (isParamExists(req, "id")) {
                long id = Long.parseLong(req.getParameter("id"));
                orderService.deleteById(id);
            }
            resp.sendRedirect("orders");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
