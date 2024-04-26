package com.atexo.identifiergenerator.service;

import com.atexo.identifiergenerator.model.CriterionConfiguration;
import com.atexo.identifiergenerator.model.CriterionName;
import com.atexo.identifiergenerator.model.GeneratedIdentifier;
import com.atexo.identifiergenerator.model.Subscriber;
import com.atexo.identifiergenerator.utils.FieldExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentifierGeneratorService {
    private final ConfigurationLoaderService configurationLoaderService;

    private final CounterService counterService;

    public IdentifierGeneratorService(ConfigurationLoaderService configurationLoaderService, CounterService counterService) {
        this.configurationLoaderService = configurationLoaderService;
        this.counterService = counterService;
    }

    public GeneratedIdentifier generateSubscriberIdentifier(Subscriber subscriber) throws NoSuchFieldException, IllegalAccessException {
        List<CriterionConfiguration> criteria = configurationLoaderService.loadConfiguration().getCriteria();
        StringBuilder identifier = new StringBuilder();
        for (CriterionConfiguration criterionConfiguration : criteria) {
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
