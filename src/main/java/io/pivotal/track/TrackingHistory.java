
package io.pivotal.track;

import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tracking_history")
@Access(AccessType.FIELD)
@NoArgsConstructor(access=PRIVATE)
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
    
    @Column(name="status")
    @Getter
    @Setter
    @JsonProperty("status")
    private StatusEvent status;
    
    @Column(name="status_details", length=500)
    @Getter
    @Setter
    @JsonProperty("status_details")
    private String statusDetails;
    
    @Column(name="status_date")
    @Getter
    @Setter
    @JsonProperty("status_date")
    private LocalDateTime statusDate;
    
    @ManyToOne
    @Getter
    @Setter
    @JsonProperty("location")
    private Address location;

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
