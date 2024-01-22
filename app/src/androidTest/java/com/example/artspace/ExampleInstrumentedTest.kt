package com.example.artspace

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.artspace.ui.theme.ArtSpaceTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun exhibit_second_art() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }

        val expectArtTitle = "Segunda arte"
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithContentDescription(expectArtTitle).assertExists("No node with this content description was found.")
        composeTestRule.onNodeWithText(expectArtTitle).assertExists("No node with this text was found.")
    }
}