import React from "react";
import style from "./Navbar.module.css";
import { Link } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import { LockOpen } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

function Navbar() {
  let navigate = useNavigate();

  const onClick = () => {
    localStorage.removeItem("tokenKey");
    localStorage.removeItem("currentUser");
    localStorage.removeItem("username");
    navigate();
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <div className={style.title}>
            <Link className={style.link} to="/">
              Home
            </Link>
          </div>
          <div>
            {localStorage.getItem("currentUser") == null ? (
              <Link className={style.link} to="/auth">
                Login / Register
              </Link>
            ) : (
              <div>
                <IconButton onClick={onClick}>
                  <LockOpen></LockOpen>
                </IconButton>
                <Link
                  className={style.link}
                  to={{
                    pathname: "/users/" + localStorage.getItem("currentUser"),
                  }}
                >
                  Profile
                </Link>
              </div>
            )}
          </div>
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default Navbar;
