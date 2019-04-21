package lesson24.touragency.user.controller;

import lesson24.touragency.common.business.application.ServiceSupplier;
import lesson24.touragency.common.business.controller.BaseController;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.dto.UserDtoConverter;
import lesson24.touragency.user.service.UserService;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ViewAllUsers", urlPatterns = "/users")
public class ViewAllUsersController extends BaseController {

    private UserService userService = ServiceSupplier.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<User> users = userService.findAll();

            if (CollectionUtils.isNotEmpty(users)) {
                req.setAttribute("users", UserDtoConverter.convertToDtos(users));
            }

            forwardToPage(req, resp, "users.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Couldn't fetch all user!", resp);
        }
    }}
