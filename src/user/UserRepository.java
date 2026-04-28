package user;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> map = new HashMap<>();

    // 아이디로 value값 찾기
    public User findById(String id) {
        return map.get(id);
    }

    // 회원가입 put(key, value)
    public void signUp(User user) {
        map.put(user.getId(), user);
    }
}
