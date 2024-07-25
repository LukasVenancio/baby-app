package com.homebaby.requests;

import com.homebaby.enums.EmailTemplate;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Map;

@RequestScope
public record SendTestEmailWithTemplateRequest(
        List<String> receiver,
        String subject,
        EmailTemplate template,
        Map<String, Object> data
) {
}
