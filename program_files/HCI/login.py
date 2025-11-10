import tkinter as tk
from tkinter import messagebox

def login():
    user = user_entry.get()
    pwd = pass_entry.get()

    if user == "admin" and pwd == "1234":
        messagebox.showinfo("Login Success", "Welcome, Admin!")
    else:
        messagebox.showerror("Login Failed", "Invalid Username or Password")

root = tk.Tk()
root.title("Login Window")
root.geometry("300x250")

tk.Label(root, text="Login Form", font=("Arial", 16, "bold")).pack(pady=15)

tk.Label(root, text="Username:").pack()
user_entry = tk.Entry(root, width=30)
user_entry.pack(pady=5)

tk.Label(root, text="Password:").pack()
pass_entry = tk.Entry(root, width=30, show="*")
pass_entry.pack(pady=5)

tk.Button(root, text="Login", command=login, bg="lightgreen").pack(pady=10)
tk.Button(root, text="Exit", command=root.destroy, bg="lightcoral").pack()

root.mainloop()
