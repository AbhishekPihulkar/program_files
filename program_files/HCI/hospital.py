import tkinter as tk

def register():
    name = name_entry.get()
    age = age_entry.get()
    gender = "Male" if gender_var.get() == 1 else "Female"
    dept = dept_list.get(tk.ACTIVE)
    admit = "Yes" if admit_var.get() == 1 else "No"

    info = f"Name: {name}, Age: {age}, Gender: {gender}, Dept: {dept}, Admit: {admit}\n"
    output.insert(tk.END, info)

root = tk.Tk()
root.title("Patient Registration Form")
root.geometry("400x400")

tk.Label(root, text="Patient Registration Form", font=("Arial", 14, "bold")).pack(pady=10)

tk.Label(root, text="Name:").pack()
name_entry = tk.Entry(root); name_entry.pack()

tk.Label(root, text="Age:").pack()
age_entry = tk.Entry(root); age_entry.pack()

tk.Label(root, text="Gender:").pack()
gender_var = tk.IntVar()
tk.Checkbutton(root, text="Male", variable=gender_var, onvalue=1, offvalue=0).pack()
tk.Checkbutton(root, text="Female", variable=gender_var, onvalue=0, offvalue=1).pack()

tk.Label(root, text="Department:").pack()
dept_list = tk.Listbox(root, height=4)
for d in ["Cardiology", "Neurology", "Pediatrics", "Orthopedics"]:
    dept_list.insert(tk.END, d)
dept_list.pack()

admit_var = tk.IntVar()
tk.Checkbutton(root, text="Admit Required", variable=admit_var).pack(pady=5)

tk.Button(root, text="Register", command=register, bg="lightblue").pack(pady=5)

output = tk.Text(root, height=5, width=45)
output.pack(pady=10)

root.mainloop()