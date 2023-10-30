
package io.pivotal.track;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="tracking_history")
@Access(AccessType.FIELD)
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "object_created",
    "status",
    "status_details",
    "status_date",
    "location"
})
public class TrackingHistory extends ImmutableEntity implements Serializable {

    private final static long serialVersionUID = 4087756947407007397L;

    private TrackingHistory() {}

    @Column(name="status")
    @JsonProperty("status")
    private StatusEvent status;

    @Column(name="status_details", length=500)
    @JsonProperty("status_details")
    private String statusDetails;

    @Column(name="status_date")
    @JsonProperty("status_date")
    private LocalDateTime statusDate;

    @ManyToOne
    @JsonProperty("location")
    private Address location;

    public StatusEvent getStatus() {
		return status;
	}

	public void setStatus(StatusEvent status) {
		this.status = status;
	}

	public String getStatusDetails() {
		return statusDetails;
	}

	public void setStatusDetails(String statusDetails) {
		this.statusDetails = statusDetails;
	}

	public LocalDateTime getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(LocalDateTime statusDate) {
		this.statusDate = statusDate;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	@JsonIgnore
    @Override
    public boolean isNew() {
        return true;
    }

    public TrackingHistory withStatus(StatusEvent status) {
        setStatus(status);
        return this;
    }

    public TrackingHistory withStatusDetails(String statusDetails) {
        setStatusDetails(statusDetails);
        return this;
    }

    public TrackingHistory withLocation(Address location) {
        setLocation(location);
        return this;
    }

    public static TrackingHistory create() {
        return new TrackingHistory();
    }
}
