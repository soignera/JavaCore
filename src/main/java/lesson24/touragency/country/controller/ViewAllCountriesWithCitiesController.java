package lesson24.touragency.country.controller;

import lesson24.touragency.common.business.application.ServiceSupplier;
import lesson24.touragency.common.business.controller.BaseController;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.dto.CountryDtoConverter;
import lesson24.touragency.country.service.CountryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@WebServlet(name = "ViewAllCountries", urlPatterns = "/countries")
public class ViewAllCountriesWithCitiesController extends BaseController {
    private CountryService countryService = ServiceSupplier.getInstance().getCountryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Country> countries = countryService.findAllCountriesFetchingCities();
            if (isNotEmpty(countries)) {
                req.setAttribute("countries", CountryDtoConverter.convertToDtos(countries));
            }
            forwardToPage(req, resp, "countries.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Couldn't fetch countries!", resp);
        }
    }
}
