import axios from "axios";
import { useEffect, useState } from "react";

function App() {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    const response = await axios.get("http://localhost:3000");
    setData(response.data.groupChat);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div id="center">
      <h2>Chat</h2>
      <div id="forms">
        <form action="" method="post">
          <label htmlFor="first">Message:</label>
          <input type="text" name="firstPerson" id="firstPerson" />
          <input type="button" value="send" />
          <br />
          <br />
          <label htmlFor="first">Respose:</label>
          <input type="text" name="secondPerson" id="secondPerson" />
          <input type="button" value="send" />
        </form>
      </div>
      <div id="result"> 
        <ul>
        {data.map((post, index) => (
          <li key={index}>
            <p>{post.name}</p>
            <p >{post.message}</p>
          </li>
        ))}
      </ul>
      </div>
    </div>
  );
}

export default App;