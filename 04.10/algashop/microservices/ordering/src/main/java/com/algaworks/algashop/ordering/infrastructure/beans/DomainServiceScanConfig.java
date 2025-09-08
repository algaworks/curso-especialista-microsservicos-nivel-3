package com.algaworks.algashop.ordering.infrastructure.beans;

import com.algaworks.algashop.ordering.domain.model.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.algaworks.algashop.ordering.domain.model",
    includeFilters = @ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            classes = DomainService.class
    )
)
public class DomainServiceScanConfig {
}
