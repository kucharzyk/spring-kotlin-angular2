package com.shardis.auth

import com.shardis.user.User
import com.shardis.user.UserRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Tomasz Kucharzyk
 */

@RestController()
@RequestMapping(path = arrayOf("/user/"))
class UserController(val userRepository: UserRepository) {

    @RequestMapping(path = arrayOf("/all"), method = arrayOf(RequestMethod.GET))
    fun getAllUsers() = userRepository.findAll()

    @RequestMapping(path = arrayOf("/add"), method = arrayOf(RequestMethod.GET))
    fun addRandomUser(): User {
        val user = User("Simple User", "ROLE_USER")
        userRepository.save(user)
        return user
    }

}
