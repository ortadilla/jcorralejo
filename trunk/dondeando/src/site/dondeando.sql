/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     02/10/2009 21:13:33                          */
/*==============================================================*/


drop index I_ID on IMAGEN;

drop table if exists IMAGEN;

drop index I_ID on IMAGENLOCAL;

drop table if exists IMAGENLOCAL;

drop index I_ID on LOCAL;

drop table if exists LOCAL;

drop index I_ID on PERMISOUSUARIO;

drop table if exists PERMISOUSUARIO;

drop index I_ID on SERVICIO;

drop table if exists SERVICIO;

drop index I_LOCAL on SERVICIOSLOCAL;

drop table if exists SERVICIOSLOCAL;

drop index I_ID on TIPOLOCAL;

drop table if exists TIPOLOCAL;

drop index I_TIPOLOCAL on TIPOLOCALLOCAL;

drop index I_LOCAL on TIPOLOCALLOCAL;

drop table if exists TIPOLOCALLOCAL;

drop index I_ID on TIPOUSUARIO;

drop table if exists TIPOUSUARIO;

drop index I_LOGIN on USUARIO;

drop index I_ID on USUARIO;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: IMAGEN                                                */
/*==============================================================*/
create table IMAGEN
(
   ID                   numeric(10) not null,
   VERSION              numeric(10) not null,
   NOMBRE               varchar(120) not null comment 'Nombre de la imagen',
   CONTENIDO            blob comment 'El contenido de la imagen en s�',
   primary key (ID)
);

alter table IMAGEN comment 'Imagenes usadas en el sistema por los usuarios';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create unique index I_ID on IMAGEN
(
   ID
);

/*==============================================================*/
/* Table: IMAGENLOCAL                                           */
/*==============================================================*/
create table IMAGENLOCAL
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   LOCAL                numeric(10) not null comment 'Local que tendr� la imagen asociada',
   IMAGEN               numeric(10) not null comment 'Imagen asociada al local',
   DESCRIPCION          varchar(50) comment 'Descripci�n de la imagen del local',
   primary key (ID)
);

alter table IMAGENLOCAL comment 'Tabla que relaciona cada uno de los locales con sus im�genes';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on IMAGENLOCAL
(
   ID
);

/*==============================================================*/
/* Table: LOCAL                                                 */
/*==============================================================*/
create table LOCAL
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   NOMBRE               varchar(50) not null comment 'Nombre completo del local',
   DESCRIPCION          varchar(2000) comment 'Descripci�n completa del local',
   DIRECCION            varchar(100) not null comment 'Direcci�n f�sica del local',
   TELEFONO             varchar(9) comment 'N�mero de tel�fono del local',
   WEB                  varchar(100) comment 'P�gina web del local',
   EMAIL                varchar(100) comment 'Direcci�n de correo electr�nico para ponerse en contacto con el local',
   HORARIO              varchar(200) comment 'Horario del local',
   PRECIOMEDIO          varchar(20) comment 'Precio medio por persona para una comida en este local',
   VALORACION           dec(20,2) comment 'Valoraci�n media de todas las valoraciones efectuadas por los usuarios para este local',
   OTRAINFORMACION      varchar(2000) comment 'Otra informaci�n relevante sobre el local',
   primary key (ID)
);

alter table LOCAL comment 'Informaci�n acerca de cada locales de la aplicaci�n';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on LOCAL
(
   ID
);

/*==============================================================*/
/* Table: PERMISOUSUARIO                                        */
/*==============================================================*/
create table PERMISOUSUARIO
(
   ID                   numeric(10) not null,
   VERSION              numeric(10) not null comment 'version',
   PERMISO              numeric(2) not null comment 'Indica qu� permiso tiene el tipoUsuario. La informaci�n no estar� en BD sino en la entidad "Permiso"',
   TIPOUSUARIO          numeric(10) not null comment 'Indica qu� tipoUsuario tiene el permiso',
   primary key (ID)
);

alter table PERMISOUSUARIO comment 'Indica qu� permisos tiene cada tipo de usuario';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on PERMISOUSUARIO
(
   ID
);

/*==============================================================*/
/* Table: SERVICIO                                              */
/*==============================================================*/
create table SERVICIO
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   DESCRIPCION          varchar(50) not null comment 'Descripci�n del servicio en s�',
   CODIGO               varchar(10) not null comment 'C�digo interno de la aplicaci�n para identificar al servicio',
   primary key (ID)
);

alter table SERVICIO comment 'Cada uno de los servicios de los que puede disponer un local';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on SERVICIO
(
   ID
);

/*==============================================================*/
/* Table: SERVICIOSLOCAL                                        */
/*==============================================================*/
create table SERVICIOSLOCAL
(
   SERVICIO             numeric(10) not null comment 'Servicio concreto del local',
   LOCAL                numeric(10) not null comment 'Local para el que se definen los servicios'
);

alter table SERVICIOSLOCAL comment 'Cada uno de los servicios que dispone un local';

