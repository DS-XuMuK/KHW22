data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int = ownerId,
    val createdBy: Int = ownerId,
    val date: Int = 0,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = true,
    val postType: String = "post",
    val likes: Likes = Likes(0)
) {
    class Likes(val count: Int, userLikes: Boolean = false)
}

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 1

    fun clear() {
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        posts += post.copy(id = id)
        id += 1
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, value) in posts.withIndex()) {
            if (value.id == post.id) {
                posts[index] = post.copy(ownerId = value.ownerId, date = value.date)
                return true
            }
        }
        return false
    }
}

fun main() {
    val firstPost = Post(ownerId = 44, date = 1661345460, text = "First post!")
    val secondPost = Post(ownerId = 44, date = 1661367175, text = "Second post!")
    val updatePost = Post(id = 2, ownerId = 44, date = 1661374009, text = "Update")

    println(WallService.add(firstPost))
    println(WallService.add(secondPost))
    WallService.update(updatePost)
}