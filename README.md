# Layermark Interview Task

The task was to create a backend API using Spring Boot, Rest Repositories, Spring Data JPA, and PostgreSQL that would manage a database and the relations it has.

Imagine a clinic that specialises in providing vaccines for people. A very naive view of this clinic could say that it has 3 entities: patients, doctors, and vaccines. A relation schema for the database related to the mentioned view is given below.

patient(id, name, email, birthDate, doctor_id) _id primary key, doctor_id foreign key references doctor_<br />
doctor(id, name, email) _id primary key_<br />
vaccine(id, name) _id primary key_<br />
patient_vaccinated = (patient_id, vaccine_id) _patient_id foreign key references patient, vaccine_id foreign key references vaccine_

The API firstly creates the required tables. It then provides all the following functionalities:<br />
- delete mapping to add the functionality to delete tables<br />
- post mapping to add the functionality to add a tuple to a table with appropriate error messages<br />
- put mapping to edit tuples by updatable attributes (one or more)<br />
- put mapping to edit relations between entities
