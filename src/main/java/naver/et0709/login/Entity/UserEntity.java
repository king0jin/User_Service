package naver.et0709.login.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 사용자 고유 번호

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId; // 사용자 아이디

    @Column(name = "user_pw", nullable = false)
    private String userPw; // 사용자 비밀번호

    @Column(nullable = false)
    private String name; // 사용자 이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.MEMBER; // 역할 (기본값: MEMBER)

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate; // 계정 생성일

    @Column(name = "last_update")
    private LocalDateTime lastUpdate; // 마지막 업데이트

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE; // 가입 여부 상태 (기본값: ACTIVE)

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    public enum Role {
        ADMIN, MEMBER
    }

    public enum UserStatus {
        ACTIVE, INACTIVE
    }
}
