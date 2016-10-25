package com.shardis.jpa.support

import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.boot.spi.MetadataImplementor
import org.hibernate.cfg.AvailableSettings
import org.hibernate.tool.hbm2ddl.SchemaExport
import org.hibernate.tool.hbm2ddl.Target
import org.reflections.Reflections
import java.io.File
import javax.persistence.Entity
import javax.persistence.MappedSuperclass

/**
 * Created by Tomasz Kucharzyk
 */
class SchemaExporter(val hibernateDialect: String, entityPackage: String) {


    val metadataSources: MetadataSources

    init {
        metadataSources = MetadataSources(
            StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.DIALECT, hibernateDialect)
                .build()
        )

        val reflections = Reflections(entityPackage)
        for (cl in reflections.getTypesAnnotatedWith(MappedSuperclass::class.java)) {
            metadataSources.addAnnotatedClass(cl)
        }
        for (cl in reflections.getTypesAnnotatedWith(Entity::class.java)) {
            metadataSources.addAnnotatedClass(cl)
        }
    }

    fun export(exportFile: File) {

        val exporter = SchemaExport(
            metadataSources.buildMetadata() as MetadataImplementor,
            true
        )

        exporter
            .setDelimiter(";")
            .setFormat(true)
            .setOutputFile(exportFile.canonicalPath)
            .create(Target.NONE)

    }


}
