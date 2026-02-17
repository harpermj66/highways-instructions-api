package com.vrs.v1.unit.service

import com.vrs.v1.service.*

import com.vrs.v1.entity.User
import com.vrs.v1.repository.UserRepository
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import io.quarkus.test.vertx.RunOnVertxContext
import io.quarkus.test.vertx.UniAsserter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class UserServiceTest {

    @InjectMock
    lateinit var repository: UserRepository

    @Inject
    lateinit var service: UserService

    @Test
    @RunOnVertxContext
    fun testListAll(asserter: UniAsserter) {
        val items = listOf(User(), User())
        whenever(repository.listAll()).thenReturn(Uni.createFrom().item(items))

        asserter.assertThat({ service.listAll() }) { result ->
            assertEquals(2, result.size)
        }
    }
    @Test
    @RunOnVertxContext
    fun testFindById(asserter: UniAsserter) {
        val item = User()
        whenever(repository.findById(1L)).thenReturn(Uni.createFrom().item(item))

        asserter.assertThat({ service.findById(1L) }) { result ->
            assertNotNull(result)
        }
    }

    @Test
    @RunOnVertxContext
    fun testAdd(asserter: UniAsserter) {
        val item = User()
        whenever(repository.persist(any<User>())).thenReturn(Uni.createFrom().item(item))

        asserter.assertThat({ service.add(item) }) { result ->
            assertNotNull(result)
        }
    }

    @Test
    @RunOnVertxContext
    fun testDeleteById(asserter: UniAsserter) {
        whenever(repository.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        asserter.assertThat({ service.deleteById(1L) }) { result ->
            assertTrue(result)
        }
    }
}
