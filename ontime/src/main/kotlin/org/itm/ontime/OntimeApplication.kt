package org.itm.ontime

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OntimeApplication

fun main(args: Array<String>) {
    runApplication<OntimeApplication>(*args)
}
