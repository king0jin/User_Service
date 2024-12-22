package naver.et0709.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean success; // 성공 여부
    private String message; // 응답 메시지
    private D data; // 응답 데이터

    // 성공 응답을 생성하는 정적 메서드
    public static <D> ResponseDto<D> success(D data, String message) {
        return ResponseDto.set(true, message, data);
    }

    // 실패 응답을 생성하는 정적 메서드
    public static <D> ResponseDto<D> failure(String message) {
        return ResponseDto.set(false, message, null);
    }

    // data가 null인 경우에도 호출할 수 있는 실패 응답 메서드
    public static <D> ResponseDto<D> failure(String message, D data) {
        return ResponseDto.set(false, message, data);
    }
}
