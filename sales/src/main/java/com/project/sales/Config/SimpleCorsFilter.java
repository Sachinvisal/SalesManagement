package com.project.sales.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    @Value("${app.client.url}")
     private String clientAppUrl ="";
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException{
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request =(HttpServletRequest) req;
        Map<String,String> map=new HashMap<>();
        String originHeader = request.getHeader("origin");
        response.setHeader("Access");

    }
}
