package post;

public class Post {
    //필드
    private int id;
    private String title;
    private String content;
    private String writer;

    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "--------------------------" + "\n" +
                "post.Post{" + "\n" +
                "id : " + id + "\n" +
                "제목 : " + title + "\n" +
                "내용 : " + content + "\n" +
                "작성자 : " + writer + "\n" +
                "}" + "\n" +
                "--------------------------";
    }

    public String simpleString() {
        return "id : " + getId() + " | 제목 : " + getTitle() + " | 작성자 : " + getWriter();
    }
}