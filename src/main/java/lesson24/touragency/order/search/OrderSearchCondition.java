package lesson24.touragency.order.search;

import lesson24.touragency.common.business.search.BaseSearchCondition;

public class OrderSearchCondition extends BaseSearchCondition<Long> {
    private Long userId;
    private Long countryId;
    private Long cityId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public boolean searchByUserId() {
        return userId != null;
    }
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
    public boolean searchByCountryId() {
        return countryId != null;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long modelId) {
        this.cityId = cityId;
    }
public boolean searchByCityId() {
    return cityId != null;
}}

