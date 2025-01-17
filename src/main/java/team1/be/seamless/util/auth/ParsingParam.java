package team1.be.seamless.util.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParsingParam {


    private final JwtToken jwtToken;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    public ParsingParam(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getEmail(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        token = token.substring(7);
        return jwtToken.getEmail(token);
    }

    public String getRole(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        token = token.substring(7);
        return jwtToken.getRole(token);
    }

}
