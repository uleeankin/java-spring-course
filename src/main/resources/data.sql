delete from Photo_Session_Order_Photo_Sessions;
delete from Photo_Session_Services;
delete from Photo_Session;
delete from Photo_Session_Order;
delete from Services;

insert into Services (id, name, type) values ('STLO', 'Studio', 'LOCATION');
insert into Services (id, name, type) values ('CILO', 'City', 'LOCATION');
insert into Services (id, name, type) values ('NALO', 'Nature', 'LOCATION');
insert into Services (id, name, type) values ('FAFA', 'Family', 'FASHION');
insert into Services (id, name, type) values ('CUFA', 'Customise', 'FASHION');
insert into Services (id, name, type) values ('CAFA', 'Casual', 'FASHION');
insert into Services (id, name, type) values ('POSH', 'Portrait', 'SHOT');
insert into Services (id, name, type) values ('GESH', 'General', 'SHOT');
insert into Services (id, name, type) values ('MESH', 'Medium', 'SHOT');
insert into Services (id, name, type) values ('PHVI', 'Photo', 'VIEW');
insert into Services (id, name, type) values ('VIVI', 'Video', 'VIEW');
insert into Services (id, name, type) values ('BOVI', 'Both', 'VIEW');
