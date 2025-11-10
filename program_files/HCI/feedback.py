import tkinter as tk
from tkinter import messagebox

def submit_feedback():
    name = name_entry.get()
    food = food_entry.get()
    taste = taste_var.get()
    service = service_var.get()
    feedback = feedback_box.get("1.0", tk.END).strip()

    if not name or not food:
        messagebox.showwarning("Missing Info", "Please fill your name and food item!")
        return

    msg = f"Thank you, {name}! Feedback submitted.\nFood: {food}\nTaste: {taste}\nService: {service}"
    messagebox.showinfo("Submitted", msg)
    name_entry.delete(0, tk.END)
    food_entry.delete(0, tk.END)
    feedback_box.delete("1.0", tk.END)

root = tk.Tk()
root.title("Hotel Food Feedback Form")
root.geometry("400x400")

tk.Label(root, text="🍴 Customer Feedback Form", font=("Arial", 14, "bold")).pack(pady=10)

tk.Label(root, text="Customer Name:").pack()
name_entry = tk.Entry(root, width=35)
name_entry.pack(pady=3)

tk.Label(root, text="Food Item:").pack()
food_entry = tk.Entry(root, width=35)
food_entry.pack(pady=3)

tk.Label(root, text="Rate Taste:").pack()
taste_var = tk.StringVar(value="Good")
tk.Checkbutton(root, text="Good", variable=taste_var, onvalue="Good").pack()
tk.Checkbutton(root, text="Average", variable=taste_var, onvalue="Average").pack()
tk.Checkbutton(root, text="Poor", variable=taste_var, onvalue="Poor").pack()

tk.Label(root, text="Service Quality:").pack()
service_var = tk.StringVar(value="Satisfactory")
tk.Checkbutton(root, text="Excellent", variable=service_var, onvalue="Excellent").pack()
tk.Checkbutton(root, text="Satisfactory", variable=service_var, onvalue="Satisfactory").pack()
tk.Checkbutton(root, text="Needs Improvement", variable=service_var, onvalue="Needs Improvement").pack()

tk.Label(root, text="Additional Feedback:").pack()
feedback_box = tk.Text(root, height=4, width=35)
feedback_box.pack(pady=5)

tk.Button(root, text="Submit", command=submit_feedback, bg="lightgreen").pack(pady=10)

root.mainloop()
