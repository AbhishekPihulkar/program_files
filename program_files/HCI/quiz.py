import tkinter as tk
from tkinter import messagebox

def submit_quiz():
    score = 0
    if q1_var.get() == 2:  
        score += 1
    if q2_var.get() == 1:  
        score += 1
    if q3_var.get() == 3: 
        score += 1

    result_box.delete("1.0", tk.END)
    result_box.insert(tk.END, f"Your Score: {score}/3\n")
    if score == 3:
        result_box.insert(tk.END, "Excellent! 🎉")
    elif score == 2:
        result_box.insert(tk.END, "Good Job!")
    else:
        result_box.insert(tk.END, "Keep Practicing!")

root = tk.Tk()
root.title("Online Quiz")
root.geometry("400x450")

tk.Label(root, text="Online Quiz", font=("Arial", 16, "bold")).pack(pady=10)

# Question 1
tk.Label(root, text="1. What is the capital of France?").pack(anchor="w")
q1_var = tk.IntVar()
tk.Checkbutton(root, text="Berlin", variable=q1_var, onvalue=1, offvalue=0).pack(anchor="w")
tk.Checkbutton(root, text="Paris", variable=q1_var, onvalue=2, offvalue=0).pack(anchor="w")
tk.Checkbutton(root, text="London", variable=q1_var, onvalue=3, offvalue=0).pack(anchor="w")

# Question 2
tk.Label(root, text="2. Largest planet in the Solar System?").pack(anchor="w", pady=(10, 0))
q2_var = tk.IntVar()
tk.Checkbutton(root, text="Jupiter", variable=q2_var, onvalue=1, offvalue=0).pack(anchor="w")
tk.Checkbutton(root, text="Earth", variable=q2_var, onvalue=2, offvalue=0).pack(anchor="w")
tk.Checkbutton(root, text="Mars", variable=q2_var, onvalue=3, offvalue=0).pack(anchor="w")

# Question 3
tk.Label(root, text="3. How many planets are there in the Solar System?").pack(anchor="w", pady=(10, 0))
q3_var = tk.IntVar()
tk.Checkbutton(root, text="7", variable=q3_var, onvalue=1, offvalue=0).pack(anchor="w")
tk.Checkbutton(root, text="9", variable=q3_var, onvalue=2, offvalue=0).pack(anchor="w")
tk.Checkbutton(root, text="8", variable=q3_var, onvalue=3, offvalue=0).pack(anchor="w")

tk.Button(root, text="Submit", bg="lightgreen", command=submit_quiz).pack(pady=10)

tk.Label(root, text="Result:").pack()
result_box = tk.Text(root, height=3, width=40)
result_box.pack()

root.mainloop()
