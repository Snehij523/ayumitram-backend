# app.py

from dotenv import load_dotenv
import os

load_dotenv()

api_key = os.getenv("API_KEY")

import google.generativeai as genai

# Configure Gemini API




genai.configure(api_key=api_key)

model = genai.GenerativeModel("gemini-2.5-flash")

print("\nAyumitram GPT\n")

# User Inputs

disease = input("Enter Disease: ")
feeling = input("What are you feeling?: ")
weight = input("Enter Weight (kg): ")
age = input("Enter Age: ")
gender = input("Enter Gender: ")
blood_group = input("Enter Blood Group: ")
preferred_language = input("Preferred Language: ")

# Prompt Creation

prompt = f"""
You are an Ayumitram Assistant.
We will provide you with patient details,and you will analyze them to give insights based on Ayurvedic principles.
Any Invalid or missing information give error in short text saying that  "invalid entry in specific patient details". And don't give response other than the error message till all entries are valid. But if the spelling is wrong but the meaning is clear then consider it as valid entry and give response
Keep the response concise as as you can and easy to understand.
Analyze the following patient details and provide:

1. Possible Ayurvedic interpretatio
2. Basic Panchakarma suggestion
3. Diet recommendation
4. Lifestyle recommendation
5. Precautions


Patient Details:
Preferred language: {preferred_language}
Enter Specific Disease (if any): {disease}
Symptoms/Feeling: {feeling}
Weight: {weight} kg
Gender: {gender}
Age: {age}
Blood Group: {blood_group}

Keep response concise and easy to understand.
"""

# Generate Response

response = model.generate_content(prompt)

# Output

print(response.text)