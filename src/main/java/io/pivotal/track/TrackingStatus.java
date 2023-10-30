
package io.pivotal.track;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="tracking_status")
@Access(AccessType.FIELD)
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "object_created",
    "object_updated",
    "status",
    "status_details",
    "status_date",
    "location"
})
public class TrackingStatus extends ImmutableEntity implements Serializable {

    private final static long serialVersionUID = -4968320067792377712L;

    private TrackingStatus() {}

    @Basic
    @LastModifiedDate
    @JsonProperty("object_updated")
    @Column(
        name = "object_updated",
        nullable = false,
        insertable = true,
        updatable = true,
        columnDefinition = "TIMESTAMP NOT NULL"
    )
    private LocalDateTime objectUpdated;

    @Column(name="status")
    @JsonProperty("status")
    private StatusEvent status;

    @Column(name="status_details", length=150)
    @JsonProperty("status_details")
    private String statusDetails;

    @Column(name="status_date")
    @JsonProperty("status_date")
    private LocalDateTime statusDate;

    @ManyToOne
    @JsonProperty("location")
    private Address location;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return true;
    }

    public LocalDateTime getObjectUpdated() {
		return objectUpdated;
	}

	void setObjectUpdated(LocalDateTime objectUpdated) {
		this.objectUpdated = objectUpdated;
	}

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

	public TrackingStatus withStatus(StatusEvent status) {
        setStatus(status);
        return this;
    }

    public TrackingStatus withStatusDetails(String statusDetails) {
        setStatusDetails(statusDetails);
        return this;
    }

    public TrackingStatus withLocation(Address location) {
        setLocation(location);
        return this;
    }

    public static TrackingStatus create() {
        return new TrackingStatus();
    }

}
