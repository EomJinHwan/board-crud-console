package user;

public class UserService {
    //필드
    private final UserRepository ur;
    private User loginUser;

    //생성자
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    //메서드

    // 아이디 중복 확인
    public boolean isDuplicatedId(String inputId) {
        // 아이디 존재하면(중복o) true 반환 / 없으면(중복x) false 반환
        return ur.findById(inputId) != null;
//아이디 중복
    }

    // 회원가입 진행
    public void signUp(User user) {
        ur.signUp(user);
    }

    // 로그인 진행
    public void login(String id, String pw) {
        // 로그인 후 다시 로그인 하는거 방지
        if (loginUser != null) {
            throw new IllegalStateException("이미 로그인 상태 입니다");
        }
        //id로 value값 있는지 조회
        User user = ur.findById(id);

        // 객체 null : 아이디 없음 / pw : pw틀림
        if (user == null || !user.getPw().equals(pw)) {
            throw new IllegalArgumentException("아이디나 비밀번호가 틀립니다");
        }
        // 로그인 성공시 user객체를 loginUser변수에 저장
        loginUser = user;
    }

    // 로그인 여부 확인
    public boolean isLogin() {
        // null(로그인 x) = false 반환 / null x(로그인 o) = true 반환
        return loginUser != null;
    }

    // 로그아웃
    public void logout() {
        if (loginUser == null) {
            throw new IllegalStateException("로그인 상태가 아닙니다");
        }
        loginUser = null;
    }

    // 로그인 사용자 가져오기
    public User getLoginUser() {
        return loginUser;
    }
}
