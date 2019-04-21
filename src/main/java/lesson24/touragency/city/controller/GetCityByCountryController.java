package lesson24.touragency.city.controller;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.dto.CityDto;
import lesson24.touragency.city.dto.CityDtoConverter;
import lesson24.touragency.city.service.CityService;
import lesson24.touragency.common.business.application.ServiceSupplier;
import lesson24.touragency.common.business.controller.BaseController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GetCityByCountryController", urlPatterns = "/getcities")

public class GetCityByCountryController extends BaseController {
    private CityService cityService = ServiceSupplier.getInstance().getCityService();

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<City> cities = getCountryId(req).map(countryId -> cityService.getCitiesByCountryId(countryId)).orElse(Collections.emptyList());
            sentCitiesToClient(cities, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<Long> getCountryId(HttpServletRequest request) {
        try {
            return Optional.of(Long.parseLong(request.getParameter("countryId")));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void sentCitiesToClient(List<City> cities, HttpServletResponse servletResponse) {
        List<CityDto> dtos = CityDtoConverter.convertToDtos(cities);

        PrintWriter writer = null;

        try {
            writer = servletResponse.getWriter();
            Iterator<CityDto> iter = dtos.iterator();
            while (iter.hasNext()) {
                CityDto dto = iter.next();
                writer.write(dto.getId() + ":" + dto.getName());
                if (iter.hasNext()){
                    writer.write(";");
                }

            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }}
