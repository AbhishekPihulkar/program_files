import tkinter as tk
from tkinter import messagebox

def transfer():
    sender = from_entry.get()
    receiver = to_entry.get()
    amount = amount_entry.get()
    if not sender or not receiver or not amount:
        messagebox.showwarning("Missing Info", "Please fill all fields!")
        return
    messagebox.showinfo("Success", f"₹{amount} transferred from {sender} to {receiver}.")

root = tk.Tk()
root.title("Fund Transfer")
root.geometry("350x300")

tk.Label(root, text="Fund Transfer", font=("Arial", 16, "bold")).pack(pady=10)

tk.Label(root, text="From Account:").pack()
from_entry = tk.Entry(root, width=30); from_entry.pack(pady=3)

tk.Label(root, text="To Account:").pack()
to_entry = tk.Entry(root, width=30); to_entry.pack(pady=3)

tk.Label(root, text="Amount (₹):").pack()
amount_entry = tk.Entry(root, width=30); amount_entry.pack(pady=3)

tk.Button(root, text="Transfer", command=transfer, bg="lightgreen").pack(pady=10)

root.mainloop()