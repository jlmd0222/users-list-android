package jlmd.dev.android.pruebaceiba

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jlmd.dev.android.pruebaceiba.data.database.daos.UserDao
import jlmd.dev.android.pruebaceiba.data.database.entities.UserEntity
import jlmd.dev.android.pruebaceiba.data.database.gateway.OfflineStorageGateway
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var offlineStorageGateway: OfflineStorageGateway
    private val usersDao: UserDao = mockk()

    @Before
    fun setup() {
        offlineStorageGateway = OfflineStorageGateway(usersDao)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `getUsers() interactions are ok`() {
        runBlocking {
            val user = mockk<UserEntity>()

            coEvery {
                offlineStorageGateway.getUsers()
            } returns listOf(user)

            offlineStorageGateway.getUsers()
            coVerify(exactly = 1) { usersDao.getUsersLocal() }
        }
    }

    @Test
    fun `saveUsers() interactions are ok`() {
        runBlocking {
            val users = mockk<List<UserEntity>>()

            coEvery { offlineStorageGateway.saveUsers(users) } returns mockk()
            offlineStorageGateway.saveUsers(users)
            coVerify(exactly = 1) { usersDao.saveUsers(users) }
        }
    }

    @Test
    fun `getUserById() interactions are ok`() {
        runBlocking {
            coEvery { offlineStorageGateway.getUserById(1) } returns mockk()
            offlineStorageGateway.getUserById(1)
            coVerify(exactly = 1) { usersDao.getUserById(1) }
        }
    }
}