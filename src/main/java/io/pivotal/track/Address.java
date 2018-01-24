
package io.pivotal.track;

import static lombok.AccessLevel.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="address")
@Indexes({
    @Index(name="by_streetAddress", columnNames= { "streetAddress" }),
    @Index(name="by_city", columnNames= { "city" }),
    @Index(name="by_state", columnNames= { "state" }),
    @Index(name="by_zip", columnNames= { "zip" })
})
@NoArgsConstructor(access=PRIVATE)
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "streetAddress",
    "city",
    "state",
    "zip",
    "country"
})
public class Address implements Serializable {
    
    private final static long serialVersionUID = 6925266306494607412L;

    @Id
    @Column(name="id")
    @GeneratedValue
    @Getter
    @Setter(PACKAGE)
    @JsonProperty(value="id", access=JsonProperty.Access.READ_ONLY)
    private Long id;
    
    @Column(name="streetAddress",length=250)
    @Getter
    @Setter
    @JsonProperty("streetAddress")
    private String streetAddress;
    
    @Column(name="city",length=50)
    @Getter
    @Setter
    @JsonProperty("city")
    private String city;
    
    @Column(name="state", length=50)
    @Getter
    @Setter
    @JsonProperty("state")
    private String state;
    
    @Column(name="zip", length=12)
    @Getter
    @Setter
    @JsonProperty("zip")
    private String zip;
    
    @Column(name="country", length=75)
    @Getter
    @Setter
    @JsonProperty("country")
    private String country;
    
    public Address withStreetAddress(String streetAddress) {
        setStreetAddress(streetAddress);
        return this;
    }
    
    public Address withCity(String city) {
        setCity(city);
        return this;
    }
    
    public Address withState(String state) {
        setState(state);
        return this;
    }
    
    public Address withZip(String zip) {
        setZip(zip);
        return this;
    }

    public Address withCountry(String country) {
        setCountry(country);
        return this;
    }
    
    public static Address create() {
        return new Address();
    }
}
