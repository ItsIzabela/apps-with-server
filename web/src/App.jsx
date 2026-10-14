import React, { useState, useEffect } from 'react';

function App() {
  const [lobby, setLobby] = useState({ players: [], count: 0, max_players: 5 });

  useEffect(() => {
    const interval = setInterval(() => {
      fetch('http://127.0.0.1:8000/api/player/')
        .then((res) => res.json())
        .then((data) => setLobby(data))
        .catch((err) => console.error("Błąd sieci:", err));
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div style={{ textAlign: 'center', padding: '30px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Lobby</h1>
      <h2>Liczba graczy: {lobby.count} / {lobby.max_players}</h2>

      <div style={{ display: 'flex', justifyContent: 'center', gap: '20px', flexWrap: 'wrap', marginTop: '30px' }}>
        {lobby.players.length > 0 ? (
          lobby.players.map((player) => (
            <div key={player.id} style={{
              border: '2px solid #ccc',
              borderRadius: '12px',
              padding: '15px',
              width: '130px',
              boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
              backgroundColor: '#f9f9f9'
            }}>
              <h3 style={{ margin: '0 0 10px 0', fontSize: '18px' }}>{player.name}</h3>
              <div style={{
                width: '80px',
                height: '80px',
                backgroundColor: player.color,
                margin: '0 auto',
                borderRadius: '8px'
              }} />
            </div>
          ))
        ) : (
          <p style={{ color: '#777', fontSize: '16px' }}>Brak graczy w lobby. Dodaj kogoś w aplikacji Desktop!</p>
        )}
      </div>
    </div>
  );
}

export default App;