package com.url.shortener.dtos;

import java.time.LocalDateTime;

//import com.url.shortener.models.User;

import lombok.Data;
@Data
public class UrlMAppingDTO {
   private Long id;
   private String originalUrl;
   private String shortUrl;
   private int clickCount;
   private LocalDateTime createdDate;
   private Long userId;
   private String username;
} 
