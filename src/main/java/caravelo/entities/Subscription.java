package caravelo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Link;

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

    private String description;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    Subscription() {
    }

    public Subscription(Customer customer, String description, Frequency frequency, Channel channel) {
        this.customer = customer;
        this.description = description;
        this.frequency = frequency;
        this.channel = channel;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
