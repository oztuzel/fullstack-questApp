import React, { useEffect, useRef, useState } from "react";
import style from "./Post.module.css";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import FavoriteIcon from "@mui/icons-material/Favorite";
import CommentIcon from "@mui/icons-material/Comment";
import { Link } from "react-router-dom";
import { Container } from "@mui/material";
import Comment from "../comment/Comment";
import CommentForm from "../comment/CommentForm";

function Post({ likes, postId, text, title, username, userId }) {
  // console.log("post rendered");
  const [expanded, setExpanded] = useState(false);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [commentList, setCommentList] = useState();
  const [isLiked, setIsLiked] = useState(false);
  const [likeCount, setLikeCount] = useState(likes.length);
  const [likeId, setLikeId] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const isInitialMount = useRef(true);

  const setCommentRefresh = () => {
    setRefresh(true);
  };

  const handleExpandClick = () => {
    setExpanded(!expanded);
    refreshComments();
  };

  const handleLike = () => {
    setIsLiked(!isLiked);
    if (!isLiked) {
      saveLike();
      setLikeCount(likeCount + 1);
    } else {
      deleteLike();
      setLikeCount(likeCount - 1);
    }
  };

  const refreshComments = () => {
    fetch("http://localhost:8080/comments?postId=" + postId)
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setCommentList(result);
        },
        (error) => {
          console.log(error);
          setIsLoaded(true);
          setError(error);
        }
      );
    setRefresh(false);
  };

  const saveLike = () => {
    fetch("http://localhost:8080/likes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json", 
        "Authorization" : localStorage.getItem("tokenKey") //artik isteklere tokenimizi eklememiz lazim
      },
      body: JSON.stringify({
        postId: postId,
        userId: localStorage.getItem("currentUser"),
      }),
    }).catch((err) => console.log(err));
  };

  const deleteLike = () => {
    fetch("http://localhost:8080/likes/" + likeId, {
      method: "DELETE",
      headers: {
        "Authorization" : localStorage.getItem("tokenKey")
      },
    }).catch((err) => console.log(err));
  };

  const checkLikes = () => {
    let likeControl = likes?.find((like) => ""+like.userId === localStorage.getItem("currentUser"));
    if (likeControl != null) {
      setLikeId(likeControl.id);
      setIsLiked();
    }
  };

  useEffect(() => {
    if (isInitialMount.current) {
      isInitialMount.current = true;
    } else {
      refreshComments();
    }
  }, [refresh]);

  useEffect(() => {
    checkLikes();
  }, []);

  return (
    <div className={style.container}>
      <title>
        {title} {text}
      </title>
      <Card className={style.root}>
        <CardHeader
          avatar={
            <Link className={style.link} to={{ pathname: "/users/" + userId }}>
              <Avatar className={style.avatar} aria-label="recipe">
                {username?.charAt(0).toUpperCase()}
              </Avatar>
            </Link>
          }
          title={title}
        />
        <CardContent>
          <div>{text}</div>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton aria-label="add to favorites" onClick={handleLike}>
            <FavoriteIcon style={isLiked ? { color: "red" } : null} />
          </IconButton>
          {likeCount}
          <IconButton
            className={`${expanded ? style.expand : style.expandOpen}`}
            onClick={handleExpandClick}
            aria-expanded={expanded}
            aria-label="show more"
          >
            <CommentIcon />
          </IconButton>
        </CardActions>
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Container fixed className={style.commentsContainer}>
            {error
              ? "error"
              : isLoaded
              ? commentList.map((comment) => (
                  <Comment userId={1} userName={"USER"} text={comment.text} />
                ))
              : "Loading"}
            {localStorage.getItem("currentUser") == null ? (
              ""
            ) : (
              <CommentForm userId={userId} userName={username} postId={postId} />
            )}
          </Container>
        </Collapse>
      </Card>
    </div>
  );
}

export default Post;
