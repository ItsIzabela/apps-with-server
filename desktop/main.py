import tkinter as tk
from tkinter import messagebox
import threading
import requests

API_URL = "http://127.0.0.1:8000/api"


def check_server():
    status_label.config(text="Laczenie z serwerem...")

    def request_api():
        try:
            response = requests.get(
                f"{API_URL}/health/",
                timeout=5
            )
            response.raise_for_status()
            data = response.json()
            message = data["message"]
        except requests.RequestException:
            message = "Nie mozna polaczyc sie z Django."
        except (ValueError, KeyError):
            message = "Nieprawidlowa odpowiedz serwera."

        root.after(
            0,
            lambda: status_label.config(text=message)
        )

    threading.Thread(target=request_api, daemon=True).start()


root = tk.Tk()
root.title("Moja aplikacja desktopowa")
root.geometry("400x220")

title = tk.Label(
    root,
    text="Aplikacja desktopowa",
    font=("Arial", 16)
)
title.pack(pady=20)

status_label = tk.Label(root, text="Kliknij, aby sprawdzic serwer.")
status_label.pack(pady=10)

check_button = tk.Button(
    root,
    text="Polacz z Django",
    command=check_server
)
check_button.pack(pady=10)

root.mainloop()