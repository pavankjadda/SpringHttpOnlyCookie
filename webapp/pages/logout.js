import React, {useEffect} from "react";
import {useRouter} from "next/router";
import styles from "../styles/Home.module.css";


function Logout() {
  const router = useRouter();
  useEffect(() => {
    router.push("/login");
  }, []);

  return (
    <div  className={styles.main}>
      <br/>
    </div>
  );
}

export default Logout;
