package lesson11.touragency.storage.initor.datasourcereader;

import lesson11.touragency.city.domain.City;
import lesson11.touragency.city.domain.CityDiscriminator;
import lesson11.touragency.city.domain.ColdCity;
import lesson11.touragency.city.domain.HotCity;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.storage.initor.checked.InvalidCityDiscriminatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static lesson11.touragency.common.solution.xml.XmlDomUtils.*;
import static lesson11.touragency.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;

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

        String type = cityXml.getAttribute("type");
        if (CityDiscriminator.isDiscriminatorExists(type)) {
            City city = null;
            switch (CityDiscriminator.valueOf(type)) {

                case HOT: {
                    city = new HotCity();
                    HotCity hot = (HotCity) city;
                    hot.setHottestMonth(getOnlyElementTextContent(cityXml, "hottestMonth"));
                    hot.setHottestTemp(parseInt(getOnlyElementTextContent(cityXml, "hottestTemp")));
                    break;
                }
                case COLD: {
                    city = new ColdCity();
                    ColdCity cold = (ColdCity) city;
                    cold.setColdestMonth(getOnlyElementTextContent(cityXml, "coldestMonth"));
                    cold.setColdestTemp(parseInt(getOnlyElementTextContent(cityXml, "coldestTemp")));
                    break;
                }
            }

            city.setName(getOnlyElementTextContent(cityXml, "name"));
            //??climate city.setClimate(parseInt(getOnlyElementTextContent(cityXml, "population")));

            String stringValue = getOnlyElementTextContent(cityXml, "population");

            if (stringValue != null) {
                city.setPopulation(parseInt(stringValue));
            }

            return city;
        } else {
            throw new InvalidCityDiscriminatorException(PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
                    PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(type));
        }
    }
}
