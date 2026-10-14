# 🚀 Uruchamianie Systemu Multiplatformowego

Projekt składa się z serwera backendowego Django, aplikacji webowej React, aplikacji Desktopowej (Python) oraz aplikacji mobilnej Android.

# 1. Backend (Django) ⚠️ SERWER MUSI BYĆ URUCHOMIONY JAKO PIERWSZY!

Otwórz terminal w głównym katalogu projektu i uruchom serwer:

```bash
cd backend
python manage.py runserver 0.0.0.0:8000
```


Serwer będzie dostępny pod adresem: ```http://127.0.0.1:8000/```

# 2. Aplikacja Webowa (React)

Otwórz nowy terminal w głównym katalogu projektu:

```bash
cd web
npm install
npm run dev
```

# 3. Aplikacja Desktopowa (Python Tkinter)

Otwórz nowy terminal w głównym katalogu projektu:

```bash
cd desktop
python main.py
```


Alternatywnie w VS Code / PyCharm: naciśnij F5 lub wybierz Run ➔ Start Debugging.

# 4. Aplikacja Mobilna (Android Studio)

Otwórz folder mobile w Android Studio.

Poczekaj na synchronizację projektu Gradle.

Wybierz emulator lub podłączony telefon i kliknij Run.

🛠️ Rozwiązywanie problemów z siecią w Androidzie

Błąd: ```SocketTimeoutException``` / Brak połączenia z Django

Jeśli aplikacja mobilna nie może połączyć się z serwerem lokalnym, otwórz terminal (np. w Android Studio) i wykonaj komendę przekierowania portu przez ADB:

```bash
adb reverse tcp:8000 tcp:8000
```


Uwaga: Upewnij się, że w kodzie Androida adres URL wskazuje na http://127.0.0.1:8000/api/ po użyciu komendy adb reverse.