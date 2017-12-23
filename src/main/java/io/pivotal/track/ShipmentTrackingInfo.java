
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

import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    @Column(name="messages")
    @ElementCollection
    @JsonProperty("messages")
    private Set<String> messages = new HashSet<>();
    
    @Getter
    @Setter
    @Column(name="carrier")
    @JsonProperty("carrier")
    private String carrier;
    
    @Id
    @Getter
    @Setter
    @Column(name="tracking_number", length=20)
    @JsonProperty("tracking_number")
    private String trackingNumber;
    
    @Getter
    @Setter
    @ManyToOne
    @JsonProperty("address_from")
    private Address addressFrom;
    
    @Getter
    @Setter
    @ManyToOne
    @JsonProperty("address_to")
    private Address addressTo;
    
    @Getter
    @Setter
    @Column(name="eta")
    @JsonProperty("eta")
    private LocalDateTime eta;
    
    @Getter
    @Setter
    @Column(name="original_eta")
    @JsonProperty("original_eta")
    private LocalDateTime originalEta;
    
    @Getter
    @Setter
    @JsonProperty("service_level")
    private ServiceLevel serviceLevel;
    
    @Getter
    @Setter
    @Column(name="metadata")
    @JsonProperty("metadata")
    private String metadata;
    
    @Getter
    @Setter
    @ManyToOne
    @JsonProperty("tracking_status")
    private TrackingStatus trackingStatus;

}
