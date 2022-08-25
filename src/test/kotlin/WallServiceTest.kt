import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post = Post(ownerId = 1, text = "text")
        val result = WallService.add(post)
        assertNotEquals(0, result.id)
    }

    @Test
    fun updateIdExists() {
        val post = Post(ownerId = 1, text = "text")

        val updatePost = Post(ownerId = 1, text = "update", id = WallService.add(post).id)
        val result = WallService.update(updatePost)
        assertTrue(result)
    }

    @Test
    fun updateIdNotExists() {
        val post = Post(ownerId = 1, text = "text", id = Int.MAX_VALUE)
        val result = WallService.update(post)
        assertFalse(result)
    }
}