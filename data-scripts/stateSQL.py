
import mysql.connector

def main():
	# Connect to the MySQL database
	mydb = mysql.connector.connect(host="localhost", user="root", password="password", database="mymedicalbillsdb")
	mycursor = mydb.cursor()
	
	sql = "INSERT state VALUES (%s, %s)"
	
	states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming']
	state_cd = ['AL', 'AK', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'FL', 'GA', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD', 'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'WA', 'WV', 'WI', 'WY']
	#file = open("D:/MyMedicalBillsAPI/data-scripts/debug.sql", 'w')
	
	for x in range(len(states)):
		val = (f"{state_cd[x]}", f"{states[x]}")
		mycursor.execute(sql, val)
		#file.write(f"INSERT state values ('{state_cd[x]}', '{states[x]}');\n")
	
	#file.close()
    
	mydb.commit()
	print(mycursor.rowcount, "record inserted.")

if __name__ == '__main__':
    main()

