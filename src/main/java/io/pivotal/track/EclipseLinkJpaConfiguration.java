package io.pivotal.track;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@EnableLoadTimeWeaving
@EntityScan(basePackageClasses={TrackShipmentsMvpApplication.class, Jsr310Converters.class})
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration implements EnvironmentAware {

    private Environment environment;

    protected EclipseLinkJpaConfiguration(DataSource dataSource, JpaProperties properties,
            ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        final Map<String, Object> baseProperties = new HashMap<>();
        baseProperties.put(PersistenceUnitProperties.TABLE_CREATION_SUFFIX, ";");
        baseProperties.put(PersistenceUnitProperties.SESSION_CUSTOMIZER, UUIDSequence.class.getName());
        baseProperties.put(PersistenceUnitProperties.WEAVING, "true");
	    baseProperties.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
        if (environment.acceptsProfiles(Profiles.of("debug"))) {
            baseProperties.put(PersistenceUnitProperties.LOGGING_LEVEL, "ALL");
        }
        return Map.copyOf(baseProperties);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
