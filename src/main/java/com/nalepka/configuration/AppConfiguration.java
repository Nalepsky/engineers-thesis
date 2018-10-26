package com.nalepka.configuration;

import com.nalepka.repository.WeaponDao;
import com.nalepka.repository.impl.WeaponDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = {"com.nalepka.repository"})
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class AppConfiguration implements WebMvcConfigurer {
    public ApplicationContext applicationContext;

    public AppConfiguration(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Bean
    public WeaponDao weaponDao(){
        return new WeaponDaoImpl();
    }
}
