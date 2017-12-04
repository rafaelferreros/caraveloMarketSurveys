package caravelo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Survey {

    public enum TargetGender{
        MALE, FEMALE, ALL,
    }

    public enum Currency{
        EUR, USD, GBP, ALL,
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Provider provider;

    private String description;

    private TargetGender targetGender;

    private Integer targetAgeL;

    private Integer targetAgeH;

    private Currency targetIncomeCurrency;

    private Integer targetIncomeL;

    private Integer targetIncomeH;



    public Survey(Provider provider, String description) {
        this.description = description;
        this.provider = provider;
    }

    Survey(){

    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TargetGender getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(TargetGender targetGender) {
        this.targetGender = targetGender;
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

    public Currency getTargetIncomeCurrency() {
        return targetIncomeCurrency;
    }

    public void setTargetIncomeCurrency(Currency targetIncomeCurrency) {
        this.targetIncomeCurrency = targetIncomeCurrency;
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


}
