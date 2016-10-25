package com.shardis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * Created by Tomasz Kucharzyk
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
open class JpaConfig {

    @Bean(name = arrayOf("auditorProvider"))
    open fun auditorProvider(): AuditorAware<Long?> {
        return AuditorAware { null }
    }
}
