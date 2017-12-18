
package io.pivotal.track;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tracking_status")
@Access(AccessType.FIELD)
@NoArgsConstructor(access=PRIVATE)
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

    @Basic
    @LastModifiedDate
    @Getter
    @Setter(PACKAGE)
    @JsonProperty("object_updated")
    @Column(
        name = "object_updated", 
        nullable = false, 
        insertable = true, 
        updatable = true, 
        columnDefinition = "TIMESTAMP NOT NULL"
    )
    private LocalDateTime objectUpdated;
    
    @Getter
    @Setter
    @Column(name="status")
    @JsonProperty("status")
    private StatusEvent status;
    
    @Column(name="status_details", length=150)
    @Getter
    @Setter
    @JsonProperty("status_details")
    private String statusDetails;
    
    @Getter
    @Setter
    @Column(name="status_date")
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
