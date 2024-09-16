package com.cicdapp.monitorning;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;

@FunctionalInterface
public interface HealthIndicator extends HealthContributor{
default Health getHealth(boolean includeDeatils)
{
	Health health=this.health();
	return includeDeatils ? health : Health.status(health.getStatus())
            .withDetail("status", health.getStatus().getCode())
            .build();
}
Health health();

}
