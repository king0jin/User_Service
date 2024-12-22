package naver.et0709.login.Entity;

import jakarta.persistence.*;
import lombok.*;
import naver.et0709.login.Dto.SignUpDto;

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

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.MEMBER; // 역할 (기본값: MEMBER)

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate; // 계정 생성일

    @Column(name = "last_update")
    private LocalDateTime lastUpdate; // 마지막 업데이트

    @Builder.Default
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
    // SignUpDto로 UserEntity 생성
    public UserEntity(SignUpDto dto) {
        this.userId = dto.getUserId();
        this.userPw = dto.getUserPw();
        this.name = dto.getName();
        this.role = mapRole(dto.getRole());
        this.createdDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
        this.userStatus = mapUserStatus(dto.getUserStatus());
    }
    private UserStatus mapUserStatus(SignUpDto.UserStatus dtoStatus) {
        if (dtoStatus == null) {
            return UserStatus.ACTIVE; // 기본값
        }
        return UserStatus.valueOf(dtoStatus.name()); // 이름 매핑
    }
    private Role mapRole(SignUpDto.Role dtoRole) {
        if (dtoRole == null) {
            return Role.MEMBER; // 기본값
        }
        return Role.valueOf(dtoRole.name()); // 이름 매핑
    }

    public enum Role {
        ADMIN, MEMBER
    }

    public enum UserStatus {
        ACTIVE, INACTIVE
    }
}
