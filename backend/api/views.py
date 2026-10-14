from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

PLAYERS_LOBBY = []
MAX_PLAYERS = 5

@api_view(['GET', 'POST', 'DELETE'])
def player_lobby(request):
    global PLAYERS_LOBBY

    if request.method == 'GET':
        return Response({
            "players": PLAYERS_LOBBY,
            "count": len(PLAYERS_LOBBY),
            "max_players": MAX_PLAYERS
        }, status=status.HTTP_200_OK)

    elif request.method == 'POST':
        if len(PLAYERS_LOBBY) >= MAX_PLAYERS:
            return Response(
                {"error": f"Lobby jest pełne! Maksymalna liczba graczy to {MAX_PLAYERS}."},
                status=status.HTTP_400_BAD_REQUEST
            )

        name = request.data.get('name', '').strip()
        color = request.data.get('color', '')

        if not name or not color:
            return Response({"error": "Wpisz imię i wybierz kolor."}, status=status.HTTP_400_BAD_REQUEST)

        if any(p['name'].lower() == name.lower() for p in PLAYERS_LOBBY):
            return Response({"error": "Gracz o takim imieniu już istnieje w lobby!"}, status=status.HTTP_400_BAD_REQUEST)

        new_player = {
            "id": len(PLAYERS_LOBBY) + 1,
            "name": name,
            "color": color
        }
        PLAYERS_LOBBY.append(new_player)
        return Response(new_player, status=status.HTTP_201_CREATED)

    elif request.method == 'DELETE':
        PLAYERS_LOBBY = []
        return Response({"message": "Lobby zostało zrestartowane."}, status=status.HTTP_200_OK)