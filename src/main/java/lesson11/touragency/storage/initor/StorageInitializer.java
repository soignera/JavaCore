package lesson11.touragency.storage.initor;

import lesson11.touragency.country.domain.Country;
import lesson11.touragency.country.service.CountryService;
import lesson11.touragency.storage.initor.datasourcereader.CountriesWithCitiesTxtFileParser;
import lesson11.touragency.storage.initor.datasourcereader.CountriesWithCitiesXmlStaxParser;
import lesson11.touragency.storage.initor.datasourcereader.FileParser;

import java.util.List;

public class StorageInitializer {
    private CountryService countryService;

    public StorageInitializer(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    public void initStorageWithCountriesAndCities(String filePath, DataSourceType dataSourceType) throws Exception {
        List<Country> countriesToPersist = getCountriesFromStorage(filePath, dataSourceType);

        if (!countriesToPersist.isEmpty()) {
            for (Country country : countriesToPersist) {
                countryService.add(country);
            }
        }
    }

    private List<Country> getCountriesFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {

        FileParser<List<Country>> dataSourceReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataSourceReader = new CountriesWithCitiesTxtFileParser();
                break;
            }

            case XML_FILE: {
                //dataSourceReader = new CountrysWithCitysXmlDomParser();
                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
                //    dataSourceReader = new CountrysWithCitysXmlSaxParser();
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }
}
