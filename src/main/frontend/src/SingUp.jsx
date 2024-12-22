import React, { useState } from "react";
import axios from "axios";

function SignUp() {
    const [form, setForm] = useState({
        name: "",
        userId: "",
        userPw: "",
        confirmPw: "",
    });
    const [message, setMessage] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleCheckDuplicate = async () => {
        try {
            const response = await axios.post("/auth/checkDuplicate", {
                userId: form.userId,
            });
            setMessage(response.data.success ? "사용 가능한 아이디입니다." : "이미 사용 중인 아이디입니다.");
        } catch (error) {
            setMessage("중복 확인 중 오류가 발생했습니다.");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (form.userPw !== form.confirmPw) {
            setMessage("비밀번호가 일치하지 않습니다.");
            return;
        }
        try {
            const response = await axios.post("/auth/signUp", {
                name: form.name,
                userId: form.userId,
                userPw: form.userPw,
            });
            setMessage(response.data.success ? "회원가입 성공!" : response.data.message);
        } catch (error) {
            setMessage("회원가입 중 오류가 발생했습니다.");
        }
    };

    return (
        <div style={{ maxWidth: "400px", margin: "auto", padding: "20px" }}>
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: "10px" }}>
                    <label>이름:</label>
                    <input
                        type="text"
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                        required
                        style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>
                <div style={{ marginBottom: "10px" }}>
                    <label>아이디:</label>
                    <input
                        type="text"
                        name="userId"
                        value={form.userId}
                        onChange={handleChange}
                        required
                        style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                    <button
                        type="button"
                        onClick={handleCheckDuplicate}
                        style={{ marginTop: "5px", padding: "8px", width: "100%" }}
                    >
                        중복 확인
                    </button>
                </div>
                <div style={{ marginBottom: "10px" }}>
                    <label>비밀번호:</label>
                    <input
                        type="password"
                        name="userPw"
                        value={form.userPw}
                        onChange={handleChange}
                        required
                        style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>
                <div style={{ marginBottom: "10px" }}>
                    <label>비밀번호 확인:</label>
                    <input
                        type="password"
                        name="confirmPw"
                        value={form.confirmPw}
                        onChange={handleChange}
                        required
                        style={{ width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>
                <button type="submit" style={{ width: "100%", padding: "10px", marginTop: "10px" }}>
                    회원가입
                </button>
            </form>
            {message && <p style={{ marginTop: "10px", color: "blue" }}>{message}</p>}
        </div>
    );
}

export default SignUp;
