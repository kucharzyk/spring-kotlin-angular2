package com.shardis.init

import com.shardis.role.Role
import com.shardis.user.RoleRepository
import com.shardis.user.User
import com.shardis.user.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.transaction.Transactional

/**
 * Created by tomasz on 07.11.16.
 */
@Transactional
@Service
open class DbInitializer(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostConstruct
    open fun initializeDB() {

        log.info("DB initialization")

        if (roleRepository.findAll().none()) {

            log.info("Creating role user")
            var roleUser = Role("ROLE_USER")
            roleRepository.save(roleUser)

            log.info("Creating role admin")
            var roleAdmin = Role("ROLE_ADMIN")
            roleRepository.save(roleAdmin)

        }

        if (userRepository.findAll().none()) {

            log.info("Crating user admin:xxxxxx")
            var roleUser = roleRepository.findOneByName("ROLE_USER")
            var roleAdmin = roleRepository.findOneByName("ROLE_ADMIN")

            var user: User = User("admin")
            user.firstName = "Admin"
            user.lastName = "Admin"
            user.email = "admin@admin.pl"
            user.activated = true
            user.password = passwordEncoder.encode("xxxxxx")
            user.roles.add(roleUser)
            user.roles.add(roleAdmin)

            userRepository.save(user)
        }

        log.info("DB initialization success")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(DbInitializer::class.java)
    }
}
