package lesson24.touragency.common.solution.web;

import javax.servlet.http.HttpServletRequest;

public final class HttpServletRequestUtils {
    private HttpServletRequestUtils(){

    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }}
