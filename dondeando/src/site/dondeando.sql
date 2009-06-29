/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/04/2009 18:44:50                          */
/*==============================================================*/


drop index I_ID on PERMISOUSUARIO;

drop table if exists PERMISOUSUARIO;

drop index I_ID on TIPOUSUARIO;

drop table if exists TIPOUSUARIO;

drop index I_LOGIN on USUARIO;

drop index I_ID on USUARIO;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: PERMISOUSUARIO                                        */
/*==============================================================*/
create table PERMISOUSUARIO
(
   ID                   numeric(10) not null,
   VERSION              numeric(10) not null comment 'version',
   ENTIDAD              numeric(2) comment 'Entidad sobre la que act�a el permiso',
   CLAVEOBJ             numeric(10) comment 'Indica el objeecto concreto sobre el que act�a el permiso',
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
/* Table: TIPOUSUARIO                                           */
/*==============================================================*/
create table TIPOUSUARIO
(
   ID                   numeric(10) not null comment 'id',
   VERSION              numeric(10) not null comment 'version',
   DESCRIPCION          varchar(120) not null comment 'Descripci�n del tipo de usuario',
   ACTIVO               char(1) not null default 'T' comment 'Indica si el Tipo de Usuario est� activo',
   FECHAMODIF           date comment '�ltima fecha de modificaci�n del Tipo de Usuario',
   USUARIOMODIF         numeric(10) comment '�ltimo usuario que modific� el Tipo de Usuario',
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
   FECHAMODIF           date comment '�ltima fecha de modificaci�n de los datos del usuario',
   USUARIOMODIF         numeric(10) comment '�ltimo usuario que modific� lo datos del usuario',
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

alter table PERMISOUSUARIO add constraint FK_PERM_TIPIU foreign key (TIPOUSUARIO)
      references TIPOUSUARIO (ID) on delete restrict on update restrict;

alter table TIPOUSUARIO add constraint FK_TIPO_USMOD foreign key (USUARIOMODIF)
      references USUARIO (ID) on delete restrict on update restrict;

alter table USUARIO add constraint FK_USUARIO_TIPO foreign key (TIPO)
      references TIPOUSUARIO (ID) on delete restrict on update restrict;

