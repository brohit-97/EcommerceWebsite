-- Create a new user
CREATE USER paymentdbuser WITH PASSWORD 'password';

-- Create a new database
CREATE DATABASE paymentdb;

-- Grant all privileges to the user on the database
GRANT ALL PRIVILEGES ON DATABASE paymentdb TO paymentdbuser;

-- Allow the user to login
ALTER ROLE paymentdbuser WITH LOGIN;
