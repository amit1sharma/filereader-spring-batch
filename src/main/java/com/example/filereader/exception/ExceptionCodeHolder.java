package com.example.filereader.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ExceptionCodeHolder {

   private static final Logger log = LoggerFactory
         .getLogger(ExceptionCodeHolder.class);

   private static final Properties properties = new Properties();
   static {
      final InputStream in = ExceptionCodeHolder.class.getClassLoader().getResourceAsStream("errors/error-codes.properties");
      try {
         properties.load(in);
      } catch (Throwable e) {
         log.error("Exception Code Properties could not be loaded" , e);
      }

   }

   public static final String getMessage(String code) {
      return properties.getProperty(code) == null ? code : properties.getProperty(code);
   }

}