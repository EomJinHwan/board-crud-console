package ui;

import post.Post;
import post.PostService;
import user.User;
import user.UserService;

import java.util.List;
import java.util.Scanner;

public class PostUi {
    //필드
    private final PostService ps;
    private final UserService us;
    private final Scanner sc = new Scanner(System.in);

    //생성자
    public PostUi(PostService ps, UserService us) {
        this.ps = ps;
        this.us = us;
    }

    // 메뉴 UI
    public void menu() {
        while (true) {
            System.out.print("1.게시글 작성 | 2.글 목록 조회 | 3.글 상세 조회 | 4. 글 수정 " + "\n" + "| 5. 글 삭제 | 6.회원가입 | 7.로그인 | 8.로그아웃 | 9.종료" + "\n" + "입력 : ");
            String menu = sc.nextLine();

            switch (menu) {
                case "1":
                    writePost();
                    break;
                case "2":
                    simplePost();
                    break;
                case "3":
                    detailedPost();
                    break;
                case "4":
                    updateUi();
                    break;
                case "5":
                    removePost();
                    break;
                case "6":
                    signUpUi();
                    break;
                case "7":
                    loginUi();
                    break;
                case "8":
                    logoutUi();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("메뉴 번호를 확인해 주세요");
            }
        }
    }

    // 게시글 작성 UI
    public void writePost() {
        // 로그인 여부 확인
        if (!us.isLogin()) {
            System.out.println("로그인 후 이용해주세요");
            return;
        }

        System.out.print("제목을 입력해주세요 : ");
        String title = sc.nextLine();

        System.out.print("내용을 입력해주세요 : ");
        String content = sc.nextLine();

        String writer = us.getLoginUser().getName();

        Post post = new Post(title, content, writer);
        ps.writePost(post);

        System.out.println("게시글 작성이 완료되었습니다");
        System.out.println(post);
    }

    // 전체 글 목록 조회
    public void simplePost() {
        List<Post> posts = ps.findAll();
        if (posts.isEmpty()) {
            System.out.println("작성된 게시물이 없습니다");
            return;
        }
        System.out.println("--전체 게시글--");
        for (Post post : posts) {
            System.out.println(post.simpleString());
        }
    }

    // 게시글 상세 조회
    public void detailedPost() {
        int inputId;
        while (true) {
            try {
                System.out.print("조회할 Id 값을 입력해 주세요 : ");
                inputId = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요");
            }
        }

        Post post = ps.findPostById(inputId);
        if (post == null) {
            System.out.println("조건에 맞는 게시글이 없습니다");
        } else {
            System.out.println(post);
        }
    }

    // 게시글 수정 Ui
    public void updateUi() {
        // 로그인 여부 확인
        if (!us.isLogin()) {
            System.out.println("로그인 후 이용해주세요");
            return;
        }
        int inputId;
        while (true) {
            try {
                System.out.print("수정할 Id 값을 입력해 주세요 : ");
                inputId = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해 주세요");
            }
        }

        Post post = ps.findPostById(inputId);
        if (post == null) {
            System.out.println("조건에 맞는 게시글이 없습니다");
            return;
        }

        // 게시글 작성자와 현재 로그인유저 비교
        if (!post.getWriter().equals(us.getLoginUser().getName())) {
            System.out.println("작성자만 수정할 수 있습니다");
            return;
        }

        while (true) {
            System.out.println("수정 할 메뉴를 선택해 주세요");
            System.out.print("1.제목 수정 | 2.내용 수정 | 3.돌아가기 : ");
            String menu = sc.nextLine();

            switch (menu) {
                case "1":
                    updateTitle(post);
                    break;
                case "2":
                    updateContent(post);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("메뉴 번호를 확인해 주세요");
            }
        }
    }

    // 게시글 제목 수정
    public void updateTitle(Post post) {
        System.out.println("현재 제목 : " + post.getTitle());
        System.out.print("수정 할 제목을 입력해 주세요 : ");
        String newTitle = sc.nextLine();
        ps.updateTitle(post, newTitle);

        System.out.println("제목 수정을 완료했습니다");
    }

    // 게시글 내용 수정
    public void updateContent(Post post) {
        System.out.println("현재 내용 : " + post.getContent());
        System.out.print("수정 할 내용을 입력해 주세요 : ");
        String newContent = sc.nextLine();
        ps.updateContent(post, newContent);

        System.out.println("내용 수정을 완료했습니다");
    }

    // 게시글 삭제
    public void removePost() {
        // 로그인 여부 확인
        if (!us.isLogin()) {
            System.out.println("로그인 후 이용해주세요");
            return;
        }
        int removeId;
        while (true) {
            try {
                System.out.print("삭제하실 게시글의 id를 입력해주세요 : ");
                removeId = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해 주세요");
            }
        }

        Post post = ps.findPostById(removeId);
        if (post == null) {
            System.out.println("조건에 맞는 게시글이 없습니다");
            return;
        }

        // 게시글 작성자와 현재 로그인유저 비교
        if (!post.getWriter().equals(us.getLoginUser().getName())) {
            System.out.println("작성자만 삭제할 수 있습니다");
            return;
        }

        while (true) {
            System.out.println("---------------------------------");
            System.out.print("게시글을 삭제하시겠습니까? Y(y) / N(n) : ");
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("n")) return;
            if (!result.equalsIgnoreCase("y")) {
                System.out.println("입력 내용을 확인해주세요");
                continue;
            }
            ps.removePost(removeId);
            System.out.println("게시글 삭제가 완료되었습니다");
            return;
        }
    }

    // 회원가입
    public void signUpUi() {
        while (true) {
            System.out.println("-------회원가입-------");
            System.out.print("아이디를 입력해 주세요 : ");
            String inputId = sc.nextLine();

            if (us.isDuplicatedId(inputId)) {
                System.out.println("중복된 아이디 입니다 다시 입력해주세요");
                return;
            }

            System.out.print("비밀번호를 입력해 주세요 : ");
            String inputPw = sc.nextLine();

            System.out.print("이름을 입력해 주세요 : ");
            String inputName = sc.nextLine();

            //user.User 객체 생성
            User user = new User(inputId, inputPw, inputName);

            //회원가입 진행
            us.signUp(user);
            System.out.println("회원가입이 완료되었습니다");
            return;
        }
    }

    // 로그인
    public void loginUi() {
        while (true) {
            System.out.println("-----로그인-----");
            System.out.print("아이디를 입력해주세요(돌아가기 0) : ");
            String inputId = sc.nextLine();
            if (inputId.equals("0")) return;

            System.out.print("비밀번호를 입력해주세요(돌아가기 0) : ");
            String inputPw = sc.nextLine();
            if (inputPw.equals("0")) return;

            try {
                us.login(inputId, inputPw);
                System.out.println("로그인 완료되었습니다");
                return;

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 로그아웃
    public void logoutUi() {
        try {
            us.logout();
            System.out.println("로그아웃 되었습니다");

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}