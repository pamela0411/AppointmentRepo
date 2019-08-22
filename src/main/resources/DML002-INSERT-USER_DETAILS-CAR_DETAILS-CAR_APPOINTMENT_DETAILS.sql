
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Arnab','arnab0418@gmail.com','141,Furstenwall','1990-11-18');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Angela','ang@gmail.com','dusseldorf, 125','1998-05-03');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Rainer','rainer@hotmail.com','dusseldorf, 1025','1997-06-05');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Carsten','carsten@yahoo.com','dusseldorf, 1095','1996-07-02');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Peaches','peaches@abc.com','dusseldorf, 20125','1990-11-18');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Kiwi','kiwi@abc.com','dusseldorf, 20165','1990-11-04');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Sundori','sundori@nbv.com','dusseldorf, 1426','1998-11-04');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Leapy','leapy@ghb.com','dusseldorf, 14226','2002-02-12');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Rishaan','rishaan@ghjn.com','dusseldorf, 1425','2002-02-21');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Andy','andy@ghy.com','dusseldorf, 14215','2001-02-21');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('Sandy','sandy@tyyu.com','dusseldorf, 3215','2000-05-21');
INSERT INTO user_details( username, email, address, date_of_birth)  VALUES('DEFAULT','DEFAULT','DEFAULT','2000-01-01');

UPDATE user_details
SET user_id = 0
WHERE username = 'DEFAULT';

