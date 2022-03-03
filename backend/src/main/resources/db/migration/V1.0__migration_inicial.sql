create table tab_filme (

	fil_id uuid not null,
	fil_nome varchar(255),
	fil_genero varchar(255),
	fil_diretor varchar(255),
	fil_quantidade int,

	primary key(fil_id)

);

create table tab_usuario (

	usu_id uuid not null,
	usu_nome varchar(255) not null,
	usu_sexo varchar(10),
	usu_cpf varchar(11) not null,
	usu_idade int,
	usu_data_nascimento date not null,

	primary key(usu_id)

);

create table tab_locacao (
	loc_id uuid not null,
	loc_usuario_id uuid not null,
	loc_data_devolucao date not null,
	loc_data_locacao date not null,

	primary key(loc_id)
);


create table locacao_filme (
 	lof_loc_id uuid not null,
 	lof_fil_id uuid not null,

    constraint fk_lof_loc_id foreign key (lof_loc_id) references tab_locacao(loc_id),
    constraint fk_lof_fil_id foreign key (lof_fil_id) references tab_filme(fil_id)
);