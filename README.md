# Master
Projects in Master of Information Security Technology - TU/eindhoven

Project: Financial Consultant. 
This is just a small demonstration project to implement the design of Search in Encrypted Data. 
It was implemented based on the paper: Executing SQL over Encrypted Data in the Database-Service-Provider Model
(H. Hacigumus, B. Iyer, C. Li, and S. Mehrotra) with some addition in the authentication and modification in the encryption scheme. The authentication was done using Diffie Hellman Key Exchange 1024 with AES-128 encryption. 
In this project, the private key and the public key of server, consultant and client are stored unencryted 
in the same folder as the project.
As in the real implementation, one might consider to enrypt and secure those. 
In this project, the implementation of the authentication procedure is also done in one class, 
while one might consider to separate the process between the client and the server.

The main class is Login.java (in gui package) and after choose the user credential (either client or consultant), 
user can run the other features.