INSERT into car_details(model_name, model_id, user_id) VALUES ('Mercedes-Benz','X1234C', (SELECT user_id FROM user_details WHERE username = 'Arnab'));
INSERT into car_details(model_name, model_id, user_id) VALUES ('BMW','X123BC', (SELECT user_id FROM user_details WHERE username = 'Angela'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('HONDA AMAZE','DUSS8907',(SELECT user_id FROM user_details WHERE username = 'Angela'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('HONDA AMAZE','DUSS8917',(SELECT user_id FROM user_details WHERE username = 'Arnab'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('MERCEDES - BENZ','DUSS7652',(SELECT user_id FROM user_details WHERE username = 'Rainer'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('FORD I10','SW7646',(SELECT user_id FROM user_details WHERE username = 'Angela'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('HONDA CITY','TGH2789',(SELECT user_id FROM user_details WHERE username = 'Carsten'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('AUDI','THJ6754',(SELECT user_id FROM user_details WHERE username = 'Kiwi'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('BMW','ERF8907',(SELECT user_id FROM user_details WHERE username = 'Sundori'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('MERCEDES - BENZ','WUI4963',(SELECT user_id FROM user_details WHERE username = 'Kiwi'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('HONDA AMAZE','QIU7634',(SELECT user_id FROM user_details WHERE username = 'Sundori'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('FORD I20','PON9427',(SELECT user_id FROM user_details WHERE username = 'Sundori'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('SANTRO SPORTZ','FPI9031',(SELECT user_id FROM user_details WHERE username = 'Sandy'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('MERCEDES - BENZ','UIT6543',(SELECT user_id FROM user_details WHERE username = 'Peaches'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('FORD I10','OPI6798',(SELECT user_id FROM user_details WHERE username = 'Andy'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('HONDA CITY','ERT1243',(SELECT user_id FROM user_details WHERE username = 'Sandy'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('FORD I20','VBN7638',(SELECT user_id FROM user_details WHERE username = 'Angela'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('SANTRO SPORTZ','KJGH6787',(SELECT user_id FROM user_details WHERE username = 'Leapy'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('AUDI','TGB5690',(SELECT user_id FROM user_details WHERE username = 'Rainer'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('BMW','PIM0411',(SELECT user_id FROM user_details WHERE username = 'Leapy'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('MERCEDES - BENZ','HYN8912',(SELECT user_id FROM user_details WHERE username = 'Rishaan'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('SUZUKI DEZIRE','TGF2321',(SELECT user_id FROM user_details WHERE username = 'Rainer'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('HONDA CITY','TGBH9867',(SELECT user_id FROM user_details WHERE username = 'Rishaan'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('AUDI','FGH4090',(SELECT user_id FROM user_details WHERE username = 'Andy'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('AUDI','TED9054',(SELECT user_id FROM user_details WHERE username = 'Peaches'));
INSERT INTO car_details (model_name, model_id, user_id) VALUES ('DEFAULT','DEFAULT',(SELECT user_id FROM user_details WHERE username = 'DEFAULT'));

UPDATE car_details
SET car_id = 0
WHERE model_name = 'DEFAULT';

INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-21','2019-08-22','John',200,'NEW',(SELECT user_id FROM user_details WHERE username = 'Peaches'),(SELECT car_id FROM car_details WHERE model_id = 'UIT6543'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-15','2019-08-17','David',500,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Andy'),(SELECT car_id FROM car_details WHERE model_id = 'OPI6798'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-15','2019-08-19','Aman',200,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Rishaan'),(SELECT car_id FROM car_details WHERE model_id = 'HYN8912'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-16','2019-08-17','Sayon',100,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Rainer'),(SELECT car_id FROM car_details WHERE model_id = 'DUSS7652'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-16','2019-08-17','John',250,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Rishaan'),(SELECT car_id FROM car_details WHERE model_id = 'TGBH9867'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-17','2019-08-19','Jack',350,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Leapy'),(SELECT car_id FROM car_details WHERE model_id = 'KJGH6787'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-17','2019-08-19','Ammy',654,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Rainer'),(SELECT car_id FROM car_details WHERE model_id = 'TGB5690'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-17','2019-08-18','Sheldon',231,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Leapy'),(SELECT car_id FROM car_details WHERE model_id = 'PIM0411'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-17','2019-08-19','Sheldon',298,'COMPLETED',(SELECT user_id FROM user_details WHERE username = 'Angela'),(SELECT car_id FROM car_details WHERE model_id = 'X123BC'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-19','2019-08-21','Penny',213,'IN PROGRESS',(SELECT user_id FROM user_details WHERE username = 'Sandy'),(SELECT car_id FROM car_details WHERE model_id = 'FPI9031'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-19','2019-08-12','Raj',100,'IN PROGRESS',(SELECT user_id FROM user_details WHERE username = 'Andy'),(SELECT car_id FROM car_details WHERE model_id = 'FGH4090'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-19','2019-08-25','Jack',300,'IN PROGRESS',(SELECT user_id FROM user_details WHERE username = 'Sundori'),(SELECT car_id FROM car_details WHERE model_id = 'QIU7634'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-19','2019-08-22','John',412,'IN PROGRESS',(SELECT user_id FROM user_details WHERE username = 'Kiwi'),(SELECT car_id FROM car_details WHERE model_id = 'THJ6754'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-20','2019-08-23','Pam',90,'IN PROGRESS',(SELECT user_id FROM user_details WHERE username = 'Carsten'),(SELECT car_id FROM car_details WHERE model_id = 'TGH2789'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-20','2019-08-22','Ammy',150,'IN PROGRESS',(SELECT user_id FROM user_details WHERE username = 'Angela'),(SELECT car_id FROM car_details WHERE model_id = 'VBN7638'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-21','2019-08-23','David',234,'NEW',(SELECT user_id FROM user_details WHERE username = 'Arnab'),(SELECT car_id FROM car_details WHERE model_id = 'DUSS8917'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-21','2019-08-24','Sayon',123,'NEW',(SELECT user_id FROM user_details WHERE username = 'Rainer'),(SELECT car_id FROM car_details WHERE model_id = 'TGF2321'));
INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) VALUES('2019-08-21','2019-08-22','Jack',543,'NEW',(SELECT user_id FROM user_details WHERE username = 'Sandy'),(SELECT car_id FROM car_details WHERE model_id = 'ERT1243'));
