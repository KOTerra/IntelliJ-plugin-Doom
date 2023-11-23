package com.github.koterra.intellijdoom.toolWindow

import com.github.koterra.intellijdoom.MyBundle
import com.github.koterra.intellijdoom.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import org.cef.browser.CefBrowser
import java.awt.BorderLayout
import javax.swing.JButton


class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            //val label = JBLabel(MyBundle.message("randomLabel", "?"))

            //add(label)
            /*  add(JButton(MyBundle.message("shuffle")).apply {
                  addActionListener {
                      label.text = MyBundle.message("randomLabel", service.getRandomNumber())
                  }
              })*/


            val panel = JBPanel<JBPanel<*>>()
            panel.layout = BorderLayout()

            // Create a JBCefBrowser
            val cefApp = JBCefApp.getInstance()
            val cefBrowser = JBCefBrowser()

            // Load a URL
            cefBrowser.loadURL("https://dos.zone/player/?bundleUrl=https%3A%2F%2Fcdn.dos.zone%2Fcustom%2Fdos%2Fdoom.jsdos?anonymous=1")

            // Add the browser component to the panel
            panel.add(cefBrowser.component, BorderLayout.CENTER)

            // Add a button to demonstrate interaction
            val button = JButton("Click me")
            button.addActionListener {
                // Execute some JavaScript on the browser
              //  cefBrowser.executeJS("alert('Button clicked!');")
            }
            panel.add(button, BorderLayout.SOUTH)

            return panel
        }
    }
}