/*==============================================================*/
/* Index: I_LOCAL                                               */
/*==============================================================*/
create index I_LOCAL on SERVICIOSLOCAL
(
   LOCAL
);

/*==============================================================*/
/* Table: TIPOLOCAL                                             */
/*==============================================================*/
create table TIPOLOCAL
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   DESCRIPCION          varchar(50) not null comment 'Descripci�n del tipo de local',
   primary key (ID)
);

alter table TIPOLOCAL comment 'Cada uno de los tipos en los que se pueden clasificar los lo';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on TIPOLOCAL
(
   ID
);

/*==============================================================*/
/* Table: TIPOLOCALLOCAL                                        */
/*==============================================================*/
create table TIPOLOCALLOCAL
(
   TIPOLOCAL            numeric(10) not null,
   LOCAL                numeric(10) not null comment 'Local clasificado en el tipo'
);

alter table TIPOLOCALLOCAL comment 'Cada uno de los tipos en los que se puede clasificar un loca';

/*==============================================================*/
/* Index: I_LOCAL                                               */
/*==============================================================*/
create index I_LOCAL on TIPOLOCALLOCAL
(
   LOCAL
);

/*==============================================================*/
/* Index: I_TIPOLOCAL                                           */
/*==============================================================*/
create index I_TIPOLOCAL on TIPOLOCALLOCAL
(
   TIPOLOCAL
);

/*==============================================================*/
/* Table: TIPOUSUARIO                                           */
/*==============================================================*/
create table TIPOUSUARIO
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   DESCRIPCION          varchar(120) not null comment 'Descripci�n del tipo de usuario',
   ACTIVO               char(1) not null default 'T' comment 'Indica si el Tipo de Usuario est� activo',
   primary key (ID)
);

alter table TIPOUSUARIO comment 'Cada uno de los tipo de usuario del sistema';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on TIPOUSUARIO
(
   ID
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   LOGIN                varchar(20) not null comment 'Nombre del usuario en el sistema',
   PASSWORD             varchar(100) not null comment 'Contrase�a del usuario para entrar en la aplicaci�n',
   ACTIVO               char(1) not null default 'T' comment 'Indica si el usuario est� activo en la aplicaci�n',
   NOMBRE               varchar(50) not null comment 'Nombre del usuario',
   APELLIDOS            varchar(100) comment 'Apellidos del usuario',
   DIRECCION            varchar(100) comment 'Direcci�n del usuario',
   EMAIL                varchar(100) not null comment 'Correo electr�nico del usuario',
   KARMA                double not null default 6 comment 'Puntuaci�n del usuario en el sistema. TODO: Explicar el algoritmo para calcularlo',
   TIPO                 numeric(10) not null comment 'Indica que tipo de usuario es',
   AVATAR               numeric(10) comment 'Imagen avatar del usuario',
   primary key (ID)
);

alter table USUARIO comment 'Informaci�n acerca de cada usuario de la aplicaci�n';

/*==============================================================*/
/* Index: I_ID                                                  */
/*==============================================================*/
create index I_ID on USUARIO
(
   ID
);

/*==============================================================*/
/* Index: I_LOGIN                                               */
/*==============================================================*/
create index I_LOGIN on USUARIO
(
   LOGIN
);

alter table IMAGENLOCAL add constraint FK_IMAGENLOCAL_IMAGEN foreign key (IMAGEN)
      references IMAGEN (ID) on delete restrict on update restrict;

alter table IMAGENLOCAL add constraint FK_IMAGENLOCAL_LOCAL foreign key (LOCAL)
      references LOCAL (ID) on delete restrict on update restrict;

alter table PERMISOUSUARIO add constraint FK_PERM_TIPIU foreign key (TIPOUSUARIO)
      references TIPOUSUARIO (ID) on delete restrict on update restrict;

alter table SERVICIOSLOCAL add constraint FK_SERVICIOLOCAL_LOCAL foreign key (LOCAL)
      references LOCAL (ID) on delete restrict on update restrict;

alter table SERVICIOSLOCAL add constraint FK_SERVICIOLOCAL_SERVICIO foreign key (SERVICIO)
      references SERVICIO (ID) on delete restrict on update restrict;

alter table TIPOLOCALLOCAL add constraint FK_FK_TIPOLOCAL_LOCAL foreign key (LOCAL)
      references LOCAL (ID) on delete restrict on update restrict;

alter table TIPOLOCALLOCAL add constraint FK_FK_TIPOLOCAL_TIPOLOCAL foreign key (TIPOLOCAL)
      references TIPOLOCAL (ID) on delete restrict on update restrict;

alter table USUARIO add constraint FK_USUARIOIMAGEN foreign key (AVATAR)
      references IMAGEN (ID) on delete restrict on update restrict;

alter table USUARIO add constraint FK_USUARIO_TIPO foreign key (TIPO)
      references TIPOUSUARIO (ID) on delete restrict on update restrict;

