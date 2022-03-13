import React, {useState} from "react";
import styles from "../styles/Home.module.css";


function Login()
{
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function login()
  {
    fetch("/api/v1/user/login", {
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
      })
      .catch(err => {
        console.log("err:"+err.data)
      });
  }

  return (
    <form  className={styles.main}>
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

      <button onClick={login} type="button">Login</button>

    </form>
  );
}

export default Login;
