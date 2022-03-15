drop table if exists empcourse;
drop table if exists jobhistory;
drop table if exists employee;
drop table if exists department;
drop table if exists course;


create table course(
        courseno integer primary key,
        cname varchar(30),
        cdate date
) ENGINE=InnoDB;

create table department(
        depno integer primary key,
        dname varchar(20),
        location varchar(15),
        head integer
)ENGINE=InnoDB;

create table employee(
        empno integer primary key,
        surname varchar(15),
        forenames varchar(30),
        dob date,
        address varchar(50),
        telno varchar(20),
        depno integer,
        constraint foreign key (depno) references department (depno)
)ENGINE=InnoDB;

create table jobhistory(empno integer,
                        position varchar(30),
                        startdate date,
                        enddate date,
                        salary  decimal(8,2),
                        primary key (empno, position),
                        constraint foreign key (empno) references employee (empno)
)ENGINE=InnoDB;

create table empcourse(empno integer,
                        courseno integer,
                        primary key (empno, courseno),
                        constraint foreign key (empno) references employee (empno),
                        constraint foreign key (courseno) references course (courseno)
)ENGINE=InnoDB;

insert into course (courseno, cname, cdate)
       values ( 1, 'Basic Accounting',str_to_date('11-JAN-1989', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 2, 'Further Accounting',str_to_date('25-JAN-1989', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 3,'Issues In Administration',str_to_date('27-SEP-1988', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 4,'More Administration',str_to_date('16-OCT-1988', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 5,'Ada',str_to_date('01-NOV-1988', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 6, 'Introduction To Ingres',str_to_date('05-FEB-1989', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 7, 'New Topologies',str_to_date('10-MAR-1989', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 8,'LANs',str_to_date('12-DEC-1988', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 9,'Structural Stress Analysis',str_to_date('20-FEB-1989', '%e-%b-%Y'));
insert into course (courseno, cname, cdate)
       values ( 10,'Taffic Flow Analysis',str_to_date('24-OCT-1988', '%e-%b-%Y'));

insert into department (depno, dname, location, head)
       values (1,'accounts','floor 3',1);
insert into department (depno, dname, location, head)
       values (2, 'administration', 'floor 2', 1);
insert into department (depno, dname, location, head)
       values (3, 'software design', 'floor 1', 2);
insert into department (depno, dname, location, head)
       values (4, 'communications', 'floor 4', 3);
insert into department (depno, dname, location, head)
       values (5, 'engineering', 'floor 5', 7);

insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (1,'Jones','Elizabeth Barbara',str_to_date('05-JAN-1944', '%e-%b-%Y'),
       '26 Agnews Terrace, Shamrock Bay','212 337 2288',1);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (2,'Smith','Robert', str_to_date('07-FEB-1947', '%e-%b-%Y'),
       '18 Marsh Street, Tollcross, Edinburgh','031 732 8972', 1);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (3,'White','Allan',str_to_date('05-MAY-1961', '%e-%b-%Y'),
       '6 Remote Place, North Berwick','121 555 6622', 1);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (4,'Reid','Gordon', str_to_date('10-AUG-1963', '%e-%b-%Y'),
       '9 Noble Road, Penicuik', '629 424 6713', 1);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (5,'MacCallan','Claire', str_to_date('18-SEP-1958', '%e-%b-%Y'),
       '25 Crisis Avenue, Leith, Edinburgh', '031 337 4166',1);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (6,'Murphy','Brian Charles', str_to_date('30-JUN-1954', '%e-%b-%Y'),
       '9 Roberts Street, Biggar', '331 229 4147', 1);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (7,'Gibson','James', str_to_date('09-MAR-1948', '%e-%b-%Y'),
       '11 Depressed Way, Glasgow', '041 447 8001', 2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (8,'Andrews','John', str_to_date('02-JAN-1958', '%e-%b-%Y'),
       '73 Long Road, Lengthitown', '70 229 7213', 2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (9,'Wright','Audrey Mary', str_to_date('02-JAN-1958', '%e-%b-%Y'),
       '10 Nile Terrace, Polwarth, Edinburgh', '031 424 7092', 2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (10,'Reagan','Anne', str_to_date('17-AUG-1961', '%e-%b-%Y'),
       '82 Longstone Road, Longstone, Edinburgh', '031 111 2799',2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (11,'North','Annabel', str_to_date('01-SEP-1962', '%e-%b-%Y'),
       '35 Marchmont Terrace, Marchmont, Edinburgh', '031 447 2266', 2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (12,'South','Todd James',str_to_date('28-FEB-1959', '%e-%b-%Y'),
       '10 Shandon Road, Merchiston, Edinburgh', '031 333 1008', 2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (13,'East','Ian',str_to_date('13-MAY-1942', '%e-%b-%Y'),
       '47 Colinton Road, Craighlochart, Edinburgh', '031 424 5665', 2);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (14,'West','Jack', str_to_date('15-JUN-1946', '%e-%b-%Y'),
       '15 South West Gardens, Peebles','466 3176', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (15,'Allen','Ester',str_to_date('27-FEB-1955', '%e-%b-%Y'),
       '10 Troon Street, Leith, Edinburgh', '031 424 2907', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (16,'Brunell','Liza', str_to_date('18-AUG-1962', '%e-%b-%Y'),
       '8 Commercial Street, Tollcross, Edinburgh','031 424 1656', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (17,'Robertson','David Andrew', str_to_date('07-AUG-1960', '%e-%b-%Y'),
       '9 North Loan,South Queensferry', '031 447 8213', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (18,'Robinson','Allan',str_to_date('09-JUN-1961', '%e-%b-%Y'),
       '31 Newall Terrace, Hawick','229 0854', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (19,'Roberts','Robert', str_to_date('01-JAN-1959', '%e-%b-%Y'),
       '7 Peebles Road,Melrose','402 9213', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (20,'Murray','James', str_to_date('06-MAR-1964', '%e-%b-%Y'),
       '40 Blackhall Loan,Biggar','121 444 4580', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (21,'Bryce','Anne', str_to_date('13-SEP-1965', '%e-%b-%Y'),
       '22 Forresthill Place, Greyfriars, Edinburgh','031 402 6666', 3);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (22,'Cowan','Audrey', str_to_date('03-APR-1940', '%e-%b-%Y'),
       '12 Down Street, Brayend', '228 9321', 4);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (23,'Stevenson','John David', str_to_date('12-DEC-1954', '%e-%b-%Y'),
       '16 Rubber Road,Stampingham', '337 6262', 4);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (24,'Dickson','William', str_to_date('06-JAN-1950', '%e-%b-%Y'),
       '11 Newplace Road, Newton', '041 444 6730', 4);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (25,'Brownlie','Matthew', str_to_date('14-MAY-1954', '%e-%b-%Y'),
       '80 Cowan Road, Shandon, Edinburgh', '031 228 4141', 4);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (26,'Gordon','Mark',str_to_date('12-FEB-1960', '%e-%b-%Y'),
       '10 Whyte Place, North Berwick','424 1024', 4);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (27,'Kennedy','Luke',str_to_date('26-JUL-1952', '%e-%b-%Y'),
       '14 Bucket Street, Musselburgh','031 414 7312', 5);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (28,'Grant','Lynn',str_to_date('12-JUL-1956', '%e-%b-%Y'),
       '3 Dey Terrace, Selkirk', '337 8911', 5);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (29,'MacDonald','Elizabeth', str_to_date('03-SEP-1968', '%e-%b-%Y'),
       '23 Neidpath Road, Peebles', '444 8998',5);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (30,'MacDugle','Shirley', str_to_date('09-OCT-1964', '%e-%b-%Y'),
       '8 Rosebank Terrace, Shandon, Edinburgh', '031 447 1189', 5);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (31,'Douglas','Chris', str_to_date('23-JUN-1955', '%e-%b-%Y'),
       '19 High Street, Livingston', '031 229 6792', 5);
insert into employee (empno, surname, forenames, dob, address, telno, depno)
       values (32,'Christie','Malcolm', str_to_date('25-JUL-1959', '%e-%b-%Y'),
       '11 Rounders Park, Polwarth, Edinburgh','031 424 8406', 5);

insert into empcourse(empno, courseno) values (1, 1);
insert into empcourse(empno, courseno) values (1, 2);
insert into empcourse(empno, courseno) values (2, 1);
insert into empcourse(empno, courseno) values (2, 2);
insert into empcourse(empno, courseno) values (7, 3);
insert into empcourse(empno, courseno) values (7, 4);
insert into empcourse(empno, courseno) values (8, 3);
insert into empcourse(empno, courseno) values (8, 4);
insert into empcourse(empno, courseno) values (14, 5);
insert into empcourse(empno, courseno) values (14, 6);
insert into empcourse(empno, courseno) values (15, 5);
insert into empcourse(empno, courseno) values (15, 6);
insert into empcourse(empno, courseno) values (21, 7);
insert into empcourse(empno, courseno) values (22, 7);
insert into empcourse(empno, courseno) values (22, 8);
insert into empcourse(empno, courseno) values (19, 5);
insert into empcourse(empno, courseno) values (19, 6);

insert into jobhistory (empno, position, startdate, enddate, salary)
       values (1, 'Accounts Manager',
       str_to_date('12-JAN-1976', '%e-%b-%Y'), NULL, 30000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (1, 'Assistant Accounts Manager',
       str_to_date('11-FEB-1972', '%e-%b-%Y'),
       str_to_date('12-JAN-1976', '%e-%b-%Y'),22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (1, 'Accountant',
       str_to_date('10-MAR-1968', '%e-%b-%Y'),
       str_to_date('11-FEB-1972', '%e-%b-%Y'),  15000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (1, 'Junior Accountant',
       str_to_date('09-APR-1964', '%e-%b-%Y'),
       str_to_date('10-MAR-1968', '%e-%b-%Y'), 6000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (2, 'Assistant Accounts Manager',
       str_to_date('08-MAY-1976', '%e-%b-%Y'), NULL, 25000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (2, 'Accountant',
       str_to_date('07-JUN-1971', '%e-%b-%Y'),
       str_to_date('08-MAY-1976', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (2,  'Junior Accountant',
       str_to_date('06-JUL-1967', '%e-%b-%Y'),
       str_to_date('07-JUN-1971', '%e-%b-%Y'), 8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (3, 'Accountant',
       str_to_date('05-AUG-1984', '%e-%b-%Y'), NULL, 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (3, 'Junior Accountant',
       str_to_date('04-SEP-1981', '%e-%b-%Y'),
       str_to_date('05-AUG-1984', '%e-%b-%Y'), 8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (4, 'Accountant',
       str_to_date('05-OCT-1989', '%e-%b-%Y'), NULL, 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (5,'Accountant',
       str_to_date('02-NOV-1980', '%e-%b-%Y'),NULL, 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (5, 'Junior Accountant',
       str_to_date('01-DEC-1978', '%e-%b-%Y'),
       str_to_date('02-NOV-1980', '%e-%b-%Y'), 8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (6, 'Accountant',
       str_to_date('12-JAN-1980', '%e-%b-%Y'), NULL, 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (6, 'Junior Accountant',
       str_to_date('11-FEB-1974', '%e-%b-%Y'),
       str_to_date('12-JAN-1980', '%e-%b-%Y'), 8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (7, 'Admin Manager',
       str_to_date('10-MAR-1980', '%e-%b-%Y'),NULL, 30000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (7, 'Assistant Admin Manager',
       str_to_date('09-APR-1974', '%e-%b-%Y'),
       str_to_date('10-MAR-1980', '%e-%b-%Y'),22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (7, 'Senior Admin Assistant',
       str_to_date('01-DEC-1968', '%e-%b-%Y'),
       str_to_date('09-APR-1974', '%e-%b-%Y'),16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (7, 'Admin Assistant',
       str_to_date('07-MAY-1960', '%e-%b-%Y'),
       str_to_date('01-DEC-1968', '%e-%b-%Y'), 10000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (8, 'Assistant Admin Manager',
       str_to_date('10-MAR-1980', '%e-%b-%Y'), NULL, 20000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (8, 'Senior Admin Assistant',
       str_to_date('09-APR-1977', '%e-%b-%Y'),
       str_to_date('10-MAR-1980', '%e-%b-%Y'),12000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (8, 'Admin Assistant',
       str_to_date('01-DEC-1976', '%e-%b-%Y'),
       str_to_date('09-APR-1977', '%e-%b-%Y'), 8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (9, 'Senior Admin Assistant',
       str_to_date('10-JUL-1988', '%e-%b-%Y'), NULL, 14000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (9, 'Admin Assistant',
       str_to_date('08-JUN-1982', '%e-%b-%Y'),
       str_to_date('10-JUL-1988', '%e-%b-%Y'),  10000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (10,'Admin Assistant',
       str_to_date('06-SEP-1986', '%e-%b-%Y'), NULL, 12000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (10,'Junior Admin Assistant',
       str_to_date('22-SEP-1983', '%e-%b-%Y'),
       str_to_date('06-SEP-1986', '%e-%b-%Y'),6000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (11,'Admin Assistant',
       str_to_date('13-MAR-1982', '%e-%b-%Y'), NULL, 15000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (11,'Junior Admin Assistant',
       str_to_date('23-OCT-1980', '%e-%b-%Y'),
       str_to_date('13-MAR-1982', '%e-%b-%Y'),8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (12,'Admin Assistant',
       str_to_date('26-FEB-1982', '%e-%b-%Y'), NULL, 15000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (12,'Junior Admin Assistant',
       str_to_date('03-JAN-1977', '%e-%b-%Y'),
       str_to_date('26-FEB-1982', '%e-%b-%Y'), 6500.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (13,'Admin Assistant',
       str_to_date('17-APR-1988', '%e-%b-%Y'), NULL, 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (14,'Admin Assistant',
       str_to_date('14-NOV-1985', '%e-%b-%Y'), NULL, 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (15,'Senior Systems Analyst',
       str_to_date('21-AUG-1988', '%e-%b-%Y'), NULL, 35000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (15,'Systems Analyst',
       str_to_date('09-FEB-1986', '%e-%b-%Y'),
       str_to_date('21-AUG-1988', '%e-%b-%Y'), 31000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (15,'Analyst Programmer',
       str_to_date('17-JAN-1984', '%e-%b-%Y'),
       str_to_date('09-FEB-1986', '%e-%b-%Y'), 25000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (15, 'Programmer',
       str_to_date('13-MAY-1980', '%e-%b-%Y'),
       str_to_date('17-JAN-1984', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (16,'Systems Analyst',
       str_to_date('25-APR-1991', '%e-%b-%Y'), NULL, 30000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (16,'Analyst Programmer',
       str_to_date('16-NOV-1986', '%e-%b-%Y'),
       str_to_date('25-APR-1991', '%e-%b-%Y'),21000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (16,'Programmer',
       str_to_date('01-OCT-1984', '%e-%b-%Y'),
       str_to_date('16-NOV-1986', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (17,'Analyst Programmer',
       str_to_date('17-JAN-1988', '%e-%b-%Y'), NULL,  21000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (17,'Programmer',
       str_to_date('01-OCT-1984', '%e-%b-%Y'),
       str_to_date('17-JAN-1988', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (18,'Analyst Programmer',
       str_to_date('23-SEP-1988', '%e-%b-%Y'), NULL,  22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (18,'Programmer',
       str_to_date('01-OCT-1984', '%e-%b-%Y'),
       str_to_date('23-SEP-1988', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (19,'Analyst Programmer',
       str_to_date('23-SEP-1988', '%e-%b-%Y'), NULL, 22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (19,'Programmer',
       str_to_date('01-OCT-1984', '%e-%b-%Y'),
       str_to_date('23-SEP-1988', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (20,'Analyst Programmer',
       str_to_date('23-SEP-1988', '%e-%b-%Y'), NULL,  22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (20,'Programmer',
       str_to_date('01-OCT-1984', '%e-%b-%Y'),
       str_to_date('23-SEP-1988', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (21,'Analyst Programmer',
       str_to_date('13-DEC-1991', '%e-%b-%Y'), NULL, 25000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (21,'Programmer',
       str_to_date('11-SEP-1989', '%e-%b-%Y'),
       str_to_date('13-DEC-1991', '%e-%b-%Y'), 21000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (22,'Communications Manager',
       str_to_date('03-AUG-1985', '%e-%b-%Y'), NULL, 36000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (22,'Senior Communications Engineer',
       str_to_date('21-JUN-1977', '%e-%b-%Y'),
       str_to_date('03-AUG-1985', '%e-%b-%Y'), 26000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (22,'Communications Engineer',
       str_to_date('13-MAY-1975', '%e-%b-%Y'),
       str_to_date('21-JUN-1977', '%e-%b-%Y'), 12000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (22,'Electrical Engineer',
       str_to_date('08-FEB-1966', '%e-%b-%Y'),
       str_to_date('13-MAY-1975', '%e-%b-%Y'), 8000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (23,'Senior Communications Engineer',
       str_to_date('03-AUG-1985', '%e-%b-%Y'), NULL, 18000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (23,'Communications Engineer',
       str_to_date('21-JUN-1979', '%e-%b-%Y'),
       str_to_date('03-AUG-1985', '%e-%b-%Y'),9000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (23,'Software Engineer',
       str_to_date('14-FEB-1977', '%e-%b-%Y'),
       str_to_date('21-JUN-1979', '%e-%b-%Y'), 5000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (24,'Communications Engineer',
       str_to_date('08-OCT-1985', '%e-%b-%Y'), NULL, 15000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (24,'Electrical Engineer',
       str_to_date('06-MAY-1980', '%e-%b-%Y'),
       str_to_date('08-OCT-1985', '%e-%b-%Y'),13000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (25,'Communications Engineer',
       str_to_date('01-OCT-1988', '%e-%b-%Y'), NULL, 19000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (25,'Software Engineer',
       str_to_date('25-AUG-1985', '%e-%b-%Y'),
       str_to_date('01-OCT-1988', '%e-%b-%Y'), 16000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (25,'Systems Programmer',
       str_to_date('29-JAN-1983', '%e-%b-%Y'),
       str_to_date('25-AUG-1985', '%e-%b-%Y'), 11000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (25,'Programmer',
       str_to_date('14-SEP-1980', '%e-%b-%Y'),
       str_to_date('29-JAN-1983', '%e-%b-%Y'), 9000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (26,'Communications Engineer',
       str_to_date('13-MAR-1988', '%e-%b-%Y'), NULL, 19500.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (26,'Software Engineer',
       str_to_date('30-AUG-1985', '%e-%b-%Y'),
       str_to_date('13-MAR-1988', '%e-%b-%Y'), 17000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (26,'Trainee Engineer',
       str_to_date('13-SEP-1982', '%e-%b-%Y'),
       str_to_date('30-AUG-1985', '%e-%b-%Y'), 11000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (26,'Electronic Engineer',
       str_to_date('01-SEP-1980', '%e-%b-%Y'),
       str_to_date('13-SEP-1982', '%e-%b-%Y'), 9000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (27,'Senior Chief Engineer',
       str_to_date('01-NOV-1990', '%e-%b-%Y'), NULL, 25000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (27,'Chief Engineer',
       str_to_date('20-JUN-1986', '%e-%b-%Y'),
       str_to_date('01-NOV-1990', '%e-%b-%Y'), 17000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (27,'Mechanical Engineer',
       str_to_date('19-APR-1981', '%e-%b-%Y'),
       str_to_date('20-JUN-1986', '%e-%b-%Y'),11000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (27,'Graduate Engineer',
       str_to_date('13-SEP-1975', '%e-%b-%Y'),
       str_to_date('19-APR-1981', '%e-%b-%Y'), 6000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (28,'Chief Engineer',
       str_to_date('06-JUL-1989', '%e-%b-%Y'), NULL, 29000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (28,'Civil Engineer',
       str_to_date('17-APR-1984', '%e-%b-%Y'),
       str_to_date('06-JUL-1989', '%e-%b-%Y'), 17000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (28,'Trainee Engineer',
       str_to_date('10-DEC-1978', '%e-%b-%Y'),
       str_to_date('17-APR-1984', '%e-%b-%Y'), 12000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (28,'Graduate Engineer',
       str_to_date('01-SEP-1978', '%e-%b-%Y'),
       str_to_date('10-DEC-1978', '%e-%b-%Y'), 3000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (29,'Electrical Engineer',
       str_to_date('09-FEB-1986', '%e-%b-%Y'), NULL, 17000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (29,'Graduate Engineer',
       str_to_date('03-OCT-1980', '%e-%b-%Y'),
       str_to_date('09-FEB-1986', '%e-%b-%Y'), 14000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (30,'Senior Electrical Engineer',
       str_to_date('30-APR-1992', '%e-%b-%Y'), NULL,  22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (30,'Electrical Engineer',
       str_to_date('06-MAR-1987', '%e-%b-%Y'),
       str_to_date('30-APR-1992', '%e-%b-%Y'), 17000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (31,'Senior Mechanical Engineer',
       str_to_date('30-MAY-1987', '%e-%b-%Y'),NULL, 22000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (31,'Mechanical Engineer',
       str_to_date('06-JUN-1980', '%e-%b-%Y'),
       str_to_date('30-MAY-1987', '%e-%b-%Y'),17000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (32,'Systems Engineer',
       str_to_date('01-MAR-1991', '%e-%b-%Y'), NULL, 19000.00);
insert into jobhistory (empno, position, startdate, enddate, salary)
       values (32,'Electronics Engineer',
       str_to_date('31-OCT-1989', '%e-%b-%Y'),
       str_to_date('01-MAR-1991', '%e-%b-%Y'), 18000.00);
