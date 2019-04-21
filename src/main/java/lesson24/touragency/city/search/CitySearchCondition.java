package lesson24.touragency.city.search;

import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.common.business.search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition<Long> {
    private CityDiscriminator cityDiscriminator;

    public CityDiscriminator getCityDiscriminator() {
        return cityDiscriminator;
    }

    public void setCityDiscriminator(CityDiscriminator cityDiscriminator) {
        this.cityDiscriminator = cityDiscriminator;
    }
}

