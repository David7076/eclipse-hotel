-- 1. Quantos clientes temos na base?
   SELECT COUNT(id) FROM customers;
   
-- 2. Quantos quartos temos cadastrados?
   SELECT COUNT(id) FROM rooms;
   
-- 3. Quantas reservas em aberto o hotel possui no momento?
    SELECT COUNT(id)
	FROM reservations
	WHERE status IN ('SCHEDULED', 'IN_USE');
	
-- 4. Quantos quartos temos vagos no momento?
	SELECT COUNT(r.id)
	FROM rooms r
	LEFT JOIN reservations res
    ON r.id = res.room_id
    AND CURRENT_DATE BETWEEN res.checkin AND res.checkout
    AND res.status IN ('SCHEDULED', 'IN_USE')
	WHERE res.id IS NULL;
	
-- 5. Quantos quartos temos ocupados no momento?
	SELECT COUNT(DISTINCT r.id)
	FROM rooms r
	INNER JOIN reservations res
    ON r.id = res.room_id
	WHERE CURRENT_DATE BETWEEN res.checkin AND res.checkout
    AND res.status IN ('SCHEDULED', 'IN_USE');
	
-- 6. Quantas reservas futuras o hotel possui?
	SELECT COUNT(id)
	FROM reservations
	WHERE status = 'SCHEDULED'
    AND checkin > CURRENT_DATE;
	
-- 7. Qual o quarto mais caro do hotel?
	SELECT room_number, type, price
	FROM rooms
	ORDER BY price DESC
	LIMIT 1;
	
-- 8. Qual o quarto com maior histórico de cancelamentos?
	SELECT r.room_number, COUNT(res.id) AS total_cancelamentos
	FROM rooms r
	JOIN reservations res ON r.id = res.room_id
	WHERE res.status = 'CANCELED'
	GROUP BY r.room_number
	ORDER BY total_cancelamentos DESC
	LIMIT 1;
	
-- 9. Liste todos os quartos e a quantidade de clientes que já ocuparam cada um.

	SELECT
    r.room_number,
    COUNT(DISTINCT res.customer_id) AS clientes_distintos_ocupantes
	FROM rooms r
	LEFT JOIN reservations res ON r.id = res.room_id
	WHERE res.status IN ('FINISHED', 'IN_USE')
	GROUP BY r.room_number
	ORDER BY clientes_distintos_ocupantes DESC;
	
	
-- 10. Quais são os 3 quartos que possuem um histórico maior de ocupações?
	SELECT r.room_number, COUNT(res.id) AS total_reservas_concluidas
	FROM rooms r
	JOIN reservations res ON r.id = res.room_id
	WHERE res.status IN ('FINISHED', 'IN_USE')
	GROUP BY r.room_number
	ORDER BY total_reservas_concluidas DESC
	LIMIT 3;
-- 11. No próximo mês, o hotel fará uma promoção para os seus 10 clientes que
	SELECT c.name, COUNT(res.id) AS total_reservas
	FROM customers c
	JOIN reservations res ON c.id = res.customer_id
	GROUP BY c.id, c.name
	ORDER BY total_reservas DESC
	LIMIT 10;
	
possuírem maior histórico de reservas e você foi acionado pelo seu time para
extrair esta informação do banco de dados. Quem são os 10 clientes?
12. Qual a receita (R$) média total gerada (assumindo que entrará no cálculo apenas
os status FINISHED e IN_USE)
	SELECT
    AVG(
        (res.checkout - res.checkin) * r.price
    ) AS receita_media_total
	FROM reservations res
	JOIN rooms r ON res.room_id = r.id
	WHERE res.status IN ('FINISHED', 'IN_USE');
