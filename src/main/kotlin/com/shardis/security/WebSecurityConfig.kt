package com.shardis.security

import com.shardis.ShardisProperties
import com.shardis.security.jwt.*
import org.springframework.beans.factory.BeanInitializationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AnonymousAuthenticationProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

/**
 * Created by Tomasz Kucharzyk
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
open class WebSecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val shardisProperties: ShardisProperties,
    private val jwtTokenAuthService: JwtTokenAuthService,
    private val shardisUserDetailsService: ShardisUserDetailsService,
    private val jwtAuthenticationSuccessHandler: JwtAuthenticationSuccessHandler,
    private val jwtAuthenticationFailureHandler: JwtAuthenticationFailureHandler,
    private val jwtAuthenticationLogoutHandler: JwtAuthenticationLogoutHandler
) : WebSecurityConfigurerAdapter(false) {


    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return Pbkdf2PasswordEncoder(shardisProperties.security.passwordSecret)
    }

    @Bean
    open fun jwtAuthenticationProvider(): AuthenticationProvider {
        return JwtAuthenticationProvider(jwtTokenAuthService)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(Arrays.asList(
            daoAuthenticationProvider(),
            jwtAuthenticationProvider()
        ))
    }

    @Bean
    open fun authenticationTokenFilterBean(): JwtAuthenticationTokenFilter {
        val authenticationTokenFilter = JwtAuthenticationTokenFilter(shardisProperties)
        authenticationTokenFilter.setAuthenticationManager(authenticationManager())
        authenticationTokenFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler)
        return authenticationTokenFilter
    }

    @Bean
    open fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(shardisUserDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity

            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin()
            .loginProcessingUrl("/api/authentication")
            .successHandler(jwtAuthenticationSuccessHandler)
            .failureHandler(jwtAuthenticationFailureHandler)
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(jwtAuthenticationLogoutHandler)
            .permitAll()
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
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .headers().frameOptions().disable()
            .and()
            .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)

    }


}
