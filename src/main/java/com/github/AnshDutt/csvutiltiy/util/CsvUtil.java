package com.github.AnshDutt.csvutiltiy.util;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AnshDutt.csvutiltiy.model.Atms;
import com.github.AnshDutt.csvutiltiy.model.Branches;
import com.github.opendevl.JFlat;
import org.json.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.AnshDutt.csvutiltiy.util.StringUtils.join;


public class
CsvUtil {

    static int breaker = 0;



    public static Map<String,Object> filterCountAndBrandName(String csvFor) throws IOException {
       Map<String,Object> countNBrandName = new HashMap<>();
        JSONArray content = (JSONArray) ExtractUtil.extractApiContent(csvFor).get("content");
        int rowCount = content.length();
        String brandName = (String) ExtractUtil.extractApiContent(csvFor).get("brandName");
        countNBrandName.put("rowCount",rowCount);
        countNBrandName.put("brandName",brandName);
        return  countNBrandName;
    }
    /**
     * Writes/Download the csv file of the ATM open API response, the current functionality download/Writes the entire
     * response or select record of the response in comma seperated values format.  T
     * <p>
     * This method returns void, & download/write csv file in location of user's choice.
     * <p> As soon as the function completed execution without any exception the data will be written in the file provided by
     * user.The name convention of the file goes as follows fileName give user followed by _BRANDNAME.csv </p>
     *<p>The Items in a list or array is seperated by ;instead of , to avoid conflict in csv file</p>
     * @param  path Available Drives present in the atm
     * @param  csvFor  User provided input, which maps to the respective api
     * @param   fileName Name of the file where the json content is downloaded/writen
     * @param contentCount Number of Records downloaded
     * @return      return void, but download/Write a csv of the response, best visualized in MS Excel
     * @authors Tejaswi Vejandla, Ansh
     */
    public static void jsonCsvWriterAtm(String path, String fileName,String csvFor,int contentCount) throws IOException {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();
        String delimiter = ";";

        File Outfile = new File(path, fileName);
        String usersString;
        FileWriter writer = new FileWriter(Outfile);

        writer.write(HeaderUtils.atmHeaders());

            ObjectMapper mapper =
                    new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Atms[] mappedValue = mapper.readValue(apiContent, Atms[].class);
        for(Atms e: mappedValue){

            if(contentCount > mappedValue.length)
                breaker = mappedValue.length-1;
            if(breaker >= contentCount)
                break;

            List<String> outputData = new ArrayList<>();

            outputData.add(e.getIdentification());
            usersString =String.join(delimiter,e.getSupportedLanguages());

            outputData.add(usersString);
            usersString =String.join(delimiter,e.getAtmServices());
            outputData.add(usersString);
            usersString =String.join(delimiter,e.getAccessibility());
            outputData.add(usersString);
            usersString =String.join(delimiter,e.getSupportedCurrencies());
            String usersString2 = join(e.getSupportedCurrencies());
            outputData.add(usersString2);
            usersString =String.join(delimiter,e.getNote());
            outputData.add(usersString);
            usersString = String.join(delimiter,e.getOtherAccessibility());
            outputData.add(usersString);
            usersString = String.join(delimiter,e.getOtherATMServices());
            outputData.add(usersString);
            usersString =String.join(delimiter,e.getLocation().getLocationCategory());
            outputData.add(usersString);
            usersString =String.join(delimiter,e.getLocation().getOtherLocationCategory());
            outputData.add(usersString);

            usersString =String.join(delimiter,e.getLocation().getPostalAddress().getAddressLine()).replace(",","");
            outputData.add(usersString);

            outputData.add(e.getLocation().getPostalAddress().getTownName());
            usersString =String.join(delimiter,e.getLocation().getPostalAddress().getCountrySubDivision());
            outputData.add(usersString);
            outputData.add(e.getLocation().getPostalAddress().getCountry());
            outputData.add(e.getLocation().getPostalAddress().getPostCode());
            outputData.add(e.getLocation().getPostalAddress().getGeoLocation().getGeographicCoordinates().getLatitude());
            outputData.add(e.getLocation().getPostalAddress().getGeoLocation().getGeographicCoordinates().getLongitude());
            String collect = outputData.stream().collect(Collectors.joining(","));
            breaker++;
            writer.write(collect);
            writer.write("\n");

        }

        writer.close();
        System.out.println(fileName +" dowloaded at "+ Outfile);


    }

    /**
     * Writes/Download the csv file of the Branch open API response, the current functionality download/Writes the entire
     * response or select record of the response in comma seperated values format.  T
     * <p>
     * This method returns void, & download/write csv file in location of user's choice.
     * <p> As soon as the function completed execution without any exception the data will be written in the file provided by
     * user.The name convention of the file goes as follows fileName give user followed by _BRANDNAME.csv </p>
     *<p>The Items in a list or array is seperated by ;instead of , to avoid conflict in csv file</p>
     * @param  path Available Drives present in the atm
     * @param  csvFor  User provided input, which maps to the respective api
     * @param   fileName Name of the file where the json content is downloaded/writen
     * @param contentCount Number of Records downloaded
     * @return      return void, but download/Write a csv of the response, best visualized in MS Excel
     * @authors Tejaswi Vejandla, Ansh
     */
    public static void jsonCsvWriterBranch(String path, String fileName, String csvFor,int contentCount ) throws IOException{


        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();
        String delimiter = ";";
        int breaker = 0;
        File Outfile = new File(path, fileName);
        String usersString;
        FileWriter writer = new FileWriter(Outfile);
        ObjectMapper mapper =
                new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//		Branch[] mappedValue = mapper.readValue(json, Branch[].class);
        Branches[] mappedValue=mapper.readValue(apiContent, Branches[].class);
        writer.write("Identification,SequenceNumber,Name,Type,SortCode,CustomerSegment,ServiceAndFacility,Accessibility,OtherCustomerSegment,OtherAccessibility,OtherServiceAndFacility,Availability,ContactType,ContactContent,AddressLine,TownName,CountrySubDivision, Country,PostCode,Latitude,Longitude\n");

        for (Branches a: mappedValue)
        {
            if(contentCount > mappedValue.length)
                breaker = mappedValue.length-1;
            if(breaker >= contentCount)
                break;
            List<String> outputData = new ArrayList<>();
//			System.out.println(a.getAvailability());
            outputData.add(a.getIdentification());
            outputData.add(a.getSequenceNumber());
            outputData.add(a.getName());
            outputData.add(a.getType());
            outputData.add(String.join(delimiter,a.getSortCode()));
            outputData.add(String.join(delimiter,a.getCustomerSegment()));
            outputData.add(String.join(delimiter,a.getServiceAndFacility()));
            outputData.add(String.join(delimiter,a.getAccessibility()));
            outputData.add(String.join(delimiter,a.getOtherCustomerSegment()));
            outputData.add(String.join(delimiter,a.getOtherAccessibility()));
            outputData.add(String.join(delimiter,a.getOtherServiceAndFacility()));

            if(a.getAvailability()!=null) {

                List dayList = new ArrayList();
                for (int i = 0; i < (a.getAvailability().getStandardAvailability().getDay().size()); i++) {
                    String day = a.getAvailability().getStandardAvailability().getDay().get(i).getName();
                    String start = a.getAvailability().getStandardAvailability().getDay().get(i).getOpeningHours().get(0).getOpeningTime();
                    String end = a.getAvailability().getStandardAvailability().getDay().get(i).getOpeningHours().get(0).getClosingTime();
                    String data = day + "(" + start + " to " + end + ")";
                    dayList.add(data);
                }
//			System.out.println(dayList);

                outputData.add(String.join(delimiter, dayList));
//			System.out.println(a);
            }
            else
            {
                outputData.add(" ");
            }
            outputData.add(String.join(delimiter,a.getContactInfo().get(0).getContactType()));
            outputData.add(String.join(delimiter,a.getContactInfo().get(0).getContactContent()));
            outputData.add((String.join(delimiter,a.getPostalAddress().getAddressLine())).replace(',',';'));
            outputData.add(String.join(delimiter,a.getPostalAddress().getTownName()));
           // outputData.add(String.join(delimiter,a.getPostalAddress().getCountrySubDivision()));
            outputData.add((String.join(delimiter,a.getPostalAddress().getCountrySubDivision())).replace(',',';'));
            outputData.add(String.join(delimiter,a.getPostalAddress().getCountry()));
            outputData.add(String.join(delimiter,a.getPostalAddress().getPostCode()));
            outputData.add(a.getPostalAddress().getGeoLocation().getGeographicCoordinates().getLatitude());
            outputData.add(a.getPostalAddress().getGeoLocation().getGeographicCoordinates().getLongitude());
            breaker++;
            String collect = outputData.stream().collect(Collectors.joining(","));
            writer.write(collect);
            writer.write("\n");
        }
        writer.close();
        System.out.println(fileName +" dowloaded at "+ Outfile);
    }

    public static void jsonCsvWriterAtm(String csvFor) throws Exception {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();

        JFlat flatMe2 = new JFlat(apiContent);

        flatMe2.json2Sheet().headerSeparator(".").write2csv(".\\jsonFiles\\atm123.csv");

    }
    public static void jsonCsvWriterCCC(String csvFor) throws Exception {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();
       // System.out.println(apiContent);
        JFlat flatMe2 = new JFlat(apiContent);

        flatMe2.json2Sheet().headerSeparator(".").write2csv(".\\jsonFiles\\ccc123.csv");

    }
    public static void jsonCsvWriterBranch(String csvFor) throws Exception {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();

        JFlat flatMe2 = new JFlat(apiContent);
        flatMe2.json2Sheet().headerSeparator(".").write2csv(".\\jsonFiles\\branch123.csv");

    }
    public static void jsonCsvWriterSME(String csvFor) throws Exception {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();
        System.out.println(ExtractUtil.extractApiContent(csvFor).getClass().getSimpleName());
        System.out.println(apiContent.getClass().getSimpleName());
        JFlat flatMe2 = new JFlat(apiContent);
        flatMe2.json2Sheet().headerSeparator(".").write2csv(".\\jsonFiles\\sme123.csv");

    }
    public static void jsonCsvWriterBCA(String csvFor) throws Exception {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();
        System.out.println(ExtractUtil.extractApiContent(csvFor).getClass().getSimpleName());
        System.out.println(apiContent.getClass().getSimpleName());
        JFlat flatMe2 = new JFlat(apiContent);
        flatMe2.json2Sheet().headerSeparator(".").write2csv(".\\jsonFiles\\bca123.csv");
    }
    public static void jsonCsvWriterPCA(String csvFor) throws Exception {
        String apiContent = ExtractUtil.extractApiContent(csvFor).get("content").toString();
        System.out.println(ExtractUtil.extractApiContent(csvFor).getClass().getSimpleName());
        System.out.println(apiContent.getClass().getSimpleName());
        JFlat flatMe2 = new JFlat(apiContent);
        flatMe2.json2Sheet().headerSeparator(".").write2csv(".\\jsonFiles\\pca123.csv");

    }


}

