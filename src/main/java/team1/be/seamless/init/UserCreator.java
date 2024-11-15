package team1.be.seamless.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team1.be.seamless.dto.UserDTO.UserSimple;
import team1.be.seamless.service.UserService;

@Component
public class UserCreator {

    private final UserService userService;

    @Autowired
    public UserCreator(UserService userService) {
        this.userService = userService;
    }

    public void creator() {
        userService.createUser(new UserSimple("테스트 계정", "kakaotechcampus.mentor@gmail.com", "https://ca.slack-edge.com/T06MHGBCEKG-U06M9KTL9GX-e92c9472326e-512"));
        userService.createUser(new UserSimple("사용자2", "user2@google.com", "https://user2Image.jpg"));
        userService.createUser(new UserSimple("사용자3", "user3@google.com", "https://user3Image.jpg"));
    }
}
