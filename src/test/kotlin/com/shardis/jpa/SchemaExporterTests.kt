package com.shardis.jpa

import com.shardis.jpa.support.SchemaExporter
import org.hibernate.dialect.PostgreSQL94Dialect
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.junit4.SpringRunner
import java.io.File

/**
 * Created by Tomasz Kucharzyk
 */

@RunWith(SpringRunner::class)
@SpringBootTest()
class SchemaExporterTests {

    @Autowired lateinit var env: Environment

    @Test
    fun generateDDL() {
        val exporter = SchemaExporter(
            PostgreSQL94Dialect::class.java.canonicalName,
            "com.shardis",
            env.getProperty("spring.jpa.hibernate.naming.implicit-strategy"),
            env.getProperty("spring.jpa.hibernate.naming.physical-strategy")
        )
        exporter.export(File("schema.sql"))
        Assert.assertTrue(true)
    }
}
