package team1.BE.seamless.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.BE.seamless.dto.UserDTO.UserDetails;
import team1.BE.seamless.dto.UserDTO.UserSimple;
import team1.BE.seamless.dto.UserDTO.UserUpdate;
import team1.BE.seamless.entity.UserEntity;
import team1.BE.seamless.mapper.UserMapper;
import team1.BE.seamless.repository.UserRepository;
import team1.BE.seamless.util.auth.ParsingParam;
import team1.BE.seamless.util.errorException.BaseHandler;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ParsingParam parsingParam;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper,
        ParsingParam parsingParam) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.parsingParam = parsingParam;
    }

    public UserDetails getUser(HttpServletRequest req) {
        UserEntity user = userRepository.findByEmailAndIsDeleteFalse(parsingParam.getEmail(req))
            .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."));
        return userMapper.toUserDetails(user);
    }

    @Transactional
    public UserSimple updateUser(HttpServletRequest req, UserUpdate update) {
        UserEntity user = userRepository.findByEmailAndIsDeleteFalse(parsingParam.getEmail(req))
            .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."));

        userMapper.toUpdate(user, update);

        return userMapper.toUserSimple(user);
    }

    @Transactional
    public UserSimple deleteUser(HttpServletRequest req) {
        UserEntity user = userRepository.findByEmailAndIsDeleteFalse(parsingParam.getEmail(req))
            .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."));

        user.setIsDelete();

        return userMapper.toUserSimple(user);
    }

    @Transactional
    public UserEntity createUser( UserSimple simple) {
        return userRepository.findByEmailAndIsDeleteFalse(simple.getEmail())
            .orElseGet(() -> userRepository.save(
                userMapper.toEntity(
                    simple.getUsername(),
                    simple.getEmail(),
                    simple.getPicture()
                )));
    }
}
