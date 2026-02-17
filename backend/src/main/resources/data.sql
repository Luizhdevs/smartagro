-- LIMPA OS DADOS
DELETE FROM tb_animal_farm_history;
DELETE FROM tb_animal;
DELETE FROM tb_farm;

-- RESETA OS CONTADORES
ALTER TABLE tb_animal_farm_history ALTER COLUMN id RESTART WITH 1;
ALTER TABLE tb_animal ALTER COLUMN id RESTART WITH 1;
ALTER TABLE tb_farm ALTER COLUMN id RESTART WITH 1;

-- ============================================
-- 1. INSERE FAZENDAS (IDs 1-6)
-- ============================================
INSERT INTO tb_farm (name, location, total_area) VALUES
                                                     ('Fazenda Boa Esperança', 'Zona Rural, São Paulo - SP', 150.5),
                                                     ('Fazenda Santa Maria', 'Vale do Paraíba, Minas Gerais', 320.75),
                                                     ('Fazenda Novo Horizonte', 'Campo Grande, Mato Grosso do Sul', 580.0),
                                                     ('Fazenda Recanto Verde', 'Serra Gaúcha, Rio Grande do Sul', 95.2),
                                                     ('Fazenda Boa Vista', 'Uberaba, Minas Gerais', 450.0),
                                                     ('Fazenda Santa Fé', 'Ribeirão Preto, São Paulo', 280.0);

-- ============================================
-- 2. INSERE ANIMAIS PAIS (IDs 1-5)
-- ============================================
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, situacao, current_farm_id) VALUES
                                                                                                      ('Touro Rei', 'Bovino', 'CORTE', 'Nelore', '2019-03-15', 850.5, 'ATIVO', 1),
                                                                                                      ('Vaca Rainha', 'Bovino', 'LEITE', 'Holandesa', '2018-07-20', 620.0, 'ATIVO', 1),
                                                                                                      ('Touro Campeão', 'Bovino', 'CORTE', 'Angus', '2020-01-10', 780.0, 'ATIVO', 2),
                                                                                                      ('Vaca Estrela', 'Bovino', 'LEITE', 'Gir', '2019-11-05', 580.0, 'ATIVO', 2),
                                                                                                      ('Touro Gigante', 'Bovino', 'CORTE', 'Hereford', '2017-05-30', 920.0, 'ATIVO', 3);

-- ============================================
-- 3. INSERE FILHOS (IDs 6-15)
-- ============================================
-- Filhos da Vaca Rainha (id=2) com Touro Rei (id=1)
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, mother_id, father_id, situacao, current_farm_id) VALUES
                                                                                                                            ('Bezerro Chocolate', 'Bovino', 'CORTE', 'Nelore', '2022-05-10', 320.0, 2, 1, 'ATIVO', 1),
                                                                                                                            ('Bezerra Malhada', 'Bovino', 'LEITE', 'Holandesa', '2023-02-18', 210.5, 2, 1, 'ATIVO', 1),
                                                                                                                            ('Bezerro Forte', 'Bovino', 'CORTE', 'Nelore', '2024-01-05', 150.0, 2, 1, 'ATIVO', 1);

-- Filhos da Vaca Estrela (id=4) com Touro Campeão (id=3)
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, mother_id, father_id, situacao, current_farm_id) VALUES
                                                                                                                            ('Bezerro Listrado', 'Bovino', 'CORTE', 'Angus', '2022-08-22', 290.0, 4, 3, 'ATIVO', 2),
                                                                                                                            ('Bezerrinha Estrela', 'Bovino', 'LEITE', 'Gir', '2023-06-14', 180.0, 4, 3, 'ATIVO', 2);

-- Filho da Vaca Rainha (id=2) com Touro Gigante (id=5)
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, mother_id, father_id, situacao, current_farm_id) VALUES
    ('Bezerrão Gigante', 'Bovino', 'CORTE', 'Hereford', '2021-11-30', 450.0, 2, 5, 'ATIVO', 3);

-- Mais filhos
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, mother_id, father_id, situacao, current_farm_id) VALUES
                                                                                                                            ('Bezerro Pintado', 'Bovino', 'CORTE', 'Nelore', '2023-09-10', 195.0, 2, 1, 'ATIVO', 1),
                                                                                                                            ('Novilha Mimosa', 'Bovino', 'LEITE', 'Gir', '2023-11-20', 165.0, 4, 3, 'ATIVO', 2),
                                                                                                                            ('Garanhão do Norte', 'Bovino', 'CORTE', 'Angus', '2022-03-12', 350.0, 4, 3, 'ATIVO', 3),
                                                                                                                            ('Bezerro Teste', 'Bovino', 'CORTE', 'Nelore', '2023-12-01', 120.0, 2, 1, 'ATIVO', 1);

-- ============================================
-- 4. INSERE ANIMAIS VENDIDOS (IDs 16-17)
-- ============================================
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, situacao, data_venda, comprador, valor_venda, observacao_situacao) VALUES
    ('Boi Gordo', 'Bovino', 'CORTE', 'Nelore', '2020-01-10', 780.0, 'VENDIDO', '2024-01-15', 'Frigorífico Boi Bom', 4500.00, 'Vendido para abate');

INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, situacao, data_venda, comprador, valor_venda, observacao_situacao) VALUES
    ('Vaca Leiteira', 'Bovino', 'LEITE', 'Holandesa', '2019-05-20', 620.0, 'VENDIDO', '2023-11-10', 'Fazenda Santa Luzia', 3500.00, 'Vendida para reprodução');

-- ============================================
-- 5. INSERE ANIMAIS MORTOS (IDs 18-19)
-- ============================================
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, situacao, data_obito, observacao_situacao) VALUES
    ('Vaca Velha', 'Bovino', 'LEITE', 'Gir', '2015-11-05', 580.0, 'MORTO', '2024-02-01', 'Morte natural por idade avançada');

INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, situacao, data_obito, observacao_situacao) VALUES
    ('Bezerro Fraco', 'Bovino', 'CORTE', 'Nelore', '2024-01-20', 95.0, 'MORTO', '2024-02-10', 'Não resistiu após nascimento');

-- ============================================
-- 6. INSERE ANIMAL TRANSFERIDO (ID 20)
-- ============================================
INSERT INTO tb_animal (name, species, tipo, breed, birth_date, weight, situacao, data_transferencia, current_farm_id) VALUES
    ('Touro Jovem', 'Bovino', 'CORTE', 'Angus', '2021-08-15', 550.0, 'TRANSFERIDO', '2024-01-20', 4);

-- ============================================
-- 7. INSERE HISTÓRICO (OPCIONAL - COMENTADO POR ENQUANTO)
-- ============================================
-- Só adicione o histórico depois que confirmar que os animais estão com os IDs corretos