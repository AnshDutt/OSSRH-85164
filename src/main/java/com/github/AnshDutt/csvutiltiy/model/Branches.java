package com.github.AnshDutt.csvutiltiy.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Branches {
        @JsonProperty("Identification")
        private String identification;
        @JsonProperty("SequenceNumber")
        private String sequenceNumber;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Type")
        private String type;
        @JsonProperty("SortCode")
        private List<String> sortCode;
        @JsonProperty("CustomerSegment")
        private List<String> customerSegment;
        @JsonProperty("ServiceAndFacility")
        private List<String> serviceAndFacility;
        @JsonProperty("Accessibility")
        private List<String> accessibility;
        @JsonProperty("OtherCustomerSegment")
        private List<String> otherCustomerSegment;
        @JsonProperty("OtherAccessibility")
        private List<String> otherAccessibility;
        @JsonProperty("OtherServiceAndFacility")
        private List<String> otherServiceAndFacility;
        @JsonProperty("Availability")
        private AvailabilityDetails availability;
        @JsonProperty("ContactInfo")
        private List<ContactInfoDetails> contactInfo;
        @JsonProperty("PostalAddress")
        private PostalDetails postalAddress;


        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class AvailabilityDetails{
            @JsonProperty("StandardAvailability")
            private StandardAvailabilityDetails standardAvailability;
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class StandardAvailabilityDetails{
            @JsonProperty("Day")
            private List<DayDetails> day;
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class DayDetails{
            @JsonProperty("Name")
            private String name;
            @JsonProperty("OpeningHours")
            private List<OpeningHoursDetails> openingHours;

        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class OpeningHoursDetails{
            @JsonProperty("OpeningTime")
            private String openingTime;
            @JsonProperty("ClosingTime")
            private String ClosingTime;
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class ContactInfoDetails{
            @JsonProperty("ContactType")
            private String contactType;
            @JsonProperty("ContactContent")
            private String contactContent;
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class PostalDetails{
            @JsonProperty("AddressLine")
            private List<String> addressLine;
            @JsonProperty("TownName")
            private String townName;
            @JsonProperty("CountrySubDivision")
            private List<String> countrySubDivision;
            @JsonProperty("Country")
            private String country;
            @JsonProperty("PostCode")
            private String  postCode;
            @JsonProperty("GeoLocation")
            private GeoLocationDetails GeoLocation;

        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class GeoLocationDetails{
            @JsonProperty("GeographicCoordinates")
            private GeographicCo_ordinates geographicCoordinates;
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        public static class GeographicCo_ordinates{
            @JsonProperty("Latitude")
            private String latitude;
            @JsonProperty("Longitude")
            private String  longitude;
        }
}
