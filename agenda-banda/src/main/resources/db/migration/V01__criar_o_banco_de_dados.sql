create table tb_banda(
    id_banda int auto_increment not null,
    nome varchar(45) not null,
    primary key (id_banda)
);

create table tb_casa_de_show(
    id_casa_de_show int auto_increment not null,
    nome varchar(45) not null,
    telefone varchar(15) not null,
    responsavel varchar(45) not null,
    email varchar(45) not null,
    primary key (id_casa_de_show)
);

create table tb_integrantes(
    id_integrantes int auto_increment not null,
    nome varchar(45) not null,
    sobrenome varchar(45) not null,
    telefone varchar(15) not null,
    cpf varchar(11) not null,
    email varchar(45) null,
    id_banda int,
    primary key (id_integrantes),
    Foreign key(id_banda) references tb_banda(id_banda)
);

create table tb_agenda_de_shows(
    id_agenda_de_shows int auto_increment not null,
    `data` date not null,
    `cache` decimal(10) not null,
    id_casa_de_show int,
    id_banda int,
    primary key (id_agenda_de_shows),
    Foreign Key (id_casa_de_show) references tb_casa_de_show(id_casa_de_show),
    Foreign Key (id_banda) references tb_banda(id_banda)
);