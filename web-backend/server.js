import express from "express";
import cors from "cors";

const app = express();

const CORS_OPTIONS = {
  origin: ["http://localhost:5173"]
};

app.use(cors(CORS_OPTIONS));

app.get("/", (req, res) => {
  res.json({
    blogPosts: [
      {
        title: "Neymar's Return to Santos and Its Impact on Grêmio",
        content: "Grêmio will face tougher competition now that Neymar is back at Santos."
      },
      {
        title: "The Importance of Digital Marketing for Your Business",
        content: "Digital marketing is a powerful tool to boost your business growth."
      }
    ]
  });
});

app.listen(3000, () => {
  console.log("Server is running on port 300");
});