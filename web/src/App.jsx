import { useEffect, useState } from "react";

const API_URL = "http://127.0.0.1:8000/api";

function App() {
  const [status, setStatus] = useState("Sprawdzam serwer...");

  useEffect(() => {
    fetch(`${API_URL}/health/`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Blad serwera");
        }
        return response.json();
      })
      .then((data) => {
        setStatus(data.message);
      })
      .catch(() => {
        setStatus("Nie mozna polaczyc sie z Django.");
      });
  }, []);

  return (
    <main style={{ padding: 32, fontFamily: "Arial" }}>
      <h1>Moja aplikacja webowa</h1>
      <p>Polaczenie z backendem:</p>
      <strong>{status}</strong>
    </main>
  );
}

export default App;