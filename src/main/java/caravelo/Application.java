package caravelo;

import caravelo.entities.Customer;
import caravelo.entities.Provider;
import caravelo.entities.Subscription;
import caravelo.entities.Survey;
import caravelo.repositories.CustomerRepository;
import caravelo.repositories.ProviderRepository;
import caravelo.repositories.SubscriptionRepository;
import caravelo.repositories.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    private static Survey fillSurveyRandomly(Survey survey){
        Random r = new Random();

        int ageL = r.ints(1, 2, 7).findFirst().getAsInt();
        int ageH = r.ints(1, ageL+1, 9).findFirst().getAsInt();
        survey.setTargetAgeL(ageL*10);
        survey.setTargetAgeH(ageH*10);

        int genderIndex = r.ints(1, 0, 3).findFirst().getAsInt();
        survey.setTargetGender(Survey.TargetGender.values()[genderIndex]);

        int currencyIndex = r.ints(1, 0, 3).findFirst().getAsInt();
        survey.setTargetIncomeCurrency(Survey.Currency.values()[currencyIndex]);

        int incomeL = r.ints(1, 5, 40).findFirst().getAsInt();
        int incomeH = r.ints(1, incomeL+1, 80).findFirst().getAsInt();
        survey.setTargetIncomeL(incomeL*1000);
        survey.setTargetIncomeH(incomeH*1000);

        return survey;
    }

        //populating database
    @Bean
    CommandLineRunner init(ProviderRepository providerRepository,
                           SurveyRepository surveyRepository,
                           CustomerRepository customerRepository,
                           SubscriptionRepository subscriptionRepository) {

        return (evt) ->{

            //Adding provider to DB.
            {
                Provider provider = providerRepository.save(new Provider("Surveys & Co"));

                {
                    Survey survey = fillSurveyRandomly(new Survey(provider, "Best survey ever"));
                    surveyRepository.save(survey);
                }
                {
                    Survey survey = fillSurveyRandomly(new Survey(provider, "Another survey"));
                    surveyRepository.save(survey);
                }
                {
                    Survey survey = fillSurveyRandomly(new Survey(provider, "This is an important survey"));
                    surveyRepository.save(survey);
                }
            }

            {
                Provider provider = providerRepository.save(new Provider("The Provider #1"));
                for(int i=0; i<99; i++){
                    Survey survey = fillSurveyRandomly(new Survey(provider, "Survey 10"+i));
                    surveyRepository.save(survey);
                }
            }

            {
                Provider provider = providerRepository.save(new Provider("The Provider #2"));
                for(int i=0; i<5; i++){
                    Survey survey = fillSurveyRandomly(new Survey(provider, "Survey 20"+i));
                    surveyRepository.save(survey);
                }
            }

            {
                Provider provider = providerRepository.save(new Provider("The Provider #3"));
                for(int i=0; i<5; i++){
                    Survey survey = fillSurveyRandomly(new Survey(provider, "Survey 30"+i));
                    surveyRepository.save(survey);
                }
            }


            //Adding customers.
            {
                Customer customer =  customerRepository.save(new Customer("Rafael Ferrero"));
                subscriptionRepository.save(new Subscription(
                        customer,
                        Subscription.Frequency.DAYLY,
                        Subscription.Channel.MAIL));
                subscriptionRepository.save(new Subscription(
                        customer,
                        Subscription.Frequency.MONTHLY,
                        Subscription.Channel.MAIL));
            }

            {
                Customer customer =  customerRepository.save(new Customer("Caravelo Customer"));
                subscriptionRepository.save(new Subscription(
                        customer,
                        Subscription.Frequency.DAYLY,
                        Subscription.Channel.MAIL));
                subscriptionRepository.save(new Subscription(
                        customer,
                        Subscription.Frequency.MONTHLY,
                        Subscription.Channel.MAIL));
            }
        };

    }

}