import tkinter as tk
from tkinter import messagebox

def signup():
    name = name_entry.get()
    email = email_entry.get()
    password = pass_entry.get()
    agree = agree_var.get()

    if not name or not email or not password:
        messagebox.showwarning("Missing Info", "Please fill all fields!")
        return
    if not agree:
        messagebox.showwarning("Terms", "Please agree to the terms & conditions!")
        return

    messagebox.showinfo("Success", f"Account created for {name}!")
    name_entry.delete(0, tk.END)
    email_entry.delete(0, tk.END)
    pass_entry.delete(0, tk.END)
    agree_var.set(0)

root = tk.Tk()
root.title("Sign-Up Window")
root.geometry("350x350")

tk.Label(root, text="Sign-Up Form", font=("Arial", 16, "bold")).pack(pady=10)

tk.Label(root, text="Full Name:").pack()
name_entry = tk.Entry(root, width=30); name_entry.pack(pady=3)

tk.Label(root, text="Email:").pack()
email_entry = tk.Entry(root, width=30); email_entry.pack(pady=3)

tk.Label(root, text="Password:").pack()
pass_entry = tk.Entry(root, width=30, show="*"); pass_entry.pack(pady=3)

agree_var = tk.IntVar()
tk.Checkbutton(root, text="I agree to the Terms & Conditions", variable=agree_var).pack(pady=5)

tk.Button(root, text="Sign Up", command=signup, bg="lightgreen").pack(pady=10)

root.mainloop()
