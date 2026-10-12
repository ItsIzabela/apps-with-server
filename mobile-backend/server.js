const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors());
app.use(express.json());

app.get('/api/mobile-data', (req, res) => {
  res.json({ message: "Dane dla aplikacji mobilnej" });
});

const PORT = 4000;
app.listen(PORT, () => {
  console.log(`Mobile backend działa na porcie ${PORT}`);
});
