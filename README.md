# Layermark Interview Task

The task was to create a backend API using Spring Boot, Rest Repositories, Spring Data JPA, and PostgreSQL that would manage a database and the relations it has.

Imagine a clinic that specialises in providing vaccines for people. A very naive view of this clinic could say that it has 3 entities: patients, doctors, and vaccines. A relation schema for the database related to the mentioned view is given below.

patient(<ins>id</ins>, name, email, birthDate, doctor_id) _doctor_id foreign key references doctor_<br />
doctor(<ins>id</ins>, name, email)<br />
vaccine(<ins>id</ins>, name, doctor_id) _doctor_id foreign key references doctor_<br />
patient_vaccinated = (<ins>patient_id, vaccine_id</ins>) _patient_id foreign key references patient, vaccine_id foreign key references vaccine_

The list of relationships in the API is provided below:<br />
- Patient -> Doctor (ManyToOne) resolved on the many side (patient) by adding the primary key of the one side (doctor)<br />
- Vaccine -> Doctor (ManyToOne) resolved on the many side (vaccine) by adding the primary key of the one side (doctor)<br />
- Patient -- Vaccine (ManyToMany) resolved by creating an extra table (patient_vaccinated)

The API firstly creates the required tables. It then provides all the following functionalities:<br />
- delete mapping to add the functionality to delete tables<br />
- post mapping to add the functionality to add a tuple to a table with appropriate error messages<br />
- put mapping to edit tuples by updatable attributes (one or more)<br />
- put mapping to edit relations between entities
