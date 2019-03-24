package lesson11.touragency.city.domain;

import java.util.HashMap;
import java.util.Map;

public enum CityDiscriminator {
    COLD, HOT;
    static Map<String, CityDiscriminator> stringCityDiscriminatorMap = new HashMap<>();

    static {
        for (CityDiscriminator discriminator : CityDiscriminator.values()) {
            stringCityDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static CityDiscriminator getDiscriminatorByName(String discriminatorName) {
        return stringCityDiscriminatorMap.get(discriminatorName);
    }

    public static boolean isDiscriminatorExists(String discriminator) {
        return getDiscriminatorByName(discriminator) != null;
    }

    public static boolean isDiscriminatorNotExists(String discriminator) {
        return !isDiscriminatorExists(discriminator);
    }
}
