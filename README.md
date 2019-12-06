# AWS-IoT-Assignment
REST APIs to perform CRUD operations on Equipment

APIs:-
   GET:- http://34.225.141.189:8181/iot-assignment/equipment/61
   GET:- http://34.225.141.189:8181/iot-assignment/equipment/search?limit=10
   POST:- http://34.225.141.189:8181/iot-assignment/equipment/
   
 SWAGGER-API:-
 	http://34.225.141.189:8181/iot-assignment/swagger-ui.html#/

1. Jar has been transfer to EC2 using FileZela desktop application.
2. Command used to run application on EC2 linux server.
    - nohup java -Xms512m -Xmx1024m -jar IoT-Assignment-1.0.jar &

3. Dummy data has equipmentId between 1 to 1000. Currently dynamoDB holds 25 records.
4. POST api call requires below json text to add new Equipment Record:
{
    "equipmentId": 1001,
    "address": {
      "country": "India",
      "state": "Madhya Pradesh",
      "streatName": "Jabalpur-Street",
      "streatNumber": "31",
      "pincode": 412231
    },
    "contractStartDate": "2019-11-11T17:14:06.495Z",
    "contractEndDate": null,
    "status": "Running"
  }
 
 
 
 
