package com.github.AnshDutt.csvutiltiy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Atms {
            @CsvBindByName
            @JsonProperty("Identification")
            private String identification;
            @CsvBindByName
            @JsonProperty("SupportedLanguages")
            private List<String> supportedLanguages;
    @CsvBindByName
            @JsonProperty("ATMServices")
            private List<String> atmServices;
    @CsvBindByName
            @JsonProperty("Accessibility")
            private List<String> accessibility;
    @CsvBindByName
            @JsonProperty("SupportedCurrencies")
            private List<String> supportedCurrencies;
    @CsvBindByName
            @JsonProperty("Note")
            private List<String> note;
    @CsvBindByName
            @JsonProperty("OtherAccessibility")
            private List<String> otherAccessibility;
    @CsvBindByName
            @JsonProperty("OtherATMServices")
            private List<String> otherATMServices;
            @CsvRecurse
            @JsonProperty("Location")
            private Location location;
            @Getter
            @Setter
            @ToString
            @NoArgsConstructor
            public static class Location{
                @CsvBindByName
                @JsonProperty("LocationCategory")
                private List<String> locationCategory;
                @CsvBindByName
                @JsonProperty("OtherLocationCategory")
                private List<String> otherLocationCategory;
                @CsvRecurse
                @JsonProperty("PostalAddress")
                private PostalAddress postalAddress;

            }
            @Getter
            @Setter
            @ToString
            @NoArgsConstructor
            public static class PostalAddress{
                @CsvBindByName
                @JsonProperty("AddressLine")
                private List<String> AddressLine;
                @CsvBindByName
                @JsonProperty("TownName")
                private String townName;
                @CsvBindByName
                @JsonProperty("CountrySubDivision")
                private List<String> countrySubDivision;
                @CsvBindByName
                @JsonProperty("Country")
                private String country;
                @CsvBindByName
                @JsonProperty("PostCode")
                private String  postCode;
                @CsvRecurse
                @JsonProperty("GeoLocation")
                private GeoLocation geoLocation;

            }
            @Getter
            @Setter
            @ToString
            @NoArgsConstructor
            public static class GeoLocation{
                @CsvRecurse
                @JsonProperty("GeographicCoordinates")
                private GeographicCo_ordinates geographicCoordinates;
            }

            @Getter
            @Setter
            @ToString
            @NoArgsConstructor
            public static class GeographicCo_ordinates{
                @CsvBindByName
                @JsonProperty("Latitude")
                private String latitude;
                @CsvBindByName
                @JsonProperty("Longitude")
                private String  longitude;
            }
}


