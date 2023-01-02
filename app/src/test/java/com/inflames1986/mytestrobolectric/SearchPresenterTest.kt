package com.inflames1986.mytestrobolectric

import com.inflames1986.mytestrobolectric.presenter.search.SearchPresenter
import com.inflames1986.mytestrobolectric.repository.GitHubRepository
import com.inflames1986.mytestrobolectric.view.search.ViewSearchContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.text.Typography.times


class SearchPresenterTest {
    @Mock
    private lateinit var repository: GitHubRepository

    @Mock
    private lateinit var viewContract: ViewSearchContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    //Проверим, что при вызове handleGitHubError запускается displayLoading и
    // displayError, если был вызван presenter.onAttach
    @Test
    fun detailsPresenter_handleGitHubErrorWorking_ReturnsTrue() {
        val presenter = SearchPresenter(repository)
        presenter.onAttach(viewContract)
        presenter.handleGitHubError()
        verify(viewContract, times(1)).displayLoading(anyBoolean())
        verify(viewContract, times(1)).displayError()
        presenter.onDetach()
    }

    //Проверим, что при вызове handleGitHubError не запускается displayLoading и
    // displayError, если handleGitHubError вызван после presenter.onAttach
    @Test
    fun detailsPresenter_handleGitHubErrorNotWorkingBeforeOnAttach_ReturnsTrue() {
        val presenter = SearchPresenter(repository)
        presenter.handleGitHubError()
        presenter.onAttach(viewContract)
        verify(viewContract, times(0)).displayLoading(anyBoolean())
        verify(viewContract, times(0)).displayError()
        presenter.onDetach()
    }

    //Проверим, что после presenter.onDetach метод handleGitHubError тоже не будет отрабатывать
    @Test
    fun detailsPresenter_handleGitHubErrorNotWorkingBeforeOnDetach_ReturnsTrue() {
        val presenter = SearchPresenter(repository)
        presenter.onAttach(viewContract)
        presenter.onDetach()
        presenter.handleGitHubError()
        verify(viewContract, times(0)).displayLoading(anyBoolean())
        verify(viewContract, times(0)).displayError()
    }
}