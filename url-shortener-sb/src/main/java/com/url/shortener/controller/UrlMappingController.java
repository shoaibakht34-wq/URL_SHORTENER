package com.url.shortener.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMAppingDTO;
import com.url.shortener.models.User;
import com.url.shortener.service.UrlMappingService;
import com.url.shortener.service.UserService;

import lombok.AllArgsConstructor;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMAppingDTO> createShortUrl(@RequestBody Map<String, String>request,Principal principal){
        String originalUrl=request.get("originalUrl");
        userService.findByUsername(principal.getName());
        //call service ,ethod
        UrlMAppingDTO urlmappingDTO=urlMappingService.createShortUrl(originalUrl,principal.getName());
        return ResponseEntity.ok(urlmappingDTO);
    }
    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMAppingDTO>> getUserUrls(Principal principal){
        if (principal == null) {
        return ResponseEntity.status(401).build();
    }
        User user= userService.findByUsername(principal.getName());
        List<UrlMAppingDTO> urls= urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
}
     

    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDTO>> getUrlAnalytics(@PathVariable String shortUrl,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        DateTimeFormatter formattter=DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start=LocalDateTime.parse(startDate,formattter);
        LocalDateTime end=LocalDateTime.parse(endDate,formattter);
       List<ClickEventDTO> clickEventDTOs= urlMappingService.getClickEventsByDate(shortUrl,start,end);
        return ResponseEntity.ok(clickEventDTOs);
    }



     @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
      DateTimeFormatter formattter=DateTimeFormatter.ISO_LOCAL_DATE;
      User user=userService.findByUsername(principal.getName());
        LocalDate start=LocalDate.parse(startDate,formattter);
        LocalDate end=LocalDate.parse(endDate,formattter);
       Map<LocalDate, Long> totalClicks= urlMappingService.getClickByUserAndDate(user,start,end);
        return ResponseEntity.ok(totalClicks);
    }
    
}
