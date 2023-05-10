import React, { useState, useEffect } from "react";
import Post from "../post/Post";
import style from "./Home.module.css";
import PostForm from "../post/PostForm";

function Home() {
  // console.log("home executed.");
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [postList, setPostList] = useState();

  const refreshPosts = () => {
    fetch("http://localhost:8080/posts")
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setPostList(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  };

  useEffect(() => {
    refreshPosts();
  }, [postList?.length]);

  if (error) {
    return <div> Error !! </div>;
  } else if (!isLoaded) {
    return <div> Loading ...</div>;
  } else {
    return (
      <div className={style.container}>
        {localStorage.getItem("currentUser") == null ? (
          ""
        ) : (
          <PostForm
            userId={localStorage.getItem("currentUser")}
            username={localStorage.getItem("username")}
            refreshPosts={refreshPosts}
          />
        )}
        {postList.map((post) => {
          return (
            <Post
              userId={post.userId}
              username={post.username}
              title={post.title}
              text={post.text}
              postId={post.id}
              key={post.id}
              likes={post.postLikes}
            />
          );
        })}
      </div>
    );
  }
}

export default Home;
