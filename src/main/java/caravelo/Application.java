package caravelo;

import caravelo.entities.Provider;
import caravelo.entities.Survey;
import caravelo.repositories.ProviderRepository;
import caravelo.repositories.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(ProviderRepository providerRepository,
                           SurveyRepository surveyRepository) {

        return (evt) ->{{
            Provider provider = providerRepository.save(new Provider("Surveys & Co"));
            surveyRepository.save(new Survey(provider,"Best survey ever"));
            surveyRepository.save(new Survey(provider,"Another survey"));
            surveyRepository.save(new Survey(provider,"This is an important survey"));
        }

        {
            Provider provider = providerRepository.save(new Provider("The Provider #1"));
            surveyRepository.save(new Survey(provider,"Survey 1"));
            surveyRepository.save(new Survey(provider,"Survey 2"));
            surveyRepository.save(new Survey(provider,"Survey 3"));
            surveyRepository.save(new Survey(provider,"Survey 4"));
        }

        {
            Provider provider = providerRepository.save(new Provider("The Provider #2"));
            surveyRepository.save(new Survey(provider,"Survey 101"));
            surveyRepository.save(new Survey(provider,"Survey 201"));
            surveyRepository.save(new Survey(provider,"Survey 301"));
            surveyRepository.save(new Survey(provider,"Survey 404"));
        }};

    }

}