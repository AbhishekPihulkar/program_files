import tkinter as tk
from tkinter import messagebox

def register():
    name = name_entry.get()
    age = age_entry.get()
    course = course_list.get(tk.ACTIVE)
    gender = gender_var.get()
    hostel = "Yes" if hostel_var.get() == 1 else "No"
    address = address_box.get("1.0", tk.END).strip()

    if not name or not age:
        messagebox.showwarning("Missing Info", "Please fill in all required fields!")
        return

    msg = f"Name: {name}\nAge: {age}\nGender: {gender}\nCourse: {course}\nHostel: {hostel}\nAddress: {address}"
    messagebox.showinfo("Registration Successful", msg)

root = tk.Tk()
root.title("Student Registration Form")
root.geometry("400x500")

tk.Label(root, text="Student Registration Form", font=("Arial", 14, "bold")).pack(pady=10)

tk.Label(root, text="Full Name:").pack()
name_entry = tk.Entry(root, width=35)
name_entry.pack(pady=3)

tk.Label(root, text="Age:").pack()
age_entry = tk.Entry(root, width=35)
age_entry.pack(pady=3)

tk.Label(root, text="Gender:").pack()
gender_var = tk.StringVar(value="Male")
tk.Checkbutton(root, text="Male", variable=gender_var, onvalue="Male").pack(anchor="w", padx=80)
tk.Checkbutton(root, text="Female", variable=gender_var, onvalue="Female").pack(anchor="w", padx=80)

tk.Label(root, text="Select Course:").pack()
course_list = tk.Listbox(root, height=4, exportselection=False)
for c in ["B.Tech", "B.Sc", "B.Com", "BA", "MBA"]:
    course_list.insert(tk.END, c)
course_list.pack(pady=5)

hostel_var = tk.IntVar()
tk.Checkbutton(root, text="Require Hostel Facility", variable=hostel_var).pack(pady=5)

tk.Label(root, text="Address:").pack()
address_box = tk.Text(root, height=4, width=35)
address_box.pack(pady=5)

tk.Button(root, text="Register", command=register, bg="lightgreen").pack(pady=10)

root.mainloop()
