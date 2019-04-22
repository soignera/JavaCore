<%@ page import="javacore.lesson24.touragency.country.dto.CountryDto" %>
<%@ page import="javacore.lesson24.touragency.country.user.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="javacore.lesson24.touragency.country.order.domain.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/resources/scripts/jquery-3.3.1.min.js"></script>
</head>
<body>

<table border="0" width="100%" style="height: 100%;">
    <tr>
        <td colspan="3"align="center">
            <%@ include file="common/header.jsp" %>
        </td>
    </tr>

    <tr>
        <td width="15%" valign="top" bgcolor="#d3d3d3">
            <%@ include file="common/menu.jsp" %>
        </td>

        <td width="70%" valign="top" style="height: 60%">
            <form method="post" action="<%=request.getContextPath() + "/addeditorder"%>">
                <table>
                    <tr>
                        <td>Клиент:</td>
                        <td>
                            <select name="userId" id="userCombo">
                                <option value=""></option>
                                <%
                                    Order editedOrder = null;
                                    if (request.getAttribute("editedOrder") != null) {
                                        editedOrder = (Order) request.getAttribute("editedOrder");
                                    }

                                    if (request.getAttribute("users") != null) {
                                        List<UserDto> users = (List<UserDto>) request.getAttribute("users");
                                        for (UserDto user : users) {

                                            String selected = "";
                                            if (editedOrder != null && user.getId().equals(editedOrder.getUser().getId())) {
                                                selected = "selected=\"selected\"";
                                            }
                                %>


                                <option value="<%=user.getId()%>" <%=selected%>><%=user.getLastName() + " " + user.getFirstName()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>Страна</td>
                        <td>
                            <select name="countryId" id="countryCombo">
                                <option value=""></option>
                                <%
                                    if (request.getAttribute("countries") != null) {
                                        List<CountryDto> countries = (List<CountryDto>) request.getAttribute("countries");
                                        for (CountryDto country : countries) {

                                            String selected = "";
                                            if (editedOrder != null && mark.getId().equals(editedOrder.getCountry().getId())) {
                                                selected = "selected=\"selected\"";
                                            }

                                %>
                                <option value="<%=country.getId()%>" <%=selected%>><%=country.getName()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>Город:</td>
                        <td><select name="cityId" id="cityCombo"></select></td>
                    </tr>

                    <tr>
                        <td>Путевка:</td>
                        <td><input type="text" name="description"
                                   value="<%=editedOrder!=null ? editedOrder.getDescription():""%>"/></td>
                    </tr>

                    <tr>
                        <td>Цена</td>
                        <td><input type="text" name="price" value="<%=editedOrder!=null ? editedOrder.getPrice():""%>"/>
                        </td>
                    </tr>

                </table>
                <%
                    if (request.getParameterMap().containsKey("id")) {
                %>
                <input type="hidden" name="editedOrderId" value="<%=request.getParameter("id")%>"/>
                <%
                    }
                %>
                <input type="submit" value="<%=editedOrder!=null ? "Редактировать заказ" : "Добавить заказ"%>">
            </form>
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

<script type="text/javascript">

    function getCitiesForSelectedCountry(countryId) {
        $.ajax({
            url: "<%=request.getContextPath() + "/getcities?countryId="%>" + countryId
        }).done(
            function (data) {

                var cityCombo = $('#cityCombo');
                $(cityCombo).empty();
                $(cityCombo).append('<option value="" selected="selected"></option>');

                data.split(";").forEach(function (cityIdNamePairStr) {
                    var splitted = cityIdNamePairStr.split(":");
                    $(cityCombo).append('<option value="' + splitted[0] + '">' + splitted[1] + '</option>');

                    <%
                    if (editedOrder!=null){
                    %>
                    $("#cityCombo").val("<%=editedOrder.getCity().getId()%>");
                    <%
                    }
                    %>
                });
            });
    }

    $('#countryCombo').on('change', function () {
        getCitiesForSelectedCountry(this.value)
    });


    <%
        if (editedOrder != null) {
    %>
    $(document).ready(function () {
        getCitiesForSelectedCountry('<%=editedOrder.getCountry().getId()%>');
    });
    <%
        }
    %>
</script>
</html>
