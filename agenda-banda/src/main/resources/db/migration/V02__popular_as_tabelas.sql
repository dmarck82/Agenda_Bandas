insert into tb_banda (id_banda, nome) values (1, 'Academicos do Samba');
insert into tb_banda (id_banda, nome) values (2, 'Cavaleiros do Forro');
insert into tb_banda (id_banda, nome) values (3, 'Os sem talento');

insert into tb_casa_de_show (id_casa_de_show, nome, telefone, responsavel, email) values (1, 'Aruba Cafe', '65 12345-6789', 'Jorginho', 'arubacafe@gmail.com');
insert into tb_casa_de_show (id_casa_de_show, nome, telefone, responsavel, email) values (2, 'Orion Lounge', '65 12345-6789', 'Gilson', 'orion@gmail.com');

insert into tb_integrantes (id_integrantes, nome, sobrenome, telefone, cpf, email, codigo_banda) values (1, 'Danilo', 'Pietro', '(82)99155-2884', '41033276340', 'danilo.pietro.darocha@zigotto.com.br', 1);
insert into tb_integrantes (id_integrantes, nome, sobrenome, telefone, cpf, email, codigo_banda) values (2, 'Joaquim', 'Almeida', '(85)2689-7133', '98509906947', 'joaquim_julio_almeida@costaporto.com.br', 2);
insert into tb_integrantes (id_integrantes, nome, sobrenome, telefone, cpf, email, codigo_banda) values (3, 'Douglas', 'Marcondes', '(34)2311-7567', '98509906947', 'douglas@gmail.com', 2);

insert into tb_agenda_de_shows(id_agenda_de_shows, data_show, `cache`, codigo_casa_de_show, codigo_banda) values (1, str_to_date('04-25-2022','%m-%d-%Y'), 250, 1, 1);
insert into tb_agenda_de_shows(id_agenda_de_shows, data_show, `cache`, codigo_casa_de_show, codigo_banda) values (2, str_to_date('03-17-2022','%m-%d-%Y'), 500, 2, 3);