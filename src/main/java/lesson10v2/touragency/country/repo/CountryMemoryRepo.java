//package lesson10v2.touragency.country.repo;
//
//
//import lesson10v2.touragency.common.solution.ArrayUtils;
//import lesson10v2.touragency.country.domain.Country;
//import lesson10v2.touragency.country.search.CountrySearchCondition;
//
//import static lesson10v2.touragency.common.solution.StringUtils.isNotBlank;
//import static lesson10v2.touragency.storage.Storage.countries;
//
//public class CountryMemoryRepo implements CountryRepo {
//    private static final Country[] EMPTY_COUNTRIES_ARR = new Country[0];
//    private int countryIndex = -1;
//
//    @Override
//    public void add(Country country) {
//        if (countryIndex == countries.length - 1) {
//            Country[] newArrCountrys = new Country[countries.length * 2];
//            System.arraycopy(countries, 0, newArrCountrys, 0, countries.length);
//            countries = newArrCountrys;
//        }
//
//        countryIndex++;
//        countries[countryIndex] = country;
//    }
//
//    @Override
//    public Country findById(Long id) {
//        Integer countryIndex = findCountryIndexById(id);
//        if (countryIndex != null) {
//            return countries[countryIndex];
//        }
//
//        return null;
//    }
//
//    @Override
//    public Country[] search(CountrySearchCondition searchCondition) {
//
//        if (searchCondition.getId() != null) {
//            return new Country[]{findById(searchCondition.getId())};
//        } else {
//            boolean searchByLanguag = isNotBlank(searchCondition.getLanguag());
//
//            boolean searchByName = isNotBlank(searchCondition.getName());
//
//            Country[] result = new Country[countries.length];
//            int resultIndex = 0;
//
//            for (Country country : countries) {
//                if (country != null) {
//                    boolean found = true;
//
//                    if (searchByLanguag) {
//                        found = searchCondition.getLanguag().equals(country.getLanguag());
//                    }
//
//                    if (found && searchByName) {
//                        found = searchCondition.getName().equals(country.getName());
//                    }
//
//                    if (found) {
//                        result[resultIndex] = country;
//                        resultIndex++;
//                    }
//                }
//            }
//
//            if (resultIndex > 0) {
//                Country toReturn[] = new Country[resultIndex];
//                System.arraycopy(result, 0, toReturn, 0, resultIndex);
//                return toReturn;
//            }
//        }
//        return EMPTY_COUNTRIES_ARR;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        Integer countryIndex = findCountryIndexById(id);
//
//        if (countryIndex != null) {
//            deleteCountryByIndex(countryIndex);
//        }
//    }
//
//    private void deleteCountryByIndex(int index) {
//        ArrayUtils.removeElement(countries, index);
//        countryIndex--;
//    }
//
//    @Override
//    public void printAll() {
//        for (Country country : countries) {
//            if (country != null) {
//                System.out.println(country);
//            }
//        }
//    }
//
//    private Integer findCountryIndexById(Long countryId) {
//        for (int i = 0; i < countries.length; i++) {
//            if (countries[i] != null && Long.valueOf(countryId).equals(countries[i].getId())) {
//                return i;
//            }
//        }
//        return null;
//    }
//
//}
