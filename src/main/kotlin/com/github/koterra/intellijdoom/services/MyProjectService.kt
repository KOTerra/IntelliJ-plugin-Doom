package com.github.koterra.intellijdoom.services

import com.github.koterra.intellijdoom.MessagesBundle

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    init {
        thisLogger().info(MessagesBundle.message("projectService", project.name))
    }

    fun getRandomNumber() = (1..100).random()


}
