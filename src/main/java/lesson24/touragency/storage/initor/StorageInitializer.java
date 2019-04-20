package lesson24.touragency.storage.initor;

import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.service.CountryService;
import lesson24.touragency.storage.initor.CountryCityFileParser;
import lesson24.touragency.storage.initor.checked.CountryCityParseXmlFileException;
import lesson24.touragency.storage.initor.datasourcereader.CountriesWithCitiesTxtFileParser;
import lesson24.touragency.storage.initor.datasourcereader.CountriesWithCitiesXmlStaxParser;
import lesson24.touragency.common.solution.parser.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static lesson24.touragency.storage.initor.exception.InitDataExceptionMeta.PARSE_COUNTRY_CITY_ERROR;

public class StorageInitializer {
    private final CountryService countryService;

    public StorageInitializer(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

//    public void initStorageWithCountriesAndCities(String filePath, DataSourceType dataSourceType) throws Exception {
//        List<Country> countriesToPersist = getCountriesFromStorage(filePath, dataSourceType);
//
//        if (!countriesToPersist.isEmpty()) {
//            for (Country country : countriesToPersist) {
//                countryService.add(country);
//            }
//        }
//    }
public void initStorageWithCountriesAndCities(List<File> files, DataSourceType dataSourceType) throws Exception {
    List<CountryCityFileParser> countryCityFileParser = prepareAsyncParsers(files, dataSourceType);
    List<Country> countriesToPersist = asyncParseFilesAndWaitForResult(countryCityFileParser);
    countryService.add(countriesToPersist);
}
    private List<CountryCityFileParser> prepareAsyncParsers(List<File> files, DataSourceType dataSourceType) {
        List<CountryCityFileParser> countryCityFileParsers = new ArrayList<>();
        for (File file : files) {
            countryCityFileParsers.add(new CountryCityFileParser(dataSourceType, file));
        }
        return countryCityFileParsers;
    }

    private List<Country> asyncParseFilesAndWaitForResult(List<CountryCityFileParser> workers) throws Exception {
        for (CountryCityFileParser worker : workers) {
            worker.asyncParseCountries();
        }

        List<Country> countriesToPersist = new ArrayList<>();
        for (CountryCityFileParser worker : workers) {
            worker.blockUntilJobIsFinished();
            if (worker.getParseException() != null) {
                throw new CountryCityParseXmlFileException(PARSE_COUNTRY_CITY_ERROR.getCode(), PARSE_COUNTRY_CITY_ERROR.getDescription(), worker.getParseException());
            }
            countriesToPersist.addAll(worker.getCountries());
        }

        return countriesToPersist;

    }

//
//    private List<Country> getCountriesFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {
//
//        FileParser<List<Country>> dataSourceReader = null;
//
//        switch (dataSourceType) {
//
//            case TXT_FILE: {
//                dataSourceReader = new CountriesWithCitiesTxtFileParser();
//                break;
//            }
//
//            case XML_FILE: {
//                //dataSourceReader = new CountrysWithCitysXmlDomParser();
//                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
//                //    dataSourceReader = new CountrysWithCitysXmlSaxParser();
//                break;
//            }
//        }
//
//        return dataSourceReader.parseFile(filePath);
//    }
}
