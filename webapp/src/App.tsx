import React from "react";
import "./App.css";
import {Outlet,Link} from "react-router-dom";

function App()
{
  return (
    <div className="App">
      <h1>Spring Http Only Cookie Demo </h1>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem",
        }}
      >
        <Link to="/login" style={{
          margin: "20px",
        }}>Login</Link>
        <Link to="/home">Home</Link>
      </nav>
      <Outlet />
    </div>
  );
}

export default App;
