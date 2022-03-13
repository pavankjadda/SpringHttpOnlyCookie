import React, {useState} from "react";


function Login()
{
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function login()
  {
    fetch(process.env.REACT_APP_BASE_URL + "/api/v1/user/login", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        authorization: "Basic " + btoa(username + ":" + password)
      }
    })
      .then(data =>
      {
        console.log("data:" + data);
        if (data)
        {
          window.location.href = "/home";
        }
      });
  }

  return (
    <form>
      <h1>Login</h1>
      <br/>
      <label>
        Username:
        <input onChange={event => setUsername(event.target.value)} type="text"/>
      </label>
      <br/>
      <label>
        Password:
        <input onChange={event => setPassword(event.target.value)} type="password"/>
      </label>
      <br/>

      <button onClick={login}>Login</button>

    </form>
  );
}

export default Login;
