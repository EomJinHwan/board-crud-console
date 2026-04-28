package post;

import java.util.List;

public class PostService {
    //필드
    private final PostRepository pr;

    //생성자
    public PostService(PostRepository pr) {
        this.pr = pr;
    }

    //메서드

    // 게시글 저장
    public void writePost(Post post) {
        pr.writePost(post);
    }

    // 전체 목록 가져오기
    public List<Post> findAll() {
        return pr.findAll();
    }

    // 상세 게시글 불러오기
    public Post findPostById(int id) {
        // findById 값 있으면 Post 객체 반환, 없으면 null 반환
        return pr.findById(id);
    }

    // 게시글 제목 업데이트
    public void updateTitle(Post post, String newTitle) {
        post.setTitle(newTitle);
    }

    // 게시글 내용 업데이트
    public void updateContent(Post post, String newContent) {
        post.setContent(newContent);
    }

    // 게시글 삭제
    public void removePost(int id) {
        pr.removePost(id);
    }
}
