package lesson24.touragency.order.controller;

import lesson24.touragency.city.domain.ProxyCity;
import lesson24.touragency.common.business.application.ServiceSupplier;
import lesson24.touragency.common.business.controller.BaseController;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.dto.CountryDtoConverter;
import lesson24.touragency.country.service.CountryService;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.service.OrderService;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.dto.UserDtoConverter;
import lesson24.touragency.user.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AddEditOrderController", urlPatterns = "/addeditorder")

public class AddEditOrderController extends BaseController {
    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();
    private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
    private UserService userService = ServiceSupplier.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("users", UserDtoConverter.convertToDtos(userService.findAll()));
            req.setAttribute("countries", CountryDtoConverter.convertToDtos(countryService.findAll()));

            if (isParamExists(req, "id")) {
                setRequestAttributesIfEditOrder(req, resp);
            }

            forwardToPage(req, resp, "addeditorder.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Error while prepare data to create order", resp);
        }
    }

    private void setRequestAttributesIfEditOrder(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        Long orderId = Long.parseLong(request.getParameter("id"));
        Optional<Order> order = orderService.findById(orderId);

        if (order.isPresent()) {
            request.setAttribute("editedOrder", order.get());
        } else {
            //no such order
            resp.sendRedirect("orders");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (isParamExists(req, "editedOrderId")) {
                updateOrder(req);
            } else {
                addNewOrder(req);
            }

            resp.sendRedirect("orders");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Error while add/edit order", resp);
        }
    }

    private void addNewOrder(HttpServletRequest req) {
        Order order = createOrderFromRequest(req);
        orderService.add(order);
    }

    private void updateOrder(HttpServletRequest req) {
        Order order = createOrderFromRequest(req);
        order.setId(Long.parseLong(req.getParameter("editedOrderId")));
        orderService.update(order);
    }

    private Order createOrderFromRequest(HttpServletRequest req) {
        Order order = new Order();
        order.setPrice(Integer.parseInt(req.getParameter("price")));
        order.setDescription(req.getParameter("description"));
        order.setUser(new User(Long.parseLong(req.getParameter("userId"))));
        order.setCountry(new Country(Long.parseLong(req.getParameter("countryId"))));
        order.setCity(new ProxyCity(Long.parseLong(req.getParameter("cityId"))));

        return order;
    }
}
