
import mysql.connector

def main():
	# Connect to the MySQL database
	mydb = mysql.connector.connect(host="localhost", user="root", password="password", database="mymedicalbillsdb")
	mycursor = mydb.cursor()
	
	sql = "INSERT INTO address (address, city, state_cd, zip) VALUES (%s, %s, %s, %s)"
	
	val = [
		('14 Olde Tavern Dr', 'Wendell', 'NC', '27591' ),
		('100 Main St', 'Wendell', 'NC', '27591'), 
        ('200 Maple Ave', 'Wendell', 'NC', '27591'), 
        ('300 Oak St', 'Wendell', 'NC', '27591'), 
        ('400 Pine St', 'Wendell', 'NC', '27591'), 
        ('500 Elm St', 'Wendell', 'NC', '27591'), 
        ('600 Cedar St', 'Wendell', 'NC', '27591'), 
        ('700 Birch St', 'Wendell', 'NC', '27591'), 
        ('800 Walnut St', 'Wendell', 'NC', '27591'),
        ('900 Chestnut St', 'Wendell', 'NC', '27591'), 
        ('1000 Hickory St', 'Wendell', 'NC', '27591'),
        ('635 Whitley Way', 'Wendell', 'NC', '27591')
	]
	
	#file = open("D:/MyMedicalBillsAPI/data-scripts/debug.sql", 'w')
	
	mycursor.executemany(sql, val)
	#file.write(f"INSERT state values ('{state_cd[x]}', '{states[x]}');\n")
	#file.close()
    
	mydb.commit()
	mycursor.execute("SELECT * FROM address")
	print(mycursor.rowcount, "record inserted.")
	mycursor.close

if __name__ == '__main__':
    main()

