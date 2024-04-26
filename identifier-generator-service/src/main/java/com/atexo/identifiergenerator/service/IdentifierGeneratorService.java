package com.atexo.identifiergenerator.service;

import com.atexo.identifiergenerator.model.*;
import com.atexo.identifiergenerator.utils.FieldExtractor;
import org.springframework.stereotype.Service;

@Service
public class IdentifierGeneratorService {
    private final IdentifierGenerationConfiguration identifierGenerationConfiguration;

    private final CounterService counterService;

    public IdentifierGeneratorService(IdentifierGenerationConfiguration identifierGenerationConfiguration, CounterService counterService) {
        this.identifierGenerationConfiguration = identifierGenerationConfiguration;
        this.counterService = counterService;
    }

    public GeneratedIdentifier generateSubscriberIdentifier(Subscriber subscriber) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder identifier = new StringBuilder();
        for (CriterionConfiguration criterionConfiguration : identifierGenerationConfiguration.getOrderedCriteria()) {
            Object fieldValue = getSubscriberFieldValueRelatedToCriterion(subscriber, criterionConfiguration);
            String formattedField = criterionConfiguration.format(fieldValue);
            identifier.append(formattedField);
        }
        return new GeneratedIdentifier(String.valueOf(identifier));
    }

    private Object getSubscriberFieldValueRelatedToCriterion(Subscriber subscriber, CriterionConfiguration criterionConfiguration) throws NoSuchFieldException, IllegalAccessException {
        CriterionName criterionName = criterionConfiguration.getCriterionName();
        if (criterionName == CriterionName.COUNTER) {
            return counterService.getIncrementedCounter();
        }
        return FieldExtractor.extractFieldByName(subscriber, criterionName.fieldName);
    }
}
