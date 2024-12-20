# Ecommerce Website

## Overview
This repository contains the microservices for an Ecommerce Website. The application includes functionalities for user management, product catalog, cart & checkout, order management, and payment processing.

## Functional Requirements

### 1. User Service
1.1. **Registration**: Allow new users to create an account using their email or social media profiles.  
1.2. **Login**: Users should be able to securely log in using their credentials.  
1.3. **Profile Management**: Users should have the ability to view and modify their profile details.  
1.4. **Password Reset**: Users must have the option to reset their password through a secure link.

### 2. Product Service
2.1. **Browsing**: Users should be able to browse products by different categories.  
2.2. **Product Details**: Detailed product pages with product images, descriptions, specifications, and other relevant information.  
2.3. **Search**: Users must be able to search for products using keywords.

### 3. Cart Serivce
3.1. **Add to Cart**: Users should be able to add products to their cart.  
3.2. **Cart Review**: View selected items in the cart with price, quantity, and total details.  
3.3. **Checkout**: Seamless process to finalize the purchase, including specifying delivery address and payment method.

### 4. Order Service
4.1. **Order Confirmation**: After making a purchase, users should receive a confirmation with order details.  
4.2. **Order History**: Users should be able to view their past orders.  
4.3. **Order Tracking**: Provide users with a way to track their order's delivery status.

### 5. Payment Service
5.1. **Multiple Payment Options**: Support for credit/debit cards, online banking, and other popular payment methods.  
5.2. **Secure Transactions**: Ensure user trust by facilitating secure payment transactions.  
5.3. **Payment Receipt**: Provide users with a receipt after a successful payment.

### 6. Authentication Service
6.1. **Secure Authentication**: Ensure that user data remains private and secure during login and throughout their session.  
6.2. **Session Management**: Users should remain logged in for a specified duration or until they decide to log out.

## Microservices
- **User Service**: Manages user registration, login, profile management, and password reset.
- **Product Service**: Handles product catalog, browsing, and search functionalities.
- **Cart Service**: Manages the shopping cart and checkout process.
- **Order Service**: Handles order confirmation, history, and tracking.
- **Payment Service**: Manages payment processing and secure transactions.

## Technologies Used
- Java
- Spring Boot
- Maven
- PostgreSQL
- Kafka
- Redis
- OAuth
- JWT (JSON Web Tokens)
- Stripe API
- Razorpay API

## Running the Application
1. Clone the repository.
2. Navigate to the project directory.
3. Ensure you have the necessary services (e.g., databases, message brokers) running.
4. Update the configuration files with your settings.
5. Run each microservice using Maven:
   ```sh
   mvn spring-boot:run