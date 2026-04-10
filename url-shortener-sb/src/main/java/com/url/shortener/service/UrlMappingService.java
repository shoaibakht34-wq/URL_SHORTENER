package com.url.shortener.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMAppingDTO;
import com.url.shortener.models.ClickEvent;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;
import com.url.shortener.repository.ClickEventRepository;
import com.url.shortener.repository.UrlMappingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlMappingService {
     
    private UrlMappingRepository urlMappingRepository;
    private UserService userService;
    private ClickEventRepository clickEventRepository;

    public UrlMAppingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl=generateShortUrl();
        UrlMapping urlMapping=new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping=urlMappingRepository.save(urlMapping);
        return convertToDto(savedUrlMapping);
       // throw new UnsupportedOperationException("Unimplemented method 'createShortUrl'");
    }
    private UrlMAppingDTO convertToDto(UrlMapping urlMapping){
        UrlMAppingDTO urlMAppingDTO=new UrlMAppingDTO();
        urlMAppingDTO.setId(urlMapping.getId());
        urlMAppingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMAppingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMAppingDTO.setClickCount(urlMapping.getClickCount());
        urlMAppingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMAppingDTO.setUsername(urlMapping.getUser().getUsername());
        urlMAppingDTO.setUserId(urlMapping.getUser().getId());
        return urlMAppingDTO;
    }

    private String generateShortUrl() {
        String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuilder shortUrl=new StringBuilder(8);
        for(int i=0;i<8;i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
//        throw new UnsupportedOperationException("Unimplemented method 'generateShortUrl'");
    }
    public UrlMAppingDTO createShortUrl(String originalUrl, String name) {
        
      //  throw new UnsupportedOperationException("Unimplemented method 'createShortUrl'");
    User user = userService.findByUsername(name);
    return createShortUrl(originalUrl, user);
    }
    public List<UrlMAppingDTO> getUrlsByUser(User user) {
        //throw new UnsupportedOperationException("Unimplemented method 'getUrlsByUser'");
        return urlMappingRepository.findByUser(user).stream()
        .map(this::convertToDto)
        .collect(Collectors.toUnmodifiableList());
    }
    public List<ClickEventDTO> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
      // return urlMappingRepository.findByShortUrl(shortUrl).getClickEvents().stream()
      UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
      if(urlMapping !=null){
        return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping, start, end).stream()
        .collect(Collectors.groupingBy(click->click.getClickDate().toLocalDate(), Collectors.counting()))
        .entrySet().stream()
        .map(entry->{
            ClickEventDTO clickEventDTO=new ClickEventDTO();
            clickEventDTO.setClickDate(entry.getKey());
            clickEventDTO.setCount(entry.getValue());
            return clickEventDTO;
        })
        .collect(Collectors.toList());
      }
      return null;
    }
    public Map<LocalDate, Long> getClickByUserAndDate(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMappings=urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents= clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        return clickEvents.stream()
        .collect(Collectors.groupingBy(click ->click.getClickDate().toLocalDate(),Collectors.counting()));
    }
    public UrlMapping getOriginalUrl(String shortUrl) {
       UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
       if(urlMapping != null){
        urlMapping.setClickCount(urlMapping.getClickCount()+1);
        urlMappingRepository.save(urlMapping);
        //record click event
        ClickEvent clickEvent=new ClickEvent();
        clickEvent.setClickDate(LocalDateTime.now());
        clickEvent.setUrlMapping(urlMapping);
        clickEventRepository.save(clickEvent);
       }
       return urlMapping;
    }
    
}
