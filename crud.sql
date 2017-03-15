create table produtos(
	idProduto bigint auto_increment,
	nome varchar (50) not null,
	valorCusto double not null,
	quantidade int not null,
	primary key(idProduto)
);

create table usuario(
	idUsuario bigint auto_increment,
	login varchar (30) not null unique,
	senha varchar (15) not null,
	primary key (idUsuario)
);
