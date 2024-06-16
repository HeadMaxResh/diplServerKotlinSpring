package com.diplback.diplserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiplServerApplication

fun main(args: Array<String>) {
	runApplication<DiplServerApplication>(*args)
}
