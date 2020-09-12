# ProductPromotionalEngine
This is a promotional rule engine developed with Java Springboot and EasyRule










Product Promotion Engine
Authored by: Tapendu Bhowmik 








Problem Statement
We need you to implement a simple promotion engine for a checkout process. Our Cart contains a list of single character
SKU ids (A, B, C. ..) over which the promotion engine will need to run.
The promotion engine will need to calculate the total order value after applying the 2 promotion types
• buy 'n' items of a SKU for a fixed price (3 A's for 130)
• buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 )
The promotion engine should be modular to allow for more promotion types to be added at a later date (e.g. a future
promotion could be x% of a SKU unit price). For this coding exercise you can assume that the promotions will be mutually
exclusive; in other words if one is applied the other promotions will not apply


Test Setup
Unit price for SKU IDs
A 50
B 30
C 20
D 15
Active Promotions
3 of A's for 130
2 of B's for 45
C & D for 30
Scenario A
1 * A 50
1 * B 30
1 * C 20
Total 100
Scenario B
5 * A 130 + 2*50
5 * B 45 + 45 + 30
1 * C 28
Total 370
Scenario C
3 * A 130
5 * B 45 + 45 + 1 * 30
1 * C -
1 * D 30

Technical details
Programming language: Java 1.8
Framework used: Spring boot
Test Framework: Junit, Mockito
Rule Engine: Easy Rule

Sample Post Request
{
  "orderId" : 1,
  "productList" :[{"id" : "A",
                   "quantity" : 5},
                  {"id" : "B",
                   "quantity" : 3},
                  {"id" : "C",
                   "quantity" : 1},
                  {"id" : "D",
                   "quantity" : 1}]
}
Sample Output
Total price of the Cart is : 335.0

