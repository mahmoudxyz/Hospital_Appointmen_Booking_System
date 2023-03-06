#  Hospital Appointment Booking System REST API Spring Boot & H2 database

an appointment system based on REST API. With this app, patients can get appointments, the Head Physician can observe the doctors' workload, and doctors can plan their days in a better manner. It's for educational purpose. this is project created by [hyperSkill](https://hyperskill.org/projects/304linkurl)  and this is only a solution.




## API Referencereturns the information about available time slots for a doctor.

#### Create a new appointment and returns information about the appointment.

```http
  POST /setAppointment
```

#### Delete an appointment and returns the information about the deleted appointment.

```http
  Delete /deleteAppointment/?id={N}
```

#### Returns the information about all appointments.

```http
  Get /appointments
```

#### Add a new doctor and returns information about the doctor.

```http
  POST /newDoctor
```


#### Returns the information about all available doctors in the hospital.

```http
  Get /allDoctorslist
```

#### Returns the information about available time slots for a doctor.
```http
  Get /availableDatesByDoctor?doc={doctorName}
```


#### Delete a doctor and returns the information about the deleted doctor. All appointments scheduled for this doctor should be transferred to director
```http
  DELETE /deleteDoctor?doc={doctor name}
```



#### Returns the information about the number of appointments per one day.
```http
  Get /statisticsDay
```

#### Returns the information about the number of appointments per doctor.
```http
  Get /statisticsDoc
```


## 🚀 About Me
I'm a full stack developer...
I enjoy programming and problem-solving since childhood
and has pursued computer science online despite studying
clinical pharmacy. I have learned algorithms and data
structures, discrete math, calculus, linear algebra, and objectoriented programming using Java and Kotlin.

Currently I am focusing on Full-stack development using
spring and Java for backend and React for the front-end. 

I am currently Working at JetBrains academy(HyperSkill
now) I write topics about Kotlin.




## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mahmoud4dev)


