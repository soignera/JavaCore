<%@ page import="javacore.lesson24.touragency.mark.dto.MarkDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="0" width="100%" style="height: 100%;">
    <tr>
        <td colspan="3" align="center">
            <%@ include file="common/header.jsp" %>
        </td>
    </tr>

    <tr>
        <td width="15%" valign="top" bgcolor="#d3d3d3">
            <%@ include file="common/menu.jsp" %>
        </td>

        <td width="70%" valign="top" style="height: 60%">
            <%
                if (request.getAttribute("countries") != null) {
                    List<CountryDto> countries = (List<CountryDto>) request.getAttribute("countries");
            %>
            <table width="100%" style="border-collapse: collapse; border: 1px solid gray" border="1" cellpadding="5px">
                <thead>
                <th>Страна</th>
                <th>Города</th>
                </thead>
                <tbody>
                <%
                    for (CountryDto country : countries) {

                %>
                <tr>
                    <td>
                        <div><b>Название</b> <%= mark.getName()%></div>
                        <div><b>Язык</b> <%= mark.getLanguag()%></div>
                    </td>
                    <td>
                        <%request.setAttribute("cities", country.getCities());%>
                        <jsp:include page="cities.jsp"/>
                    </td>
                </tr>
                <%} %>
                </tbody>
            </table>
            <%}%>
        </td>

        <td valign="top">
            <%@ include file="common/right.jsp" %>
        </td>
    </tr>

    <tr>
        <td colspan="3" align="center" style="height: 15%">
            <%@ include file="common/footer.jsp" %>
        </td>
    </tr>
</table>

</body>
</html>
