package br.com.wellingtoncosta.mvvm

import br.com.wellingtoncosta.mvvm.repository.ColorRepositoryTesst
import br.com.wellingtoncosta.mvvm.repository.UserRepositoryTest
import br.com.wellingtoncosta.mvvm.viewmodel.ListColorsViewModelTest
import br.com.wellingtoncosta.mvvm.viewmodel.ListUsersViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

/**
 * @author wellingtoncosta on 05/02/18.
 */
@RunWith(Suite::class)
@SuiteClasses(*[
    (ColorRepositoryTesst::class),
    (UserRepositoryTest::class),
    (ListColorsViewModelTest::class),
    (ListUsersViewModelTest::class)
])
class TestSuite