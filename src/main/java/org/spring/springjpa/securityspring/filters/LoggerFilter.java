package org.spring.springjpa.securityspring.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class LoggerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Request Method: {}, Request URI: {}", request.getMethod(), request.getRequestURI());
        log.info("Request Headers: {}", request.getHeaderNames());
        log.info("Request Parameters: {}", request.getParameterMap());
        log.info("Request Remote Address: {}", request.getRemoteAddr());
        log.info("Request Remote Host: {}", request.getRemoteHost());
        log.info("Request Remote Port: {}", request.getRemotePort());
        log.info("Request Local Address: {}", request.getLocalAddr());
        log.info("Request Local Name: {}", request.getLocalName());
        log.info("Request Local Port: {}", request.getLocalPort());
        log.info("Request Protocol: {}", request.getProtocol());
        log.info("Request Scheme: {}", request.getScheme());
        log.info("Request Content Type: {}", request.getContentType());
        log.info(response.getStatus() == 200 ? "Request processed successfully" : "Request processing failed");
        log.info("Response Status: {}", response.getStatus());
        log.info("Response Headers: {}", response.getHeaderNames());

        log.info("Response Content Type: {}", response.getContentType());
        log.info("Response Character Encoding: {}", response.getCharacterEncoding());
        log.info("Response Locale: {}", response.getLocale());
        log.info("Response Buffer Size: {}", response.getBufferSize());
        log.info("Response Committed: {}", response.isCommitted());
        log.info("Response Buffer Size: {}", response.getBufferSize());
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Error during request processing: {}", e.getMessage());
            throw e; // Re-throw the exception to let it be handled by the next filter or servlet
        };
    }
}
