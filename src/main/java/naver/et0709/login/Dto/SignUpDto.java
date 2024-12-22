package naver.et0709.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private Integer id; // 사용자 고유 번호
    private String userId; // 사용자 아이디
    private String userPw; // 사용자 비밀번호
    private String name; // 사용자 이름
    private Role role; // 역할
    private LocalDateTime createdDate; // 계정 생성일
    private LocalDateTime lastUpdate; // 마지막 업데이트
    private UserStatus userStatus; // 가입 여부 상태

    public enum Role {
        ADMIN, MEMBER
    }

    public enum UserStatus {
        ACTIVE, INACTIVE
    }

}
