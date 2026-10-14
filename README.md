# backend

## server must be on first!

open server by doing in ur project folder
```bash
cd backend
python manage.py runserver 0.0.0.0:8000
```

# run web app

```bash
cd web
npm install
npm run dev
```

# run desktop app

```bash
cd desktop
python main.py
```

lub f5 lub run  -> start debbuging

# run mobile app
open folder mobile on android studio and click run

if server does not connect and shows socket timeout exception
open terminal and write:

```bash
adb reverse tcp:8000 tcp:8000
```



## server must be on first!