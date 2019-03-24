package lesson11.touragency.reporting;

import lesson11.touragency.city.service.CityService;
import lesson11.touragency.country.service.CountryService;
import lesson11.touragency.order.service.OrderService;
import lesson11.touragency.user.service.UserService;

import java.io.File;

public class ReportProvider {
    private final UserService userService;
    private final OrderService orderService;
    private final CityService cityService;
    private final CountryService countryService;

    private ReportComponent userOrdersTextFileReport;

    public ReportProvider(UserService userService, OrderService orderService,
                          CityService cityService,CountryService countryService ) {
        this.userService = userService;
        this.orderService = orderService;
        this.cityService = cityService;
        this.countryService = countryService;

        initReportComponents();
    }

    private void initReportComponents() {
        userOrdersTextFileReport = new UserOrdersIoTextFileReport(
                userService,
                orderService,
                countryService,
                cityService);
    }

    public File getUserOrdersTextFileReport() throws Exception {
        return userOrdersTextFileReport.generateReport();
    }
}
