--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (1, 'alice@example.com', 'hashed_password_123', '00000000', '2025-02-04 13:18:11.127277', 1, 'temporary_password');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (4, 'abrahamsilvera3@gmail.com', NULL, '058-3253938', '2025-05-20 00:00:00', 4, '1234');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (3, 'esayagsl211@gmail.com', NULL, '123456789', '2025-05-20 00:00:00', 3, '1234');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (5, 'simja1@gmail.com', NULL, '123456', '2025-05-28 00:00:00', 5, '1010');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (6, 'em@gov.il', NULL, '058-3253938', '2025-05-14 00:00:00', 6, '789');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (8, '123@gmail.com', NULL, '058-3253938', '2025-05-27 00:00:00', 8, '1234');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (9, 'dyj23@gmail.com', NULL, '0555521602', '2017-01-27 00:00:00', 9, '123456');
INSERT INTO public.users (user_id, email, password_hash, phone, date, id, password) VALUES (10, 'ab11@gmail.com', NULL, '026529506', '2012-06-22 00:00:00', 10, '1233');


--
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (9, 'נעל עקב', 'נעל עקב עם קישוט גובה 8 ס"מ מידה 38', '/uploads/pinkshoe.png', 100, 100, '2025-05-29 00:00:00', '2025-07-23 00:00:00', 'active', 6, 9, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (4, 'מיקסר', 'מיקסר חשמלי בצבע אדום טוב מאוד ממש חדש', '/uploads/kitchenaid.png', 800, 800, '2025-05-28 00:00:00', '2025-06-05 00:00:00', 'active', 3, 4, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (6, 'מברגה', 'מברגה חברת דיוולט טובה במיוחד עם סוללה רזרבה ', '/uploads/dewalt.png', 249, 260, '2025-05-28 00:00:00', '2025-07-10 00:00:00', 'active', 5, 6, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (11, 'ספה', 'ספה נוחה במיוחד בצבע צהוב כחדשה', '/uploads/yellowcaoch.png', 650, 675, '2025-06-05 00:00:00', '2025-12-03 00:00:00', 'active', 6, 11, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (3, 'פנס', 'מנורה עם פנס נטענת אור יום ', '/uploads/light.png', 12, 23, '2025-05-29 00:00:00', '2025-06-04 00:00:00', 'active', 3, 3, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (10, 'נעל אדומה', 'נעל אדום לק שטוחה מדהימה לאירוע', '/uploads/redshoe.png', 50, 75, '2025-06-24 00:00:00', '2025-12-17 00:00:00', 'active', 6, 10, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (8, 'איירפראייר', 'מכונה לטיגון ללא שמן חברת ninja ', '/uploads/airfrayer.png', 500, 550, '2025-05-14 00:00:00', '2025-08-13 00:00:00', 'active', 6, 8, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (1, 'כלי עבודה', 'מארז כלי עבודה קטנים לשימוש ביתי חובה בכל בית', '/uploads/hera.png', 9, 25, '2025-05-21 00:00:00', '2025-05-25 00:00:00', 'closed', 3, 1, 4);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (2, 'ספרייה', 'ספרייה מעוצבת לבית בצבע שחור ועץ', '/uploads/bo.png', 200, 270, '2025-05-21 00:00:00', '2025-05-28 00:00:00', 'closed', 3, 2, 8);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (5, 'כורסא', 'כורסא שחורה נוחה במיוחד', '/uploads/blackchair.png', 200, 250, '2025-05-10 00:00:00', '2025-05-16 00:00:00', 'closed', 3, 5, 9);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (7, 'מיקרוגל', 'מיקרוגל בצבע לבן ', '/uploads/micro.png', 220, 250, '2025-04-28 00:00:00', '2025-05-27 00:00:00', 'closed', 5, 7, 10);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (12, 'שולחן', 'שולחן מעץ מלא נפתח ל12 סועדים מחיר מציעה', '/uploads/table1.png', 350, 370, '2025-06-04 00:00:00', '2025-12-10 00:00:00', 'active', 6, 12, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (13, 'רבסל', 'רבסל 5 מדפים מתכת חזק במיוחד', '/uploads/ravsal.png', 320, 320, '2025-05-29 00:00:00', '2025-07-10 00:00:00', 'active', 4, 13, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (17, 'jkl', 'lklkjljk', '/uploads/a68873c1-1a45-4b5e-a828-d9f80c0b37ed_צילום מסך 2024-03-19 210248.png', 50, 50, '2025-05-28 00:00:00', '2025-06-11 00:00:00', 'active', 4, 17, NULL);
INSERT INTO public.items (item_id, title, description, image_url, starting_price, current_price, start_date, end_date, status, user_id, id, winner_offer_id) VALUES (18, 'תמונה', 'תמונה לקישוט חלל', '/uploads/32cbd9d0-698c-40b0-a76a-54fb5d843545_צילום מסך 2024-09-04 220043.png', 50, 50, '2025-05-29 00:00:00', '2025-06-25 00:00:00', 'active', 4, 18, NULL);


--
-- Data for Name: offers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (1, 5, '2025-05-22 13:41:09.557905', 3, 5, 1);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (2, 210, '2025-05-22 13:55:24.339734', 3, 2, 2);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (3, 220, '2025-05-22 19:25:20.90994', 3, 2, 3);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (5, 15, '2025-05-25 13:19:56.369012', 4, 1, 5);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (6, 25, '2025-05-25 13:20:01.129578', 4, 1, 6);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (7, 7, '2025-05-26 22:34:37.911443', 4, 5, 7);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (8, 270, '2025-05-27 11:35:59.736619', 9, 2, 8);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (9, 12, '2025-05-27 11:37:16.149273', 10, 5, 9);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (10, 250, '2025-05-27 23:44:50.999761', 3, 7, 10);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (11, 18, '2025-05-28 21:48:47.366735', 5, 3, 11);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (12, 60, '2025-05-28 21:48:57.879958', 5, 10, 12);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (4, 800, '2025-05-25 13:19:45.467659', 4, 2, 4);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (13, 370, '2025-05-28 22:22:23.9131', 3, 12, 13);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (14, 70, '2025-05-28 22:22:35.99076', 3, 10, 14);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (15, 260, '2025-05-28 22:23:30.5065', 3, 6, 15);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (16, 675, '2025-05-28 22:23:43.238646', 3, 11, 16);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (17, 23, '2025-05-28 22:24:55.509266', 4, 3, 17);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (18, 75, '2025-05-28 22:25:12.266467', 4, 10, 18);
INSERT INTO public.offers (offers_id, amount, offer_time, user_id, item_id, id) VALUES (19, 550, '2025-05-28 22:25:26.219455', 4, 8, 19);


--
-- Name: items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_id_seq', 18, true);


--
-- Name: items_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_item_id_seq', 18, true);


--
-- Name: offers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.offers_id_seq', 19, true);


--
-- Name: offers_offers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.offers_offers_id_seq', 19, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 10, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 10, true);


--
-- PostgreSQL database dump complete
--

