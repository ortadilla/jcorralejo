/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     10/12/2010 0:55:47                           */
/*==============================================================*/


drop index IDX_ID on LIBRO;

drop table if exists LIBRO;

drop index IDX_ID on PERFIL;

drop table if exists PERFIL;

drop index IDX_PERFIL on PERMISOPERFIL;

drop index IDX_ID on PERMISOPERFIL;

drop table if exists PERMISOPERFIL;

drop index IDX_LIBRO on PRESTAMO;

drop index IDX_USUARIO on PRESTAMO;

drop index IDX_ID on PRESTAMO;

drop table if exists PRESTAMO;

drop index IDX_LOGIN on USUARIO;

drop index IDX_ID on USUARIO;

drop table if exists USUARIO;

drop index IDX_USUARIO on USUARIOPERFIL;

drop table if exists USUARIOPERFIL;

/*==============================================================*/
/* Table: LIBRO                                                 */
/*==============================================================*/
create table LIBRO
(
   ID                   numeric(10,0) not null,
   VERSION              numeric(10,0) not null,
   TITULO               varchar(255) not null comment 'Título del libro',
   AUTOR                varchar(255) not null comment 'Autor del libro',
   ISBN                 varchar(13) not null comment 'ISBN del Libro. Puede ser de 10 o de 13 caracteres',
   UNIDADESDISPONIBLES  numeric(2,0) not null default 1 comment 'Números de unidades disponibles para prestamo del libro en la Aplicación',
   primary key (ID)
);

alter table LIBRO comment 'Cada uno de los libros disponibles en la Aplicación';

/*==============================================================*/
/* Index: IDX_ID                                                */
/*==============================================================*/
create unique index IDX_ID on LIBRO
(
   ID
);

/*==============================================================*/
/* Table: PERFIL                                                */
/*==============================================================*/
create table PERFIL
(
   ID                   numeric(10,0) not null,
   VERSION              numeric(10,0) not null,
   DESCRIPCION          varchar(50) not null comment 'Descripción del perfil',
   primary key (ID)
);

alter table PERFIL comment 'Cada uno de los perfiles de la Aplicación';

/*==============================================================*/
/* Index: IDX_ID                                                */
/*==============================================================*/
create unique index IDX_ID on PERFIL
(
   ID
);

/*==============================================================*/
/* Table: PERMISOPERFIL                                         */
/*==============================================================*/
create table PERMISOPERFIL
(
   ID                   numeric(10,0) not null,
   VERSION              numeric(10,0) not null comment 'version',
   PERFIL               numeric(10,0) not null comment 'Indica qué perfil tiene el permiso',
   PERMISO              numeric(2,0) not null comment 'Clave del permiso',
   primary key (ID)
);

alter table PERMISOPERFIL comment 'Indica qué permisos tiene cada tipo de usuario';

/*==============================================================*/
/* Index: IDX_ID                                                */
/*==============================================================*/
create unique index IDX_ID on PERMISOPERFIL
(
   ID
);

/*==============================================================*/
/* Index: IDX_PERFIL                                            */
/*==============================================================*/
create index IDX_PERFIL on PERMISOPERFIL
(
   PERFIL
);

/*==============================================================*/
/* Table: PRESTAMO                                              */
/*==============================================================*/
create table PRESTAMO
(
   ID                   numeric(10,0) not null,
   VERSION              numeric(10,0) not null,
   USUARIO              numeric(10,0) not null comment 'Usuario que realiza el préstamo',
   LIBRO                numeric(10,0) not null comment 'Libro que se presta',
   FECHAINICIO          date not null comment 'Fecha en la que comienza el préstamo',
   FECHAFIN             datetime not null comment 'Fecha en la que termina el préstamo',
   DEVUELTO             char(1) not null default 'F' comment 'Indica si el libro ha siso devuelto',
   primary key (ID)
);

alter table PRESTAMO comment 'Cada uno de los préstamos que se realizan en la Aplicación';

/*==============================================================*/
/* Index: IDX_ID                                                */
/*==============================================================*/
create unique index IDX_ID on PRESTAMO
(
   ID
);

/*==============================================================*/
/* Index: IDX_USUARIO                                           */
/*==============================================================*/
create index IDX_USUARIO on PRESTAMO
(
   USUARIO
);

/*==============================================================*/
/* Index: IDX_LIBRO                                             */
/*==============================================================*/
create index IDX_LIBRO on PRESTAMO
(
   LIBRO
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID                   numeric(10,0) not null,
   VERSION              numeric(10,0) not null,
   LOGIN                varchar(50) not null comment 'Login del usuario para conectarse a la Aplicación',
   PASS                 varchar(100) not null comment 'Contraseña del usuario para entrar en la Aplicación',
   NOMBRE               varchar(50) comment 'Nombre del Usuario',
   primary key (ID)
);

alter table USUARIO comment 'Cada uno de los usuarios de la Aplicación';

/*==============================================================*/
/* Index: IDX_ID                                                */
/*==============================================================*/
create unique index IDX_ID on USUARIO
(
   ID
);

/*==============================================================*/
/* Index: IDX_LOGIN                                             */
/*==============================================================*/
create unique index IDX_LOGIN on USUARIO
(
   LOGIN
);

/*==============================================================*/
/* Table: USUARIOPERFIL                                         */
/*==============================================================*/
create table USUARIOPERFIL
(
   USUARIO              numeric(10,0) not null comment 'Usuario que tiene el perfil',
   PERFIL               numeric(10,0) not null comment 'Perfil que tiene el usuario',
   primary key (USUARIO, PERFIL)
);

alter table USUARIOPERFIL comment 'Relación entre los usuarios y sus perfiles asociados';

/*==============================================================*/
/* Index: IDX_USUARIO                                           */
/*==============================================================*/
create index IDX_USUARIO on USUARIOPERFIL
(
   USUARIO
);

alter table PERMISOPERFIL add constraint FK_PERMISOPERFIL_PERFIL foreign key (PERFIL)
      references PERFIL (ID) on delete restrict on update restrict;

alter table PRESTAMO add constraint FK_PRESTAMO_LIBRO foreign key (LIBRO)
      references LIBRO (ID) on delete restrict on update restrict;

alter table PRESTAMO add constraint FK_PRESTAMO_USUARIO foreign key (USUARIO)
      references USUARIO (ID) on delete restrict on update restrict;

alter table USUARIOPERFIL add constraint FK_USUARIOPERFIL_PERFIL foreign key (PERFIL)
      references PERFIL (ID) on delete restrict on update restrict;

alter table USUARIOPERFIL add constraint FK_FK_USUARIOPERFIL_USUARIO foreign key (USUARIO)
      references USUARIO (ID) on delete restrict on update restrict;

