import post.PostRepository;
import post.PostService;
import ui.PostUi;
import user.UserRepository;
import user.UserService;

public class Main {
    public static void main(String[] args) {
        PostRepository pr = new PostRepository();
        PostService ps = new PostService(pr);

        UserRepository ur = new UserRepository();
        UserService us = new UserService(ur);

        PostUi pui = new PostUi(ps, us);

        pui.menu();
    }
}