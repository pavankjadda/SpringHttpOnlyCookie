import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter, Route, Routes,} from "react-router-dom";
import App from "./App";
import Home from "./home/Home";
import "./index.css";
import Login from "./login/Login";

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App/>}>
          <Route path="home" element={<Home/>}/>
          <Route path="login" element={<Login/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
);

