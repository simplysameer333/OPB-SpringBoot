package com.banking.opb.clientapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class AddAuthRequestInterceptor implements RequestInterceptor {
    private String directLoginPath;

    public AddAuthRequestInterceptor(@Value("${obp.api.directLoginPath}") String directLoginPath) {

        this.directLoginPath = directLoginPath;
    }

    @Override
    public void apply(RequestTemplate template) {
        //skip login request, no auth context to add.
        if(directLoginPath.equals(template.url())) {
            return;
        }
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) return;

        String authToken = (String) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials();
        String dlHeader = String.format("DirectLogin token=%s", authToken);
        template.header(HttpHeaders.AUTHORIZATION, dlHeader);
    }
}
