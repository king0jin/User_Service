import React, {useEffect, useState} from 'react';
import axios from 'axios';
// import CheckResponse from "./CheckResponse";
import SingUp from "./SingUp";

function App() {
  const [data, setData] = useState('')

  useEffect(() => {
    axios.get('/api/data')
        .then(res => setData(res.data))
        .catch(err => console.log(err))
  }, []);

  return (
      <div>
          {/*<h2>첫 번째 데이터 요청:</h2>*/}
          {/*<p>받아온 값: {data}</p>*/}

          {/* 새로운 컴포넌트 렌더링 */}
          {/*<CheckResponse/>*/}
          <SingUp/>
      </div>
  );
}

export default App;
