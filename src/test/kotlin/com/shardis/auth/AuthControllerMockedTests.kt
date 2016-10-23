package com.shardis.auth

import com.shardis.ShardisProperties
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


/**
 * Created by Tomasz Kucharzyk
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class AuthControllerMockedTests {

    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build()
    }


    @Test
    @WithMockUser(username = "admin", roles = arrayOf("USER"))
    fun mockUser() {

        val principalResult = this.mockMvc
            .perform(MockMvcRequestBuilders.get("/auth/principal"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        Assert.assertEquals("admin", principalResult.response.contentAsString)
    }
}
