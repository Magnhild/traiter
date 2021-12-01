--
-- PostgreSQL database dump
--

-- Dumped from database version 10.16
-- Dumped by pg_dump version 13.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- Name: tacategory; Type: TABLE; Schema: public; Owner: chef
--

CREATE TABLE public.tacategory (
    id integer NOT NULL,
    label character varying(30) NOT NULL
);


ALTER TABLE public.tacategory OWNER TO chef;

--
-- Name: tacategory_id_seq; Type: SEQUENCE; Schema: public; Owner: chef
--

CREATE SEQUENCE public.tacategory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tacategory_id_seq OWNER TO chef;

--
-- Name: tacategory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chef
--

ALTER SEQUENCE public.tacategory_id_seq OWNED BY public.tacategory.id;


--
-- Name: tanature; Type: TABLE; Schema: public; Owner: chef
--

CREATE TABLE public.tanature (
    id integer NOT NULL,
    label character varying(30) NOT NULL,
    code smallint NOT NULL
);


ALTER TABLE public.tanature OWNER TO chef;

--
-- Name: tanature_id_seq; Type: SEQUENCE; Schema: public; Owner: chef
--

CREATE SEQUENCE public.tanature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tanature_id_seq OWNER TO chef;

--
-- Name: tanature_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chef
--

ALTER SEQUENCE public.tanature_id_seq OWNED BY public.tanature.id;


--
-- Name: tatrait; Type: TABLE; Schema: public; Owner: chef
--

CREATE TABLE public.tatrait (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    category_id integer NOT NULL,
    nature_id integer NOT NULL
);


ALTER TABLE public.tatrait OWNER TO chef;

--
-- Name: tatrait_id_seq; Type: SEQUENCE; Schema: public; Owner: chef
--

CREATE SEQUENCE public.tatrait_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tatrait_id_seq OWNER TO chef;

--
-- Name: tatrait_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chef
--

ALTER SEQUENCE public.tatrait_id_seq OWNED BY public.tatrait.id;


--
-- Name: tacategory id; Type: DEFAULT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tacategory ALTER COLUMN id SET DEFAULT nextval('public.tacategory_id_seq'::regclass);


--
-- Name: tanature id; Type: DEFAULT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tanature ALTER COLUMN id SET DEFAULT nextval('public.tanature_id_seq'::regclass);


--
-- Name: tatrait id; Type: DEFAULT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tatrait ALTER COLUMN id SET DEFAULT nextval('public.tatrait_id_seq'::regclass);


--
-- Data for Name: tacategory; Type: TABLE DATA; Schema: public; Owner: chef
--

COPY public.tacategory (id, label) FROM stdin;
1	Lifestyle
2	Social
3	Emotional
4	Hobby
\.


--
-- Data for Name: tanature; Type: TABLE DATA; Schema: public; Owner: chef
--

COPY public.tanature (id, label, code) FROM stdin;
1	Positive	1
2	Neutral	0
3	Negative	-1
\.


--
-- Data for Name: tatrait; Type: TABLE DATA; Schema: public; Owner: chef
--

COPY public.tatrait (id, name, category_id, nature_id) FROM stdin;
2	Active	1	1
4	Outgoing	2	1
5	Cheerful	3	1
6	Creative	3	1
7	Genius	3	1
3	Mean	2	3
8	Gloomy	3	3
9	Goofball	3	1
11	Romantic	3	1
12	Self-assured	3	1
10	Hot-headed	3	3
13	Unflirty	3	3
14	Art Lover	4	2
15	Bookworm	4	2
16	Foodie	4	2
17	Geek	4	2
18	Music Lover	4	2
19	Perfectionist	4	1
20	Ambitious	1	1
21	Childish	1	3
22	Clumsy	1	3
23	Dance Machine	1	2
24	Glutton	1	3
25	Insane	1	3
26	Kleptomaniac	1	3
27	Lazy	1	3
28	Loves Outdoors	1	2
29	Materialistic	1	3
30	Neat	1	1
31	Slob	1	3
32	Snob	1	3
33	Squeamish	1	3
34	Vegetarian	1	2
35	Bro	2	1
36	Evil	2	3
37	Family-oriented	2	1
38	Good	2	1
39	Hates Children	2	3
40	Insider	2	1
41	Jealous	2	3
42	Loner	2	3
43	Noncommittal	2	3
\.


--
-- Name: tacategory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chef
--

SELECT pg_catalog.setval('public.tacategory_id_seq', 4, true);


--
-- Name: tanature_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chef
--

SELECT pg_catalog.setval('public.tanature_id_seq', 3, true);


--
-- Name: tatrait_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chef
--

SELECT pg_catalog.setval('public.tatrait_id_seq', 43, true);


--
-- Name: tacategory tacategory_pkey; Type: CONSTRAINT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tacategory
    ADD CONSTRAINT tacategory_pkey PRIMARY KEY (id);


--
-- Name: tanature tanature_pkey; Type: CONSTRAINT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tanature
    ADD CONSTRAINT tanature_pkey PRIMARY KEY (id);


--
-- Name: tatrait tatrait_pkey; Type: CONSTRAINT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tatrait
    ADD CONSTRAINT tatrait_pkey PRIMARY KEY (id);


--
-- Name: tatrait tatrait_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tatrait
    ADD CONSTRAINT tatrait_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.tacategory(id);


--
-- Name: tatrait tatrait_nature_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: chef
--

ALTER TABLE ONLY public.tatrait
    ADD CONSTRAINT tatrait_nature_id_fkey FOREIGN KEY (nature_id) REFERENCES public.tanature(id);


--
-- PostgreSQL database dump complete
--

