package naver.et0709.login.controller;

import naver.et0709.login.Dto.ResponseDto;
import naver.et0709.login.Dto.SignUpDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @PostMapping("/signUp")
    public ResponseDto<SignUpDto> signUp(@RequestBody SignUpDto requestBody) {
        System.out.println(requestBody.toString());
        return null;
    }
}
