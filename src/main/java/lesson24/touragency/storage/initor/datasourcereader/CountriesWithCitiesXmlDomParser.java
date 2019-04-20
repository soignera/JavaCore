package lesson24.touragency.storage.initor.datasourcereader;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;
import lesson24.touragency.common.solution.parser.FileParser;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.storage.initor.checked.InvalidCityDiscriminatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static lesson24.touragency.common.solution.xml.XmlDomUtils.*;
import static lesson24.touragency.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;

public class CountriesWithCitiesXmlDomParser implements FileParser<List<Country>> {

    @Override
    public List<Country> parseFile(String file) throws Exception {

        Document doc = getDocument(file);
        Element root = getOnlyElement(doc, "countries");

        NodeList xmlCountries = root.getElementsByTagName("country");
        List<Country> result = new ArrayList<>();

        for (int i = 0; i < xmlCountries.getLength(); i++) {
            result.add(getCountryFromXmlElement(xmlCountries.item(i)));
        }
        return result;
    }

    private Country getCountryFromXmlElement(Node xmlCountry) throws Exception {
        Country country = new Country();

        country.setName(getOnlyElementTextContent((Element) xmlCountry, "name"));
        country.setLanguag(getOnlyElementTextContent((Element) xmlCountry, "languag"));

        NodeList cities = ((Element) xmlCountry).getElementsByTagName("city");
        if (cities.getLength() > 0) {
            country.setCities(new ArrayList<>());

            for (int i = 0; i < cities.getLength(); i++) {
                City city = getCityFromXmlElement((Element) cities.item(i));
                country.getCities().add(city);
            }
        }
        return country;
    }

    private City getCityFromXmlElement(Element cityXml) throws Exception {

//        String type = cityXml.getAttribute("type");
//        if (CityDiscriminator.isDiscriminatorExists(type)) {
//            City city = null;
//            switch (CityDiscriminator.valueOf(type)) {
//
//                case HOT: {
//                    city = new HotCity();
//                    HotCity hot = (HotCity) city;
//                    hot.setHottestMonth(getOnlyElementTextContent(cityXml, "hottestMonth"));
//                    hot.setHottestTemp(parseInt(getOnlyElementTextContent(cityXml, "hottestTemp")));
//                    break;
//                }
//                case COLD: {
//                    city = new ColdCity();
//                    ColdCity cold = (ColdCity) city;
//                    cold.setColdestMonth(getOnlyElementTextContent(cityXml, "coldestMonth"));
//                    cold.setColdestTemp(parseInt(getOnlyElementTextContent(cityXml, "coldestTemp")));
//                    break;
//                }
//            }
        return CityDiscriminator.getDiscriminatorByName(cityXml.getAttribute("type"))
                .map(cityDiscriminator -> createCityByDiscriminatorAndXmlElement(cityDiscriminator, cityXml))
                .orElseThrow(() -> new InvalidCityDiscriminatorException(PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
                        PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(cityXml.getAttribute("type"))));
    }
//            city.setName(getOnlyElementTextContent(cityXml, "name"));
            //??climate city.setClimate(parseInt(getOnlyElementTextContent(cityXml, "population")));
private City createCityByDiscriminatorAndXmlElement(CityDiscriminator cityDiscriminator, Element cityXml) {
    City city = null;
    switch (cityDiscriminator) {
        case HOT: {
            city = new HotCity();
            fillHotCity((HotCity) city, cityXml);
            break;
        }
        case COLD: {
            city = new ColdCity();
            fillColdCity((ColdCity) city, cityXml);
            break;
        }
    }
    fillCommonCityData(city, cityXml);
    return city;
}

    private void fillHotCity(HotCity hot, Element cityXml) {
        hot.setHottestMonth(getOnlyElementTextContent(cityXml, "hottestMonth"));
                 hot.setHottestTemp(parseInt(getOnlyElementTextContent(cityXml, "hottestTemp")));
    }
    private void fillColdCity(ColdCity cold, Element cityXml) {
        cold.setColdestMonth(getOnlyElementTextContent(cityXml, "coldestMonth"));
                    cold.setColdestTemp(parseInt(getOnlyElementTextContent(cityXml, "coldestTemp")));
    }

    private void fillCommonCityData(City city, Element cityXml) {
        city.setName(getOnlyElementTextContent(cityXml, "name"));


    }}
//            if (stringValue != null) {
//                city.setPopulation(parseInt(stringValue));
//            }
//
//            return city;
//        } else {
//            throw new InvalidCityDiscriminatorException(PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
//                    PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(type));
//        }
//    }
//}
