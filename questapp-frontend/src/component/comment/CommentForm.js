import {
  Avatar,
  Button,
  CardContent,
  InputAdornment,
  OutlinedInput,
} from "@mui/material";
import React, { useState } from "react";
import style from "./Comment.module.css";
import { Link } from "react-router-dom";

function CommentForm({ userId, userName , postId}) {
  const [text, setText] = useState("");
  
  const saveComment = () => {
    fetch("http://localhost:8080/comments", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        postId: postId,
        userId: userId,
        text: text,
      }),
    })
      .then((res) => res.json())
      .catch((err) => console.log("error"));
  };


  const handleSubmit = () => {
    saveComment();
    setText("");
  };

  const handleChange = (value) => {
    setText(value);
  }

  return (
    <CardContent className={style.comment}>
      <OutlinedInput
        id="outlined-adorment-amount"
        multiline
        inputProps={{ maxLength: 250 }}
        fullWidth
        onChange={(i) => handleChange(i.target.value)}
        startAdornment={
          <InputAdornment position="start">
            <Link className={style.link} to={{ pathname: "/users/" + userId }}>
              <Avatar aria-label="recipe" className={style.small}>
                {userName?.charAt(0).toUpperCase()}
              </Avatar>
            </Link>
          </InputAdornment>
        }
        endAdornment={
          <InputAdornment position="end">
            <Button
              variant="contained"
              className={style.commentButton}
              onClick={handleSubmit}
            >
              Comment
            </Button>
          </InputAdornment>
        }
        value={text}
      ></OutlinedInput>
    </CardContent>
  );
}

export default CommentForm;
