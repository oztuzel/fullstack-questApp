import React, { useState } from "react";
import style from "./Auth.module.css";
import { Navigate } from "react-router-dom";

function Auth() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const user = localStorage.getItem("currentUser");
  console.log(user);

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const handleUsername = (event) => {
    setUsername(event.target.value);
  };

  const registerButton = (e) => {
    e.preventDefault();
    sendRequest("register");
    setUsername("");
    setPassword("");
  };
  const loginButton = (e) => {
    e.preventDefault();
    sendRequest("login");
    setUsername("");
    setPassword("");
  };

  const sendRequest = (path) => {
    fetch("http://localhost:8080/auth/" + path, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: username,
        password: password,
      }),
    })
      .then((res) => res.json())
      .then((result) => {
        localStorage.setItem("tokenKey", result.token);
        localStorage.setItem("currentUser", result.userId);
        localStorage.setItem("username", username);
        console.log(localStorage);
        console.log(result);
      })
      .catch((err) => console.log(err));
  };

  return (
    <form className={style.form}>
      <h4>Username</h4>
      <input onChange={handleUsername} name="username" value={username} />
      <h4>Password</h4>
      <input onChange={handlePassword} name="password" value={password} />
      <div>
        <button className={style.button} onClick={registerButton} type="submit">
          Register
        </button>
        <button
          className={style.loginButton}
          type="submit"
          onClick={loginButton}
        >
          Login
        </button>
      </div>
      {user != null && <Navigate replace to="/" />}
    </form>
  );
}

export default Auth;
