import tkinter as tk
from tkinter import ttk, messagebox
import random

def calculate_fare():
    pickup = pickup_entry.get()
    drop = drop_entry.get()
    vehicle = vehicle_type.get()
    
    if not pickup or not drop:
        messagebox.showwarning("Missing Info", "Please enter both pickup and drop addresses!")
        return
    
    base = 30 if vehicle == "Auto" else 50
    fare = base + random.randint(50, 200)
    result.set(f"Estimated Fare for {vehicle}: ₹{fare}")

root = tk.Tk()
root.title("Cab/Auto Booking App")
root.geometry("400x300")

tk.Label(root, text="Cab/Auto Booking App", font=("Arial", 16, "bold")).pack(pady=10)

tk.Label(root, text="Enter Pickup Address:").pack()
pickup_entry = tk.Entry(root, width=40)
pickup_entry.pack(pady=2)

tk.Label(root, text="Enter Drop Address:").pack()
drop_entry = tk.Entry(root, width=40)
drop_entry.pack(pady=2)

tk.Label(root, text="Select Vehicle Type:").pack(pady=5)
vehicle_type = ttk.Combobox(root, values=["Cab", "Auto"], state="readonly")
vehicle_type.current(0)
vehicle_type.pack()

tk.Button(root, text="Book Ride", command=calculate_fare, bg="lightblue").pack(pady=10)

result = tk.StringVar(value="Fare will appear here")
tk.Label(root, textvariable=result, font=("Arial", 11, "bold")).pack(pady=5)

root.mainloop()
