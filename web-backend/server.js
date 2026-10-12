import express from "express";
import cors from "cors";

const app = express();

const CORS_OPTIONS = {
  origin: ["http://localhost:5173"]
};

app.use(cors(CORS_OPTIONS));

app.get("/", (req, res) => {
  res.json({
    groupChat: [
      {
        name: "x",
        message: "hello."
      },
      {
        name: "y",
        message: "bye."
      }
    ]
  });
});

app.listen(3000, () => {
  console.log("Server is running on port 300");
});