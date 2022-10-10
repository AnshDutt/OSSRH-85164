package com.github.AnshDutt.csvutiltiy.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {




    public static ResponseEntity<String> getResponse(String csvFor){
        String url = ResponseUtil.codeMapping().get(csvFor);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response;
    }
    private static Map<String,String> codeMapping(){
        Map<String,String> codeMap = new HashMap<String,String>();
        codeMap.put("atm","https://openapi.natwest.com/open-banking/v2.2/atms");
        codeMap.put("branch","https://openapi.natwest.com/open-banking/v2.2/branches");
        codeMap.put("ccc","https://openapi.natwest.com/open-banking/v2.2/commercial-credit-cards");
        codeMap.put("sme","https://openapi.natwest.com/open-banking/v2.2/unsecured-sme-loans");
        codeMap.put("bca","https://openapi.natwest.com/open-banking/v2.2/business-current-accounts");
        codeMap.put("pca","https://openapi.natwest.com/open-banking/v2.2/personal-current-accounts");

        return codeMap;
    }

}
