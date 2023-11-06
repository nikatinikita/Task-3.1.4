package web.consumer;

import web.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Consumer {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
    private static final String URL = "http://94.198.50.185:7081/api/users";
    private static List<String> set_cookie;
    public static String result = "";



    public String getAllUsers() {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        set_cookie = response.getHeaders().get("Set-Cookie");
        return response.getBody();
    }

    public void createUser(User user) {
        headers.set("Cookie", String.join(";", set_cookie));
        headers.add("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody  = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, requestBody, String.class);
        result += response.getBody();
    }

    public void editUser(User user) {
        headers.set("cookie", String.join(";", set_cookie));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, String.class);
        result += response.getBody();
    }

    public void deleteUser(Long id) {
        headers.set("Cookie", String.join(";", set_cookie));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<User> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> response =  restTemplate.exchange(URL + "/" + id,
                HttpMethod.DELETE, requestBody, String.class);
        result += response.getBody();
    }


}
