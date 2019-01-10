package io.pivotal.track;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class ImmutableEntity implements Persistable<UUID> {

    @Id
    @Column(columnDefinition="BINARY(16) NOT NULL")
    @GeneratedValue(generator="uuid-sequence")
    @JsonProperty(value="id", access=JsonProperty.Access.READ_ONLY)
    protected UUID id;

    @Basic
    @CreatedDate
    @JsonProperty("object_created")
    @Column(
        name = "object_created", 
        nullable = false, 
        insertable = true, 
        updatable = false, 
        columnDefinition = "TIMESTAMP NOT NULL"
    )
    private LocalDateTime objectCreated;

    protected ImmutableEntity() {}

    public ImmutableEntity(UUID id) {
        this.id = id;
    }

	public UUID getId() {
		return id;
	}

	void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getObjectCreated() {
		return objectCreated;
	}

	void setObjectCreated(LocalDateTime objectCreated) {
		this.objectCreated = objectCreated;
	}

}