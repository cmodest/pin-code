#   Project
##  Implement a PIN manager REST service:
The solution should provide a standalone service that provides PIN generation and validation
functionality.
##  Concepts
- MSISDN: Full length phone number. (ex. +34999112233)
- PIN: a random 4 digit numeric combination.
##  Mandatory features
- Implementation in spring-boot
- Has to provide an operation that receives an MSISDN and generates a 4-digit PINs for
that MSISDN.
- Has to allow a maximum of 3 PINs per MSISDN awaiting validation, erroring out if the
MSISDN already has 3 non-validated PINs.
- Has to provide an operation to validate a PIN for a given MSISDN
- Has to use a DB to keep its state
- Has to provide precise instructions on how to run the component
##  Bonus features
- Has to control the number of validation attempts and allow only 3 before discarding the
PIN.
- If not validated, PINs should be removed 1h after their creation
- The component can scale horizontally by deploying multiple instances
- Runs as a docker container
- Runs inside a docker-compose along with the DB

#   How to deploy
##  Compile
First of all you need to go to the pom.xml path that is located in `/microservice/pin-generator`, then run the command 

```
> mvn clean install
```

once we have compile the java project go to the next step.

##  Deploy dockers
You need to go back to the initial path of the project where you can find a docker-compose.yaml file.
There yo run the command to build the docker images with
```
> docker-compose build
```

After that you can deploy the images that can be done with the command

```
> docker-compose up
```

#   How to use
The project deploys two endpoints called 
- `api/pinCode/getPin`
- `api/pinCode/checkPin`

The `getPin` is a GET endpoint that requires a URI parameter called phoneNumber where you put the phone number that you want to register, as example: `http://localhost:8080/api/pinCode/getPin?phoneNumber=%2B34123456789`
Where you use %2B as encoding for symbol +

The `checkPin` is a POST endpoint that is able to receive a Json object with attributes `phoneNumber` and `pinCode` which will be the values that is going to check on the database to authenticate, as example of the URI: `http://localhost:8080/api/pinCode/checkPin`. 
As example of the object:

```
{"phoneNumber":"+34123456789","pinCode":"1234"}
```