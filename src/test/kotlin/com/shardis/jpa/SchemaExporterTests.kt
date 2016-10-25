package com.shardis.jpa

import com.shardis.jpa.support.SchemaExporter
import org.hibernate.dialect.SQLServerDialect
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File

/**
 * Created by Tomasz Kucharzyk
 */

@RunWith(SpringRunner::class)
@SpringBootTest
class SchemaExporterTests {

    @Test
    fun generateDDL() {
        val exporter = SchemaExporter(SQLServerDialect::class.java.canonicalName, "com.shardis")
        exporter.export(File("schema.sql"))
        Assert.assertTrue(true)
    }
}
