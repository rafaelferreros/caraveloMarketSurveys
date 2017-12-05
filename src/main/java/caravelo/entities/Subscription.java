package caravelo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
public class Subscription {

    public enum Frequency {
        DAYLY,
        WEEKLY,
        MONTHLY,
    }

    public enum Channel {
        POSTAL,
        MAIL,
        API,
        FTP,
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private Channel channel;


    @Enumerated(EnumType.STRING)
    private Survey.TargetGender targetGender;

    @Enumerated(EnumType.STRING)
    private Survey.Currency targetIncomeCurrency;

    private Integer targetAgeL;

    private Integer targetAgeH;

    private Integer targetIncomeL;

    private Integer targetIncomeH;

    Subscription() {
    }

    public Subscription(Customer customer, Frequency frequency, Channel channel) {
        this.customer = customer;
        this.frequency = frequency;
        this.channel = channel;
    }


    public Survey.TargetGender getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(Survey.TargetGender targetGender) {
        this.targetGender = targetGender;
    }

    public Survey.Currency getTargetIncomeCurrency() {
        return targetIncomeCurrency;
    }

    public void setTargetIncomeCurrency(Survey.Currency targetIncomeCurrency) {
        this.targetIncomeCurrency = targetIncomeCurrency;
    }

    public Integer getTargetAgeL() {
        return targetAgeL;
    }

    public void setTargetAgeL(Integer targetAgeL) {
        this.targetAgeL = targetAgeL;
    }

    public Integer getTargetAgeH() {
        return targetAgeH;
    }

    public void setTargetAgeH(Integer targetAgeH) {
        this.targetAgeH = targetAgeH;
    }

    public Integer getTargetIncomeL() {
        return targetIncomeL;
    }

    public void setTargetIncomeL(Integer targetIncomeL) {
        this.targetIncomeL = targetIncomeL;
    }

    public Integer getTargetIncomeH() {
        return targetIncomeH;
    }

    public void setTargetIncomeH(Integer targetIncomeH) {
        this.targetIncomeH = targetIncomeH;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }



}
