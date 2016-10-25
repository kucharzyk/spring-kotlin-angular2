package com.shardis.user

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Tomasz Kucharzyk
 */

@RestController()
@RequestMapping(path = arrayOf("/user/"))
class UserController(val userService: UserService) {

    @RequestMapping(path = arrayOf("/all"), method = arrayOf(RequestMethod.GET))
    fun getAllUsers() = userService.findAll()

    @RequestMapping(path = arrayOf("/add"), method = arrayOf(RequestMethod.GET))
    fun addRandomUser(): User {
        val user = User("Simple User")
        userService.save(user)
        return user
    }

    @RequestMapping(path = arrayOf("/update"), method = arrayOf(RequestMethod.GET))
    fun updateUsers(): MutableIterable<User>? {
        return userService.updateUsers()
    }

}
