
- ShopForHome
ShopForHome is a popular Store in the market for shopping home décor stuff. Now the store wants to move to online platforms and wants their own web application

There are 2 users on the application: -
1. User
2. Admin
User features –
1. User can login, Logout and Register into the application.
2. User can see the products in different categories.
3. User can sort the products.
4. User can add the products into the shopping cart
5. User can increase or decrease the quantity added in the cart.
6. User can add “n” number of products in the cart
7. User can get the wish list option where I can add those products which I want but don’t want to order now
8. User can get different discount coupons

Admin features –
1. Admin can login, Logout and Register into the application
2. Admin can perform CRUD (Create, Read, Update, Delete) operations on Users
3. Admin can Perform CRUD (Create, Read, Update, Delete) operations on the products
4. Admin can get the stocks
5. Admin can get the sales report of a specific duration
6. Admin can set the discount coupons for the specific set of users


The project designed as micro service architecture. It contains 3 micro services 
1.	ShopFromHomeMain – it is the main micro service contain all the operations
2.	DiscountCouponService – It is the micro service deals with the all operations of the discount coupons
3.	WishlistService – It is the micro service deals with the user wish list
All the services are registered in the Eureka server registry. All the micro services are secured token authentication and authorization.
The project is developed in java spring boot and use MySQL database to store data

The API references can be seen in the SWAGGER documentation configured in the project and the URL to see the swagger documentation are given below

DiscountCouponService - http://localhost:9091/swagger-ui/
ShopFromHomeMain- http://localhost:9092/swagger-ui/ 
WishlistService - http://localhost:9093/swagger-ui/