
package io.pivotal.track;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="address")
@Indexes({
    @Index(name="by_streetAddress", columnNames= { "streetAddress" }),
    @Index(name="by_city", columnNames= { "city" }),
    @Index(name="by_state", columnNames= { "state" }),
    @Index(name="by_zip", columnNames= { "zip" })
})
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

    private Address() {}

    @Id
    @Column(name="id")
    @GeneratedValue
    @JsonProperty(value="id", access=JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name="streetAddress",length=250)
    @JsonProperty("streetAddress")
    private String streetAddress;

    @Column(name="city",length=50)
    @JsonProperty("city")
    private String city;

    @Column(name="state", length=50)
    @JsonProperty("state")
    private String state;

    @Column(name="zip", length=12)
    @JsonProperty("zip")
    private String zip;

    @Column(name="country", length=75)
    @JsonProperty("country")
    private String country;

    public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

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
