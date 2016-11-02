package com.shardis.security

import com.shardis.ShardisProperties
import com.shardis.security.jwt.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

/**
 * Created by Tomasz Kucharzyk
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
open class WebSecurityConfig(
    private val unauthorizedHandler: JwtAuthenticationEntryPoint,
    private val shardisProperties: ShardisProperties,
    private val jwtTokenAuthService: JwtTokenAuthService
) : WebSecurityConfigurerAdapter(false) {


    @Bean
    open fun jwtAuthenticationProvider(): AuthenticationProvider {
        return JwtAuthenticationProvider(jwtTokenAuthService)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(Arrays.asList(jwtAuthenticationProvider()))
    }

    @Bean
    open fun authenticationTokenFilterBean(): JwtAuthenticationTokenFilter {
        val authenticationTokenFilter = JwtAuthenticationTokenFilter(shardisProperties)
        authenticationTokenFilter.setAuthenticationManager(authenticationManager())
        authenticationTokenFilter.setAuthenticationSuccessHandler(JwtAuthenticationSuccessHandler())
        return authenticationTokenFilter
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity

            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/test").anonymous()
            .antMatchers("/api/protected/**").hasRole("USER")
            .antMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .csrf().disable()
            .anonymous()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .headers().frameOptions().disable()
            .and()
            .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)


    }
}
