#Question 1
from itertools import count
requiredCourses = ["DSCI5210","DSCI5240","DSCI5340","BCIS5420","BCIS5700","DSCI5260"]
electvieCourses = ["DSCI5180","DSCI5250","DSCI5320","DSCI5330","DSCI5350","DSCI5360","BCIS5110","BCIS5120","BCIS5130","BCIS5140","BCIS5150","BCIS5610","BCIS5630","BCIS5660","BCIS5670","BCIS5680","BCIS5740","BCIS5750","DSCI5800"]
course = input("Enter Course Number: ")
if requiredCourses.count(course) >= 1:   #CASE For Required Course
    print("Required course")
if electvieCourses.count(course) >= 1:   #CASE for Elective Course
    print("Elective  course")
if requiredCourses.count(course) == 0 and electvieCourses.count(course) == 0 : ##Not Electve and not required
    print("Course not listed for MS BUAN")


# 2.Total price of the Goods
print ("---------Running Second Program --------")

couponsList = ["Spring2022","UNT2022","ITDS2022"]
subtotal = int(input("Enter the subtotal: "))
dededeif couponsList.count(coupon) == 1:
    discount = (subtotal*5)/100
    tax = (subtotal*10)/100
    totalPrice = subtotal+tax-discount
    print("Subtotal = $"+str(subtotal))
    print("10% Tax on Subtotal = $" + str(tax))
    print("5% Discount on Subtotal = $" + str(discount))
    print ("Total Price (subtotal+tax-discount)= $ " + str(totalPrice))
else :
    discount = 0
    tax = (subtotal*10)/100
    totalPrice = subtotal+tax-discount
    print("Subtotal = $"+str(subtotal))
    print("10% Tax on Subtotal = $" + str(tax))
    print("0% Discount on Subtotal = $" + str(discount))
    print ("Total Price (subtotal+tax-discount)= $ " + str(totalPrice))


print ("---------Running Third Program --------")
#3.Disney Tickets
ages = input("Enter the Ages: ")
groupages = ages.split(";")
isFloridaResident = input("FL resident?")
subtotal = 0
flDisCount = 0
for age in groupages:
    if int(age) >= 10:
        subtotal+=149
    if int(age) >= 3 and int(age) <= 9:
        subtotal+=135
    else:
        subtotal+=0

if isFloridaResident == 'Y':
    flDisCount = (subtotal*10.00)/100

tax = (subtotal*10.00)/100

total = subtotal+tax-flDisCount

print("Subtotal :$"+str(subtotal))
print("Tax :$"+str(tax))
print("FL Discount :$"+str(flDisCount))
print("Total :$"+str(total))

#####Input#####
# Enter the Ages: 2;5;22;25;0
# FL resident?Y

#####OutPut ######
# Subtotal :$433
# Tax :$43.3
# FL Discount :$43.3
# Total :$433.0


print ("---------Running Fourth Program --------")
#4.Cashback of User
dinning = int(input("Enter the Total Expenses in Dining: "))
travel = int(input("Enter the Total Expenses in Travel: "))
groceries = int(input("Enter the Total Expenses in Groceries: "))
leisure = int(input("Enter the Total Expenses in Leisure: "))

dinningCashBack = (dinning*4.00)/100
travelCashBack = (travel*3.00)/100
groceriesCashBack = (groceries*2.00)/100
leisureCashBack = (leisure*1.00)/100

totalCashBack = dinningCashBack + travelCashBack + groceriesCashBack + leisureCashBack

print("Total Cashback = $"+str(totalCashBack))

print ("---------Running Fifth Program --------")

#5. Monthly Charges of Electricity

usedElectricityInKw = int(input("Enter Electricity usage in kWh "))

subtotal = 0
tax = 0
if usedElectricityInKw >= 0 and usedElectricityInKw <= 500:
    subtotal = (usedElectricityInKw * 10.00)/100
    tax = (subtotal*10.00)/100

if usedElectricityInKw > 500 and usedElectricityInKw <= 1000:
    subtotal = (500 * 10.00)/100
    subtotal+= ((usedElectricityInKw-500) * 14.00)/100
    tax = (subtotal*10.00)/100

if usedElectricityInKw > 1000 :
    subtotal = (500 * 10.00)/100
    subtotal+= ((500) * 14.00)/100
    subtotal+= ((usedElectricityInKw-1000) * 20.00)/100
    tax = (subtotal*10.00)/100

total = subtotal+tax
print("Subtotal: $"+str(subtotal))
print("10% tax on subtotal =  $"+str(tax))
print("total charge: $"+str(total))
