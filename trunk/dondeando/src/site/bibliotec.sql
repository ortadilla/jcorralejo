
drop database if exists BIBLIOTEC;

/*==============================================================*/
/* Database: BIBLIOTEC                                          */
/*==============================================================*/
create database BIBLIOTEC;

use BIBLIOTEC;

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


/*==============================================================*/
/* CARGA DE DATOS DE PRUEBA                                     */
/*==============================================================*/
      
INSERT INTO libro(ID, VERSION, TITULO, AUTOR, ISBN, UNIDADESDISPONIBLES)
  VALUES(1, 0, 'Un mundo sin fin', 'Ken Follet', '9788401336560', 3);
INSERT INTO libro(ID, VERSION, TITULO, AUTOR, ISBN, UNIDADESDISPONIBLES)
  VALUES(2, 0, 'El color de la magia', 'Terry Pratchett', '9788397596794', 2);
INSERT INTO libro(ID, VERSION, TITULO, AUTOR, ISBN, UNIDADESDISPONIBLES)
  VALUES(3, 0, 'La regenta', 'Leopoldo Alas Clarín', '9788498165692', 4);
INSERT INTO libro(ID, VERSION, TITULO, AUTOR, ISBN, UNIDADESDISPONIBLES)
  VALUES(4, 0, 'El asombroso viaje de Pomponio Flato', 'Eduardo Mendoza', '9788432212536', 1);


INSERT INTO perfil(ID, VERSION, DESCRIPCION)
  VALUES(1, 0, 'Administrador general');
INSERT INTO perfil(ID, VERSION, DESCRIPCION)
  VALUES(2, 0, 'Usuario Biblioteca');

  
INSERT INTO permisoperfil(ID, VERSION, PERFIL, PERMISO)
  VALUES(1, 0, 1, 1);
INSERT INTO permisoperfil(ID, VERSION, PERFIL, PERMISO)
  VALUES(2, 0, 1, 2);
INSERT INTO permisoperfil(ID, VERSION, PERFIL, PERMISO)
  VALUES(3, 0, 1, 3);
INSERT INTO permisoperfil(ID, VERSION, PERFIL, PERMISO)
  VALUES(4, 0, 1, 4);
INSERT INTO permisoperfil(ID, VERSION, PERFIL, PERMISO)
  VALUES(5, 0, 2, 3);
INSERT INTO permisoperfil(ID, VERSION, PERFIL, PERMISO)
  VALUES(6, 0, 2, 5);

  
INSERT INTO usuario(ID, VERSION, LOGIN, PASS, NOMBRE)
  VALUES(1, 0, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Administrador');
INSERT INTO usuario(ID, VERSION, LOGIN, PASS, NOMBRE)
  VALUES(6, 0, 'usuario', 'b665e217b51994789b02b1838e730d6b93baa30f', 'Jesús Corralejo Banda');
INSERT INTO usuario(ID, VERSION, LOGIN, PASS, NOMBRE)
  VALUES(7, 0, 'aLobato', 'e6b4f43016dec11f6b4aa5e4ffdeb0a20d381109', 'Álvaro Lobato Moreno');

  
INSERT INTO usuarioperfil(USUARIO, PERFIL)
  VALUES(1, 1);
INSERT INTO usuarioperfil(USUARIO, PERFIL)
  VALUES(6, 2);
INSERT INTO usuarioperfil(USUARIO, PERFIL)
  VALUES(7, 2);

  
INSERT INTO prestamo(ID, VERSION, USUARIO, LIBRO, FECHAINICIO, FECHAFIN, DEVUELTO)
  VALUES(1, 0, 7, 4, '2010-12-10', '2010-12-19 00:00:00.0', 'F');
INSERT INTO prestamo(ID, VERSION, USUARIO, LIBRO, FECHAINICIO, FECHAFIN, DEVUELTO)
  VALUES(2, 0, 6, 2, '2010-12-10', '2010-12-19 00:00:00.0', 'F');
