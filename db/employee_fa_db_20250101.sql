--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-01-02 00:24:50

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
-- TOC entry 4906 (class 0 OID 16751)
-- Dependencies: 217
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (is_delete, created_date, updated_date, phone_number, email, full_name, password, id) FROM stdin;
f	2025-01-01 23:39:17.671503	\N	0909113114	admin@example.com	Dang Gia Duc	$2a$10$qr1kHR4KppSxeL8y0kjRN.FjP4jl4eJc5tvrsgAtF7nYmCgpnXHb6	c0a80104-9422-1bb6-8194-22bc5db20000
\.


--
-- TOC entry 4907 (class 0 OID 16760)
-- Dependencies: 218
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (is_delete, created_date, updated_date, name, description, id) FROM stdin;
f	2025-01-01 23:54:08.446313	\N	GST	Phong ban 1	c0a80104-9422-1bb6-8194-22c9f53f0001
f	2025-01-01 23:54:52.727514	\N	ADS	phong ban 3	c0a80104-9422-1bb6-8194-22caa2380005
f	2025-01-01 23:54:37.905805	2025-01-01 23:55:02.977174	AES	autosar department	c0a80104-9422-1bb6-8194-22ca68520003
f	2025-01-01 23:54:45.92766	2025-01-01 23:55:10.392357	AIS	autosar interagte department	c0a80104-9422-1bb6-8194-22ca87a70004
\.


--
-- TOC entry 4908 (class 0 OID 16771)
-- Dependencies: 219
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (base_salary, is_delete, created_date, date_of_birth, hire_date, updated_date, phone_number, email, full_name, job_title, address, department_id, id) FROM stdin;
5000000	f	2025-01-01 23:54:25.970713	2003-08-21 07:00:00	2025-01-01 07:00:00	\N	0909113114	baodt17@fpt.com	Duong Ton Bao	DEVE	Vung Tau	c0a80104-9422-1bb6-8194-22c9f53f0001	c0a80104-9422-1bb6-8194-22ca39b30002
5000000	f	2025-01-01 23:56:09.800526	2004-05-02 07:00:00	2024-01-01 07:00:00	\N	0921421727	anlmn@fpt.com	Luong Nguyen Minh An	AI	Long An	c0a80104-9422-1bb6-8194-22ca68520003	c0a80104-9422-1bb6-8194-22cbcf480006
5000000	f	2025-01-02 00:05:33.08732	2004-01-07 07:00:00	2024-12-01 07:00:00	\N	0909113114	hieupvt2@fpt.com	Pham Van Tuan Hieu	DEVE	Long An	c0a80104-9422-1bb6-8194-22ca68520003	c0a80104-9422-1bb6-8194-22d4679f0007
5000000	f	2025-01-02 00:09:25.913908	2003-02-17 14:00:00	2024-06-01 14:00:00	2025-01-02 00:14:10.917005	0909113114	ducdpg@fpt.com	Dang Phan Gia Duc	BA	Long An	c0a80104-9422-1bb6-8194-22ca68520003	c0a80104-9422-1bb6-8194-22d7f51a0008
\.


-- Completed on 2025-01-02 00:24:50

--
-- PostgreSQL database dump complete
--

