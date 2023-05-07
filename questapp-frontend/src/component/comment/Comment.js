import { CardContent, InputAdornment, OutlinedInput } from "@mui/material";
import React from "react";
import style from "./Comment.module.css";

function Comment({ text, userId, userName }) {
  return (
    <CardContent className={style.comment}>
      <OutlinedInput
        disabled
        id="outlined-adorment-amount"
        multiline
        inputProps={{ maxLength: 25 }}
        fullWidth
        value={text}
        startAdornment={
          <InputAdornment position="start">

          </InputAdornment>
        }
      ></OutlinedInput>
    </CardContent>
  );
}

export default Comment;
