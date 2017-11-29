package caravelo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Survey {

    @JsonIgnore
    @ManyToOne
    private Provider provider;

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    public Survey(Provider provider, String description) {
        this.description = description;
        this.provider = provider;
    }

    Survey(){}

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




}
