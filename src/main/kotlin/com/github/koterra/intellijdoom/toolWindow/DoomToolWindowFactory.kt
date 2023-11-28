package com.github.koterra.intellijdoom.toolWindow

import com.github.koterra.intellijdoom.UrlsBundle
import com.github.koterra.intellijdoom.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout


class DoomToolWindowFactory : ToolWindowFactory {

    init {
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val doomToolWindow = DoomToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(doomToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class DoomToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()
        private val toolWindow: ToolWindow = toolWindow;

        fun getContent() = JBPanel<JBPanel<*>>().apply {

            var selection: String = UrlsBundle.get("LANDING_PAGE");

            val panel = JBPanel<JBPanel<*>>()
            panel.layout = BorderLayout()

            // Create a JBCefBrowser
            val cefBrowser = JBCefBrowser()

            // Load a URL
            cefBrowser.loadURL(UrlsBundle.getUrl(selection))

            // Add the browser component to the panel
            panel.add(cefBrowser.component, BorderLayout.CENTER)

            // Add a button to demonstrate interaction
            val combo = ComboBox<String>();
            combo.isEditable = false;
            combo.addItem(UrlsBundle.get("LANDING_PAGE"));
            combo.addItem(UrlsBundle.get("DOOM"));
            combo.addItem(UrlsBundle.get("MORTAL_KOMBAT"));
            combo.addItem(UrlsBundle.get("GTA"));
            combo.addItem(UrlsBundle.get("NEED_FOR_SPEED"));
            combo.addItem(UrlsBundle.get("PRINCE_OF_PERSIA"));
            combo.addItem(UrlsBundle.get("SIM_CITY"));


            combo.addActionListener {
                // Handle the event when an item is selected
                selection = combo.selectedItem as String;
                cefBrowser.loadURL(UrlsBundle.getUrl(selection))
            }
            panel.add(combo, BorderLayout.SOUTH)

            return panel
        }

    }
}