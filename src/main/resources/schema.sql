drop table if exists USERS;
drop table if exists MESSAGES;
drop table if exists FOLLOWERS;

create table USERS
(
   USER_ID integer not null,
   USERNAME varchar(255) not null,
   FOLLOWER integer,
   primary key(USER_ID),
   foreign key (FOLLOWER) references USERS(USER_ID)

);

create table MESSAGES
(
   MESSAGE_ID integer not null,
   TEXT varchar(140) not null,
   USER_ID integer,
   CREATE_DATE timestamp,
   primary key(MESSAGE_ID),
   foreign key (USER_ID) references USERS(USER_ID)
);

create table FOLLOWERS
(
   ID integer not null,
   TARGET integer,
   FOLLOWER integer,
   primary key(ID),
   foreign key (TARGET) references USERS(USER_ID),
   foreign key (FOLLOWER) references USERS(USER_ID)
);
