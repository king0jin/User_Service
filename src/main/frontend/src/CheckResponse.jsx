import React, { useEffect, useState } from "react";
import axios from "axios";

function CheckResponse() {
    const [response, setResponse] = useState("");

    useEffect(() => {
        axios
            .get("http://localhost:8080/check") // Spring Boot 서버 요청
            .then((res) => setResponse(res.data))
            .catch((err) => console.error(err));
    }, []);

    return (
        <div>
            <h2>Spring Boot 서버 응답:</h2>
            <p>{response}</p>
        </div>
    );
}

export default CheckResponse;
