
package io.pivotal.track;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="shipment_tracking_info")
@Index(name="by_tracking_number", columnNames= { "tracking_number" })
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "carrier",
    "tracking_number",
    "address_from",
    "address_to",
    "eta",
    "original_eta",
    "service_level",
    "metadata",
    "tracking_status",
    "messages"
})
public class ShipmentTrackingInfo implements Serializable {
    private final static long serialVersionUID = -8159346226119323489L;

    @Column(name="messages")
    @ElementCollection
    @JsonProperty("messages")
    private Set<String> messages = new HashSet<>();
    
    @Column(name="carrier")
    @JsonProperty("carrier")
    private String carrier;
    
    @Id
    @Column(name="tracking_number", length=20)
    @JsonProperty("tracking_number")
    private String trackingNumber;
    
    @ManyToOne
    @JsonProperty("address_from")
    private Address addressFrom;
    
    @ManyToOne
    @JsonProperty("address_to")
    private Address addressTo;
    
    @Column(name="eta")
    @JsonProperty("eta")
    private LocalDateTime eta;
    
    @Column(name="original_eta")
    @JsonProperty("original_eta")
    private LocalDateTime originalEta;
    
    @JsonProperty("service_level")
    private ServiceLevel serviceLevel;
    
    @Column(name="metadata")
    @JsonProperty("metadata")
    private String metadata;
    
    @ManyToOne
    @JsonProperty("tracking_status")
    private TrackingStatus trackingStatus;

	public Set<String> getMessages() {
		return messages;
	}

	public void setMessages(Set<String> messages) {
		this.messages = messages;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Address getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(Address addressFrom) {
		this.addressFrom = addressFrom;
	}

	public Address getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(Address addressTo) {
		this.addressTo = addressTo;
	}

	public LocalDateTime getEta() {
		return eta;
	}

	public void setEta(LocalDateTime eta) {
		this.eta = eta;
	}

	public LocalDateTime getOriginalEta() {
		return originalEta;
	}

	public void setOriginalEta(LocalDateTime originalEta) {
		this.originalEta = originalEta;
	}

	public ServiceLevel getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(ServiceLevel serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public TrackingStatus getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(TrackingStatus trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

}
