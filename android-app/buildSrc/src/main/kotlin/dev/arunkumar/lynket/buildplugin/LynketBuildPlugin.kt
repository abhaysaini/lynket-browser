/*
 *
 *  Lynket
 *
 *  Copyright (C) 2022 Arunkumar
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.arunkumar.lynket.buildplugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class LynketBuildPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    project.publishLocalProperties()
    project.ensureBuildVariables()
  }

  private fun Project.putPropertyIfNotExists(key: String, value: Any) {
    if (!hasProperty(key)) {
      extensions.add(key, value)
    }
  }

  private fun Project.publishLocalProperties() {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
      val localProperties = Properties().apply { load(localPropertiesFile.bufferedReader()) }
      allprojects { project ->
        localProperties.forEach { key, value ->
          project.putPropertyIfNotExists(key.toString(), value)
        }
      }
    }
  }

  /**
   * In case project is not setup with necessary variables, inject empty strings to let debug
   * build run successfully
   */
  private fun Project.ensureBuildVariables() {
    allprojects { project ->
      project.putPropertyIfNotExists("PLAY_LICENSE_KEY", "")
    }
  }
}
