package com.inflames1986.mytestrobolectric

import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.inflames1986.mytestrobolectric.view.search.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {

    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun free() {
        scenario.close()
    }

    //Проверка наличия активити
    @Test
    fun mainActivity_AssertNotNull_ReturnsTrue() {
        scenario.onActivity { assertNotNull(it) }
    }

    //Проверим, что нужные вьюхи есть и видны, а progressBar есть, но скрыт
    @Test
    fun mainActivity_EditTextNotNull_ReturnsTrue() {
        scenario.onActivity {
            assertNotNull(it.searchEditText)
            assertEquals(View.VISIBLE, it.searchEditText.visibility)

            assertNotNull(it.toDetailsActivityButton)
            assertEquals(View.VISIBLE, it.toDetailsActivityButton.visibility)

            assertNotNull(it.recyclerView)
            assertEquals(View.VISIBLE, it.recyclerView.visibility)

            assertNotNull(it.progressBar)
            assertEquals(View.GONE, it.progressBar.visibility)
        }
    }

    //Проверим, что activity в состоянии RESUMED
    @Test
    fun mainActivity_CurrentStateIsResumed_ReturnsTrue() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    //Проверим, что если в поле поиска ничего не вводить и нажать на поиск, отобразиться Toast
    //с текстом "Enter search word"
    @Test
    fun mainActivity_EmptyQueryIsShowingToast_ReturnsTrue() {
        scenario.onActivity {
            with(it.searchEditText) {
                setText("", TextView.BufferType.EDITABLE)
                onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            }
            ShadowLooper.idleMainLooper()
            assertEquals(ShadowToast.getTextOfLatestToast(), EMPTY_QUERY_RESULT_TOAST)
        }
    }

    //Проверим, что при вводе слова Glide в адаптере будет 30 записей, т.к. по умолчанию
    // API.GITHUB.COM возвращает максимум 30 записей.
    @Test
    fun mainActivity_EqualFindResultCount_ReturnsTrue() {
        scenario.onActivity {
            with(it.searchEditText) {
                setText("Glide")
                onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            }
            Thread.sleep(DELAY)
            assertEquals(it.recyclerView.adapter?.itemCount, FIND_COUNT_ITEMS)
        }
    }

    companion object {
        private const val EMPTY_QUERY_RESULT_TOAST = "Enter search word"
        private const val DELAY = 2000L
        private const val FIND_COUNT_ITEMS = 30
    }
}