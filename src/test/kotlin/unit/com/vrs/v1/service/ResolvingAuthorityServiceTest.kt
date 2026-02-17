package com.vrs.v1.service

import com.vrs.v1.entity.ResolvingAuthority
import com.vrs.v1.repository.ResolvingAuthorityRepository
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
class ResolvingAuthorityServiceTest {

    @InjectMock
    lateinit var repository: ResolvingAuthorityRepository

    @Inject
    lateinit var service: ResolvingAuthorityService

    @Test
    @RunOnVertxContext
    fun testListAll(asserter: UniAsserter) {
        val items = listOf(ResolvingAuthority(), ResolvingAuthority())
        whenever(repository.listAll()).thenReturn(Uni.createFrom().item(items))

        asserter.assertThat({ service.listAll() }) { result ->
            assertEquals(2, result.size)
        }
    }
    @Test
    @RunOnVertxContext
    fun testFindById(asserter: UniAsserter) {
        val item = ResolvingAuthority()
        whenever(repository.findById(1L)).thenReturn(Uni.createFrom().item(item))

        asserter.assertThat({ service.findById(1L) }) { result ->
            assertNotNull(result)
        }
    }

    @Test
    @RunOnVertxContext
    fun testAdd(asserter: UniAsserter) {
        val item = ResolvingAuthority()
        whenever(repository.persist(any<ResolvingAuthority>())).thenReturn(Uni.createFrom().item(item))

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
