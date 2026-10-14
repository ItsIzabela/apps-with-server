import tkinter as tk
from tkinter import ttk, messagebox
import threading
import requests

API_URL = "http://127.0.0.1:8000/api"

COLOR_MAP = {
    "Czerwony": "#FF4D4D",
    "Zielony": "#2ECC71",
    "Niebieski": "#3498DB",
    "Żółty": "#F1C40F",
    "Fioletowy": "#9B59B6",
    "Pomarańczowy": "#E67E22"
}

def add_player():
    name = name_entry.get().strip()
    color_name = color_combobox.get()
    color_hex = COLOR_MAP.get(color_name)

    if not name:
        messagebox.showwarning("Błąd", "Wpisz imię gracza!")
        return

    status_label.config(text="Dodawanie gracza...")

    def request_api():
        try:
            payload = {"name": name, "color": color_hex}
            response = requests.post(f"{API_URL}/player/", json=payload, timeout=5)
            
            if response.status_code == 201:
                msg = f"Dodano gracza: {name}!"
                root.after(0, lambda: name_entry.delete(0, tk.END))
            else:
                err = response.json().get("error", "Błąd serwera.")
                msg = f"Błąd: {err}"
        except requests.RequestException:
            msg = "Nie można połączyć się z Django."

        root.after(0, lambda: status_label.config(text=msg))

    threading.Thread(target=request_api, daemon=True).start()

def clear_lobby():
    def request_api():
        try:
            requests.delete(f"{API_URL}/player/", timeout=5)
            root.after(0, lambda: status_label.config(text="Reset lobby wykonany!"))
        except requests.RequestException:
            root.after(0, lambda: status_label.config(text="Błąd połączenia z Django."))

    threading.Thread(target=request_api, daemon=True).start()

root = tk.Tk()
root.title("Lobby Master (Desktop)")
root.geometry("420x380")

tk.Label(root, text="Dodaj Gracza do Lobby (Max 5)", font=("Arial", 14, "bold")).pack(pady=15)

tk.Label(root, text="Imię gracza:").pack()
name_entry = tk.Entry(root, font=("Arial", 11))
name_entry.pack(pady=5)

tk.Label(root, text="Wybierz kolor:").pack()
color_combobox = ttk.Combobox(root, values=list(COLOR_MAP.keys()), state="readonly", font=("Arial", 10))
color_combobox.set("Czerwony")
color_combobox.pack(pady=5)

add_btn = tk.Button(root, text="Dodaj gracza do Lobby", command=add_player, bg="#2ECC71", fg="white", font=("Arial", 11, "bold"))
add_btn.pack(pady=15)

clear_btn = tk.Button(root, text="Wyczyść całe Lobby", command=clear_lobby, bg="#E74C3C", fg="white", font=("Arial", 9))
clear_btn.pack(pady=5)

status_label = tk.Label(root, text="Gotowy do dodawania graczy.", fg="#555")
status_label.pack(pady=10)

root.mainloop()