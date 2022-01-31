/*
 список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу
 */
(SELECT mov.title, COUNT(mov.id) as visits, avgt.avg_visits, SUM(ti.price) as amount
 FROM movies mov
          INNER JOIN (SELECT avgtbl.movie_id, AVG(avgtbl.ses_count) as avg_visits
                      FROM (SELECT sc.movie_id, COUNT(sc.movie_id) as ses_count
                            FROM session_schedule sc
                                     LEFT JOIN session_tickets ti on ti.session_id = sc.id
                                     LEFT JOIN sales_log lg ON lg.ticket_id = ti.id
                            GROUP BY sc.id) avgtbl
                      GROUP BY avgtbl.movie_id) avgt ON avgt.movie_id = mov.id
          INNER JOIN session_schedule sc ON sc.movie_id = mov.id
          LEFT JOIN session_tickets ti on ti.session_id = sc.id
          LEFT JOIN sales_log lg ON lg.ticket_id = ti.id
 GROUP BY mov.id
 ORDER BY amount DESC LIMIT 1000000)
UNION ALL
(SELECT itog.name, sum(itog.visits) as visit, AVG(itog.avg_visits), SUM(itog.amount) as imount
 FROM (SELECT 'Итого' as name, COUNT(mov.id) as visits, avgt.avg_visits, SUM(ti.price) as amount
       FROM movies mov
                INNER JOIN (SELECT avgtbl.movie_id, AVG(avgtbl.ses_count) as avg_visits
                            FROM (SELECT sc.movie_id, COUNT(sc.movie_id) as ses_count
                                  FROM session_schedule sc
                                           LEFT JOIN session_tickets ti on ti.session_id = sc.id
                                           LEFT JOIN sales_log lg ON lg.ticket_id = ti.id
                                  GROUP BY sc.id) avgtbl
                            GROUP BY avgtbl.movie_id) avgt ON avgt.movie_id = mov.id
                INNER JOIN session_schedule sc ON sc.movie_id = mov.id
                LEFT JOIN session_tickets ti on ti.session_id = sc.id
                LEFT JOIN sales_log lg ON lg.ticket_id = ti.id
       GROUP BY mov.id) as itog
 GROUP BY itog.name)