This bank system is designed for customers of a bank and employees of a bank.
Customers can use this bank system to create account, login and do operations.
Employees can use this system to enter administrator interface and do operations.
Caution:
1."201805250406.txt" "201805270144.txt" and "201805271052.txt" are three files of customers.
The names of the three txt files are three account numbers, pin of the first one is 123, pin of 
the second one is 1314 and pin of the third one is 2018.
2."check.txt" is for credit check when a customer opens an account. Name on the list will
not be able to open an account.
3."key.txt" is for an employee to verify his identity.
Only if the key provided by the employee matches the key in the txt, can the employee 
enter the administrator interface.
4. There are three types of accounts. When opening a new account, the type will be stored in 
the user txt file. I didn't use extends, but I show the difference of three kinds of account in methods.
5. There are some empty variables. Because "nextLine" can not use direcrly after "nextInt".
These empty variables are used to eliminate the effects of statement "nextInt".
They don't influence the stability of the system.
6. The content of user txt file (in order) 
account type(1 is saver account, 2 is junior account, 3 is current account), name, address, 
birthday, pin, account number, unclear fund, balance, last deposit time, last login time, 
state of account(true is active, false is suspend).
Running:
1. Put ALL FILES in "java sources" into a SAME path (including java files and txt files)
2. Comple ALL OF THE JAVA FILE under the path
3. Run Test.java
4. You can choose to create a new account or use the two accounts provided to 
login directly.
5. All information you provide when you create a new account will be restored in a txt file.
When you provide your name, the account number will be created according to 
the time you create the account.
6. After login, you can make 8 operations, you can enter "1" to check status of account
enter "2" to deposit money to your account or another person's account
enter "3" to withdraw money, enter "4" to suspend you account,
enter "5" to recover your account, enter "6" to change your pin,
enter "7" to close your account, enter "8" to logout.
7. You can use key provider in "key.txt" to enter administrator interface.
Then, you can enter "1" to check information of a customer, enter "2" to clear fund,
enter "3" to delete a customer, enter "4" to logout.

