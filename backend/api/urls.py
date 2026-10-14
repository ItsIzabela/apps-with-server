from django.urls import path
from .views import player_lobby

urlpatterns = [
    path('player/', player_lobby, name='player_lobby'),
]