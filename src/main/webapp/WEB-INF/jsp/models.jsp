<%@ page import="javacore.lesson24.touragency.model.dto.ModelDto" %>
<%@ page import="java.util.List" %>
<%@ page import="javacore.lesson24.touragency.model.domain.ModelDiscriminator" %>
<%@ page import="javacore.lesson24.touragency.model.dto.PassengerModelDto" %>
<%@ page import="javacore.lesson24.touragency.model.dto.TruckModelDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<CityDto> cities = (List<CityDto>) request.getAttribute("cities");%>

<%
    if (cities != null && !cities.isEmpty()) {
%>
<table cellpadding="4px">
    <%
        for (CityDto cityDto : cities) {
    %>
    <tr>
        <td>
            <b><%=CityDiscriminator.COLD.equals(cityDto.getDiscriminator()) ? "Холодный" : "Жаркий"%>
            </b>
        </td>
        <td>
            <div><b>Название</b> <%=cityDto.getName()%>
            </div>
            <div><b>Популяция </b><%=cityDto.getPopulation()%>
            </div>

            <%
                if (CityDiscriminator.COLD.equals(cityDto.getDiscriminator())) {
            %>
            <div><b>Холодная температура</b> <%=((ColdCityDto) cityDto).getColdestTemp()%>
            </div>
            <div><b>Холодный месяц</b> <%=((ColdCityDto) cityDto).getColdestMonth()%>
            </div>
            <%
            } else {
            %>
            <div><b>Горячая температура</b> <%=((HotCityDto) cityDto).getHottestTemp()%>
            </div>
            <div><b>Горячий месяц</b> <%=((HotCityDto) cityDto).getHottestMonth()%>
            </div>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>