
package io.pivotal.track;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="service_level")
@Index(name="by_token", columnNames = { "token" })
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "token",
    "name"
})
public class ServiceLevel implements Serializable {
    
    private final static long serialVersionUID = -4932735742032198322L;

    private ServiceLevel() {}
    
    @Id
    @Column(name="token")
    @JsonProperty("token")
    private String token;
    
    @Column(name="name")
    @JsonProperty("name")
    private String name;
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
