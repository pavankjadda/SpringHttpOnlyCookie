import React, {useEffect} from 'react';
import styles from '../styles/Home.module.css'


function Home() {
  const [user, setUser] = React.useState(null);

  useEffect(() => {
    fetch("/api/v1/user/home", {
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(data => {
        setUser(data);
      })
      .catch(err => {
        console.log("err:" + err.data)
      });
  }, []);
  return (
    <div className={styles.main}>
      <h1>Home</h1>

      <div className={styles.container}>
        <div>
          <label>First Name: {user?.firstName}</label> <br />
          <label>Last Name: {user?.lastName}</label><br />
          <label>Email: {user?.email}</label><br />
          <label>Username: {user?.username}</label><br /> <br /><br />
        </div>
      </div>

      <nav>
        <a href="/" style={{margin: "10px"}}>Home</a>
        <a href="/logout" style={{margin: "10px"}}>Logout</a>
      </nav>
    </div>
  );
}

export default Home;
