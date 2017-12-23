
package io.pivotal.track;

import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="service_level")
@Index(name="by_token", columnNames = { "token" })
@NoArgsConstructor(access=PRIVATE)
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "token",
    "name"
})
public class ServiceLevel implements Serializable {
    
    private final static long serialVersionUID = -4932735742032198322L;

    @Id
    @Column(name="token")
    @Getter
    @Setter
    @JsonProperty("token")
    private String token;
    
    @Getter
    @Setter
    @Column(name="name")
    @JsonProperty("name")
    private String name;
    
    public ServiceLevel withToken(String token) {
        setToken(token);
        return this;
    }
    
    public ServiceLevel withName(String name) {
        setName(name);
        return this;
    }
    
    public static ServiceLevel create() {
        return new ServiceLevel();
    }

}
