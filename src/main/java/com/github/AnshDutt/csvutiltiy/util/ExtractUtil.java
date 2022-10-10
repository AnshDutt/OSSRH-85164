package com.github.AnshDutt.csvutiltiy.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractUtil {

    public static Map<String,Object> extractApiContent(String csvFor){
        Map<String,Object> map = new HashMap<>();
        List<String> csvForList = extractionMapper(csvFor);
        ResponseEntity<String> result = ResponseUtil.getResponse(csvFor);
        System.out.println("***");
        System.out.println(result);
        System.out.println("***");
        JSONObject body = new JSONObject(result.getBody());

        JSONArray getData = body.getJSONArray(csvForList.get(0)); //data
        JSONArray getBrand = (JSONArray) getData.getJSONObject(0).get(csvForList.get(1)); //Brand
        JSONArray content = (JSONArray) getBrand.getJSONObject(0).get(csvForList.get(2)); //Branch
        String brandName = (String)getBrand.getJSONObject(0).get("BrandName");
        map.put("brandName",brandName);
        map.put("content",content);

        return map;
    }

    private static List<String> extractionMapper(String csvFor){
        Map<String,List<String>> extractMap = new HashMap<String,List<String>>();
        List<String> atmList = Arrays.asList("data","Brand","ATM");
        List<String> branchList = Arrays.asList("data","Brand","Branch");
        List<String> cccList = Arrays.asList("data","Brand","CCC");
        List<String> bcaList = Arrays.asList("data","Brand","BCA");
        List<String> pcaList = Arrays.asList("data","Brand","PCA");
        List<String> smeList = Arrays.asList("data","Brand","SMELoan");



        extractMap.put("atm",atmList);
        extractMap.put("branch",branchList);
        extractMap.put("ccc",cccList);
        extractMap.put("sme",smeList);
        extractMap.put("bca",bcaList);
        extractMap.put("pca",pcaList);
        return extractMap.get(csvFor);
    }

}
