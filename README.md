# HospitalManagement 
Hospital Mangement is a project, made with the purpose of learning, and can enable us to have some of the hands-on on hospital related works. The backend is created with spring boot 3.x and front-end is created with Angular 12.

### FrontEnd Repo Link 
https://github.com/Nikhil-Prabhat/Hospital-Management-UI.git

## Brief
Hospital Management basically follows microservice architecture. It is having 3 mircoservices which are as follows :

- **Security Service** : This service is responsible for saving the hospital users to the system and generating the JWT token while logging into the system. The token generated will be supplied everywhere instead of the original credentials of the user, to make sure the credentials anonymity of the user. It also makes sure that the correct role is supplied to users as well.
- **Hospital Service** : This service is responsible for all the management works within the hospital. It is accountable for the doctors, patients, appointments, treatment history and bills 
related works within the system.
- **Insurance Service** : This service is responsible for post bill and insurance related works in the hospital management. It is accountable for calculating payable after the preferred insurance is chosen and get all the conclusive details of the patients. 

> Note: Three Roles have been maintained in the system respectively to confine the access of the users in the system.

```sh
ROLE_ADMIN : The user is having ADMIN access and is allowed for each and every operation in the system.
ROLE_DOCTOR : The user is having DOCTOR access and is allowed to see or perform his or her personal doctor oriented details only in the system.
ROLE_USER : The user is having USER access and is allowed to perform or see his or her patient related details only in the system.
```

## Technology And Concepts Used

- **Spring Security** : JWT token is created to login to the system for each user so that the privacy of the user may not be breached and kept intact.
- **Spring AOP** : Aspect Oriented Programming is used to handle the exceptions and logging in a better and improved way across all the microservices.
- **Open API** : OpenAPI is configured which simplifies the generation and maintenance of API docs based on the OpenApi 3 specifications for spring boot 3.x applications.
- **Postman Collection** : Postman collection is seperately maintained so as to import the endpoints collections and get started directly.
- **Feign Client** : Feign Client is used to interact with the respective microservices in the system.
- **Circuit Breaker** : Circuit Breaker is implemented so as to achieve fault tolerance in the system as there are three microservices in the system interacting with each other. Fault tolerance helps us achieve an informative or known response when the service is down via fallback method. As soon as the error is encountered, the circuit should open and for the subsequent calls, it should return the error response immediately unless the circuit is restored successfully.
- **Retry** : Spring Retry provides an ability to automatically re-invoke a failed operation, It is helpful in case when the error may be transient like a momentary network glitch.
- **Spring Validation** : Spring Validation is implemeted so as to avoid bad request as much as possible while persisting the object in our db.
- **Entity Relationship** : Entity Relationships i.e many to many, one to many, many to one and one to one , all are implemented to make the architecture sound.
- **PL/PgSQL** : Scripts are created to create the trigger in the system to automate the total bill calculation.
- **Pagination** : Pagination is implemented at the database level so as to reduce the load as much as possible.
- **Caching** : Spring Cache is used to improve the performance and minimise the round trip time from insurance servie to hospital service to get the conclusive details of the respective patient. It caches patient personal information, treatment history, appointments and bill details of the patient.
- **Spring Scheduling** : Spring Scheduling is implemented to clear the cache every minute.
- **Entity Inheritance** : Inheritance is showcased on the entity level in insurance service to segregate the parent child relationship.
- **Elastic BeanStalk** : All the three microservices are deployed to Elastic BeanStalk which internally uses Elastic Compute Cloud.
- **AWS RDS** : PostgreSQL is used as AWS RDS for the data persistence.
- **SNS** : SNS is used to notify admin for the system update when the patient is added or he/she has recovered so as to proceed with the discharge formalities.
- **S3** : The frontend code is stored on S3 and hosted from there itself.
- **Terraform** : Terraform is used as Infrastrucutre as Code (IaC) to create some of the infrastructures on AWS.
- **Payment Integration** : Razorpay is used as payment integration to enable the patients to pay their respective bill.

## UI Link to access 
http://hm-frontend-deploy.s3-website.ap-south-1.amazonaws.com

## Improvements
There are a lot of scopes of improvements here which I am planning to work on next:

- Automate the  manual tasks performed by ADMIN.
- Persist the patient claim detail as well after the payment is done.
- Work on different types of testing to ensure sanity of the application i.e Unit Testing for functional testing, Integaration Testing for end to end testing, Contract Testing and Stress Testing to perform the performance testing.
- Make UI more dynamic.
