package com.jnart.yoyo

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

/**
 * Action triggered by the icon in the IntelliJ main header toolbar or keyboard shortcut.
 * Opens a new terminal tab and executes the 'yoyo' command (YoYo Platform CLI).
 * Supports Dynamic Plugin loading without IDE restart.
 */
class YoYoAction : DumbAwareAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return

        try {
            val terminalManager = TerminalToolWindowManager.getInstance(project)
            val workingDir = project.basePath

            // Create and show a new terminal widget tab named "YoYo Platform"
            val widget = terminalManager.createShellWidget(workingDir, "YoYo Platform", true, true)

            // Send the 'yoyo' command for execution in the terminal
            widget.sendCommandToExecute("yoyo")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun update(e: AnActionEvent) {
        val hasProject = e.project != null
        e.presentation.isVisible = true
        e.presentation.isEnabled = hasProject
    }
}
