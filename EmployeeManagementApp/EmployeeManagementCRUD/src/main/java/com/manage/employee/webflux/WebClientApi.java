package com.manage.employee.webflux;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/web_client_api")
public class WebClientApi {
    @GetMapping("weather_forecast")
    public ResponseEntity<String> getWeatherForecast(@RequestParam("location") String location, @RequestParam("days") String days) {

        WebClient client = WebClient.create();

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("q", location);
        urlVariables.put("days", days);

        String weatherMono = client.get()
                .uri("https://weatherapi-com.p.rapidapi.com/forecast.json", urlVariables)
                .headers(httpHeaders -> {
                    httpHeaders.add("X-RapidAPI-Key", "89979732a4msh09d8e5d2b0cca98p139815jsna8f2c02adad9");
                    httpHeaders.add("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");

                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(weatherMono);
    }
}
