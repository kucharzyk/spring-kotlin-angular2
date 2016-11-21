package com.shardis.user

import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by Tomasz Kucharzyk
 */

@Transactional
@Service
open class UserService(open val userRepository: UserRepository) {

    open fun updateUsers(): MutableIterable<User> {
        val users = userRepository.findAll()
        for (user in users) {
            user.username = "Simple User v.${(user.version ?: 0) + 1}"
        }
        return users
    }

    open fun findAll(): MutableIterable<User> = userRepository.findAll()

    open fun save(user: User): User = userRepository.save(user)

}
