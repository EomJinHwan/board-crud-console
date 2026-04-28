package post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    // 게시글 객체 저장
    public void writePost(Post post) {
        post.setId(nextId++);
        posts.add(post);
    }

    // 게시글 불러오기
    public Post findById(int id) {
        for (Post post : posts) {
            if (post.getId() == id)
                return post;
        }
        return null;
    }

    // 전체 목록 가져오기
    public List<Post> findAll() {
        return posts;
    }

    // 게시글 삭제
    public void removePost(int id) {
        Post post = findById(id);
        if (post != null) {
            posts.remove(post);
        }
    }
}