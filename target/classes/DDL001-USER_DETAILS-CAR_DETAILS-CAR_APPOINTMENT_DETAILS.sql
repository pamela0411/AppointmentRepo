CREATE TABLE user_details(
   user_id serial PRIMARY KEY,
   username VARCHAR (50) NOT NULL,
   email VARCHAR (355) NOT NULL,
   address VARCHAR (400),
   date_of_birth DATE NOT NULL,
	CONSTRAINT username_date_of_birth_ukey UNIQUE (username, date_of_birth)       
);



CREATE TABLE car_details(
	car_id serial PRIMARY KEY,
    model_name VARCHAR (50) NOT NULL,
	model_id VARCHAR (50) UNIQUE NOT NULL,  
	user_id integer,	
     CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
      REFERENCES user_details (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION           
);



CREATE TABLE car_appointment_details(
   ticket_id serial PRIMARY KEY,
	appointment_date DATE NOT NULL,
	delivery_date DATE NOT NULL,
	assigned_to VARCHAR (50) NOT NULL,
	price bigint NOT NULL,
	status VARCHAR (50) NOT NULL,
   user_id integer,	
  CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
      REFERENCES user_details (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      car_id integer,
      CONSTRAINT car_id_fkey FOREIGN KEY (car_id)
      REFERENCES car_details (car_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
      
);