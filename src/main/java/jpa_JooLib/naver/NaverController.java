package jpa_JooLib.naver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/")
public class NaverController {
	
	private static String NAVER_API_CLIENT_ID;
    private static String NAVER_API_CLIENT_SECRET;

    static {
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = NaverController.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("config.properties");

            properties.load(inputStream);
            NAVER_API_CLIENT_ID = properties.getProperty("naver.api.clientId");
            NAVER_API_CLIENT_SECRET = properties.getProperty("naver.api.clientSecret");
        } catch (IOException e) {
            NAVER_API_CLIENT_ID = null;
            NAVER_API_CLIENT_SECRET = null;
        }
    }
	
    @RequestMapping("naverBooks")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        String booksJson = findBooks(query);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> books = null;

        try {
            books = objectMapper.readValue(booksJson, new TypeReference<Map<String, Object>>() {});
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("books", books);

        return "book/naverBooks";
    }
    
	private String findBooks(String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String urlString = "https://openapi.naver.com/v1/search/book.json?query=" + encodedQuery;
            URI uri = new URI(urlString);

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", NAVER_API_CLIENT_ID);
            headers.set("X-Naver-Client-Secret", NAVER_API_CLIENT_SECRET);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                System.out.println("fail : " + responseEntity.getStatusCode());
                return "{}";
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return "{}";
        }
    }

}

//@RequestMapping("naverBooks")
//public String searchBooks(@RequestParam("query") String query, Model model) {
//
//  String booksJson = findBooks(query);
//  model.addAttribute("booksJson", booksJson);
//
//  return "book/naverBooks";
//}
