--
-- PostgreSQL database dump
--

\restrict xXdBiu9VMMTS37FmZeadXZrFnXhUCOjdRN8ilmlH2JojVxOCd8oXuVT3ODDTqBL

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

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

ALTER TABLE IF EXISTS ONLY public.tarea_rol DROP CONSTRAINT IF EXISTS tarea_rol_tareaid_fkey;
ALTER TABLE IF EXISTS ONLY public.tarea_rol DROP CONSTRAINT IF EXISTS tarea_rol_idrol_fkey;
ALTER TABLE IF EXISTS ONLY public.socioasistencia DROP CONSTRAINT IF EXISTS socioasistencia_idsocio_fkey;
ALTER TABLE IF EXISTS ONLY public.socioasistencia DROP CONSTRAINT IF EXISTS socioasistencia_idasistencia_fkey;
ALTER TABLE IF EXISTS ONLY public.rolsocio DROP CONSTRAINT IF EXISTS rolsocio_idsocio_fkey;
ALTER TABLE IF EXISTS ONLY public.rolsocio DROP CONSTRAINT IF EXISTS rolsocio_idrol_fkey;
ALTER TABLE IF EXISTS ONLY public.pagos DROP CONSTRAINT IF EXISTS pagos_iddeuda_fkey;
ALTER TABLE IF EXISTS ONLY public.movimiento DROP CONSTRAINT IF EXISTS movimiento_idsocio_fkey;
ALTER TABLE IF EXISTS ONLY public.familiares DROP CONSTRAINT IF EXISTS familiares_idsocio_fkey;
ALTER TABLE IF EXISTS ONLY public.deuda DROP CONSTRAINT IF EXISTS deuda_idtipodeuda_fkey;
ALTER TABLE IF EXISTS ONLY public.deuda DROP CONSTRAINT IF EXISTS deuda_idsocioasistencia_fkey;
ALTER TABLE IF EXISTS ONLY public.tipodeuda DROP CONSTRAINT IF EXISTS tipodeuda_pkey;
ALTER TABLE IF EXISTS ONLY public.tarea_rol DROP CONSTRAINT IF EXISTS tarea_rol_pkey;
ALTER TABLE IF EXISTS ONLY public.tarea DROP CONSTRAINT IF EXISTS tarea_pkey;
ALTER TABLE IF EXISTS ONLY public.socios DROP CONSTRAINT IF EXISTS socios_pkey;
ALTER TABLE IF EXISTS ONLY public.socios DROP CONSTRAINT IF EXISTS socios_numero_carnet_key;
ALTER TABLE IF EXISTS ONLY public.socios DROP CONSTRAINT IF EXISTS socios_dni_key;
ALTER TABLE IF EXISTS ONLY public.socioasistencia DROP CONSTRAINT IF EXISTS socioasistencia_pkey;
ALTER TABLE IF EXISTS ONLY public.rolsocio DROP CONSTRAINT IF EXISTS rolsocio_pkey;
ALTER TABLE IF EXISTS ONLY public.rol DROP CONSTRAINT IF EXISTS rol_pkey;
ALTER TABLE IF EXISTS ONLY public.pagos DROP CONSTRAINT IF EXISTS pagos_pkey;
ALTER TABLE IF EXISTS ONLY public.movimiento DROP CONSTRAINT IF EXISTS movimiento_pkey;
ALTER TABLE IF EXISTS ONLY public.familiares DROP CONSTRAINT IF EXISTS familiares_pkey;
ALTER TABLE IF EXISTS ONLY public.deuda DROP CONSTRAINT IF EXISTS deuda_pkey;
ALTER TABLE IF EXISTS ONLY public.asistencia DROP CONSTRAINT IF EXISTS asistencia_pkey;
ALTER TABLE IF EXISTS public.tipodeuda ALTER COLUMN idtipodeuda DROP DEFAULT;
ALTER TABLE IF EXISTS public.tarea_rol ALTER COLUMN idroltarea DROP DEFAULT;
ALTER TABLE IF EXISTS public.tarea ALTER COLUMN tareaid DROP DEFAULT;
ALTER TABLE IF EXISTS public.socios ALTER COLUMN idsocio DROP DEFAULT;
ALTER TABLE IF EXISTS public.socioasistencia ALTER COLUMN idsocioasistencia DROP DEFAULT;
ALTER TABLE IF EXISTS public.rolsocio ALTER COLUMN idrolsocio DROP DEFAULT;
ALTER TABLE IF EXISTS public.rol ALTER COLUMN idrol DROP DEFAULT;
ALTER TABLE IF EXISTS public.pagos ALTER COLUMN idpago DROP DEFAULT;
ALTER TABLE IF EXISTS public.movimiento ALTER COLUMN idmovimiento DROP DEFAULT;
ALTER TABLE IF EXISTS public.familiares ALTER COLUMN idfamiliares DROP DEFAULT;
ALTER TABLE IF EXISTS public.deuda ALTER COLUMN iddeuda DROP DEFAULT;
ALTER TABLE IF EXISTS public.asistencia ALTER COLUMN idasistencia DROP DEFAULT;
DROP SEQUENCE IF EXISTS public.tipodeuda_idtipodeuda_seq;
DROP TABLE IF EXISTS public.tipodeuda;
DROP SEQUENCE IF EXISTS public.tarea_tareaid_seq;
DROP SEQUENCE IF EXISTS public.tarea_rol_idroltarea_seq;
DROP TABLE IF EXISTS public.tarea_rol;
DROP TABLE IF EXISTS public.tarea;
DROP SEQUENCE IF EXISTS public.socios_idsocio_seq;
DROP TABLE IF EXISTS public.socios;
DROP SEQUENCE IF EXISTS public.socioasistencia_idsocioasistencia_seq;
DROP TABLE IF EXISTS public.socioasistencia;
DROP SEQUENCE IF EXISTS public.rolsocio_idrolsocio_seq;
DROP TABLE IF EXISTS public.rolsocio;
DROP SEQUENCE IF EXISTS public.rol_idrol_seq;
DROP TABLE IF EXISTS public.rol;
DROP SEQUENCE IF EXISTS public.pagos_idpago_seq;
DROP TABLE IF EXISTS public.pagos;
DROP SEQUENCE IF EXISTS public.movimiento_idmovimiento_seq;
DROP TABLE IF EXISTS public.movimiento;
DROP SEQUENCE IF EXISTS public.familiares_idfamiliares_seq;
DROP TABLE IF EXISTS public.familiares;
DROP SEQUENCE IF EXISTS public.deuda_iddeuda_seq;
DROP TABLE IF EXISTS public.deuda;
DROP SEQUENCE IF EXISTS public.asistencia_idasistencia_seq;
DROP TABLE IF EXISTS public.asistencia;
DROP EXTENSION IF EXISTS pgcrypto;
--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: asistencia; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.asistencia (
    idasistencia integer NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    tipoevento character varying(255),
    descripcion character varying(255)
);


--
-- Name: asistencia_idasistencia_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.asistencia_idasistencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: asistencia_idasistencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.asistencia_idasistencia_seq OWNED BY public.asistencia.idasistencia;


--
-- Name: deuda; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.deuda (
    iddeuda integer NOT NULL,
    idsocioasistencia integer NOT NULL,
    idtipodeuda integer NOT NULL,
    fecha date NOT NULL,
    monto numeric(38,2) NOT NULL,
    estado character varying(255)
);


--
-- Name: deuda_iddeuda_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.deuda_iddeuda_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: deuda_iddeuda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.deuda_iddeuda_seq OWNED BY public.deuda.iddeuda;


--
-- Name: familiares; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.familiares (
    idfamiliares integer NOT NULL,
    idsocio integer NOT NULL,
    nombrefamiliar character varying(100) NOT NULL,
    apellidopaternofamiliar character varying(255),
    apellidomaternofamiliar character varying(255),
    parentesco character varying(255) NOT NULL,
    telefono character varying(255) NOT NULL,
    dni character varying(255) NOT NULL,
    essocio boolean DEFAULT false NOT NULL
);


--
-- Name: familiares_idfamiliares_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.familiares_idfamiliares_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: familiares_idfamiliares_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.familiares_idfamiliares_seq OWNED BY public.familiares.idfamiliares;


--
-- Name: movimiento; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.movimiento (
    idmovimiento integer NOT NULL,
    idsocio integer NOT NULL,
    tipo character varying(255),
    descripcion character varying(255),
    monto numeric(38,2) NOT NULL,
    fecha date NOT NULL
);


--
-- Name: movimiento_idmovimiento_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.movimiento_idmovimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: movimiento_idmovimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.movimiento_idmovimiento_seq OWNED BY public.movimiento.idmovimiento;


--
-- Name: pagos; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.pagos (
    idpago integer NOT NULL,
    iddeuda integer NOT NULL,
    fecha date NOT NULL,
    monto numeric(38,2) NOT NULL,
    carnet_registrador character varying(255)
);


--
-- Name: pagos_idpago_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.pagos_idpago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: pagos_idpago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.pagos_idpago_seq OWNED BY public.pagos.idpago;


--
-- Name: rol; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rol (
    idrol integer NOT NULL,
    nombrerol character varying(255) NOT NULL
);


--
-- Name: rol_idrol_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rol_idrol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.rol_idrol_seq OWNED BY public.rol.idrol;


--
-- Name: rolsocio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rolsocio (
    idrolsocio integer NOT NULL,
    idrol integer NOT NULL,
    idsocio integer NOT NULL,
    fechainicio date NOT NULL,
    fechafin date
);


--
-- Name: rolsocio_idrolsocio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rolsocio_idrolsocio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rolsocio_idrolsocio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.rolsocio_idrolsocio_seq OWNED BY public.rolsocio.idrolsocio;


--
-- Name: socioasistencia; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.socioasistencia (
    idsocioasistencia integer NOT NULL,
    idsocio integer NOT NULL,
    idasistencia integer NOT NULL,
    estado character varying(255),
    puntualidad character varying(255),
    motivo character varying(255)
);


--
-- Name: socioasistencia_idsocioasistencia_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.socioasistencia_idsocioasistencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: socioasistencia_idsocioasistencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.socioasistencia_idsocioasistencia_seq OWNED BY public.socioasistencia.idsocioasistencia;


--
-- Name: socios; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.socios (
    idsocio integer NOT NULL,
    nombresocio character varying(100) NOT NULL,
    apellidopaternosocio character varying(255) NOT NULL,
    apellidomaternosocio character varying(255),
    direccion character varying(255),
    distrito character varying(255),
    estadocivil character varying(255),
    dni character varying(8),
    numero_carnet character varying(255),
    telefono character varying(255),
    contrasena character varying(255),
    estado character varying(255),
    tipo character varying(255),
    correo character varying(255) NOT NULL,
    correo_verificado boolean,
    cuenta_bloqueada boolean,
    fecha_bloqueo timestamp(6) without time zone,
    intentos_fallidos integer,
    token character varying(255)
);


--
-- Name: socios_idsocio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.socios_idsocio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: socios_idsocio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.socios_idsocio_seq OWNED BY public.socios.idsocio;


--
-- Name: tarea; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tarea (
    tareaid integer NOT NULL,
    nombre character varying(100) NOT NULL
);


--
-- Name: tarea_rol; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tarea_rol (
    idroltarea integer NOT NULL,
    idrol integer NOT NULL,
    tareaid integer NOT NULL
);


--
-- Name: tarea_rol_idroltarea_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tarea_rol_idroltarea_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tarea_rol_idroltarea_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tarea_rol_idroltarea_seq OWNED BY public.tarea_rol.idroltarea;


--
-- Name: tarea_tareaid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tarea_tareaid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tarea_tareaid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tarea_tareaid_seq OWNED BY public.tarea.tareaid;


--
-- Name: tipodeuda; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tipodeuda (
    idtipodeuda integer NOT NULL,
    nombre character varying(255) NOT NULL,
    monto numeric(38,2) NOT NULL
);


--
-- Name: tipodeuda_idtipodeuda_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tipodeuda_idtipodeuda_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tipodeuda_idtipodeuda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tipodeuda_idtipodeuda_seq OWNED BY public.tipodeuda.idtipodeuda;


--
-- Name: asistencia idasistencia; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.asistencia ALTER COLUMN idasistencia SET DEFAULT nextval('public.asistencia_idasistencia_seq'::regclass);


--
-- Name: deuda iddeuda; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deuda ALTER COLUMN iddeuda SET DEFAULT nextval('public.deuda_iddeuda_seq'::regclass);


--
-- Name: familiares idfamiliares; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.familiares ALTER COLUMN idfamiliares SET DEFAULT nextval('public.familiares_idfamiliares_seq'::regclass);


--
-- Name: movimiento idmovimiento; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.movimiento ALTER COLUMN idmovimiento SET DEFAULT nextval('public.movimiento_idmovimiento_seq'::regclass);


--
-- Name: pagos idpago; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.pagos ALTER COLUMN idpago SET DEFAULT nextval('public.pagos_idpago_seq'::regclass);


--
-- Name: rol idrol; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rol ALTER COLUMN idrol SET DEFAULT nextval('public.rol_idrol_seq'::regclass);


--
-- Name: rolsocio idrolsocio; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rolsocio ALTER COLUMN idrolsocio SET DEFAULT nextval('public.rolsocio_idrolsocio_seq'::regclass);


--
-- Name: socioasistencia idsocioasistencia; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socioasistencia ALTER COLUMN idsocioasistencia SET DEFAULT nextval('public.socioasistencia_idsocioasistencia_seq'::regclass);


--
-- Name: socios idsocio; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socios ALTER COLUMN idsocio SET DEFAULT nextval('public.socios_idsocio_seq'::regclass);


--
-- Name: tarea tareaid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarea ALTER COLUMN tareaid SET DEFAULT nextval('public.tarea_tareaid_seq'::regclass);


--
-- Name: tarea_rol idroltarea; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarea_rol ALTER COLUMN idroltarea SET DEFAULT nextval('public.tarea_rol_idroltarea_seq'::regclass);


--
-- Name: tipodeuda idtipodeuda; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipodeuda ALTER COLUMN idtipodeuda SET DEFAULT nextval('public.tipodeuda_idtipodeuda_seq'::regclass);


--
-- Data for Name: asistencia; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.asistencia (idasistencia, fecha, tipoevento, descripcion) FROM stdin;
200	2025-10-15 00:00:00	Asamblea Extraordinaria	Revisión de cuentas del mes y planificación anual
201	2026-10-10 14:00:00	AsambleaOrdinaria	Tema de aprobación de nueva estrategía
202	2025-12-17 13:57:48.637815	Asistencia	Asistencia a los socios todos los días
203	2025-12-17 13:57:59.87106	Asistencia	Asistencia a los socios todos los días
204	2025-12-17 13:58:22.470667	Asistencia	Asistencia a los socios todos los días
205	2025-12-18 18:29:22.76159	Asistencia	Asistencia a los socios todos los días
206	2025-12-18 19:12:23.635676	Asistencia	Asistencia a los socios todos los días
207	2025-12-19 12:27:00	AsambleaOrdinaria	Prueba de asistencia
\.


--
-- Data for Name: deuda; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.deuda (iddeuda, idsocioasistencia, idtipodeuda, fecha, monto, estado) FROM stdin;
1002	5014	2	2025-10-16	50.00	Pendiente
1001	5013	2	2025-10-16	50.00	Parcial
1003	5103	5	2025-12-18	50.00	Pendiente
1004	5104	5	2025-12-18	50.00	Pendiente
1005	5105	5	2025-12-18	50.00	Pendiente
1006	5106	5	2025-12-18	50.00	Pendiente
1007	5107	5	2025-12-18	50.00	Pendiente
1008	5108	5	2025-12-18	50.00	Pendiente
1009	5109	5	2025-12-18	50.00	Pendiente
1010	5110	5	2025-12-18	50.00	Pendiente
1011	5111	5	2025-12-18	50.00	Pendiente
1012	5112	5	2025-12-18	50.00	Pendiente
1013	5113	5	2025-12-18	50.00	Pendiente
1014	5114	5	2025-12-18	50.00	Pendiente
1015	5115	5	2025-12-18	50.00	Pendiente
1016	5116	5	2025-12-18	50.00	Pendiente
1017	5117	5	2025-12-18	50.00	Pendiente
1018	5118	5	2025-12-18	50.00	Pendiente
1020	5120	5	2025-12-18	50.00	Pendiente
1021	5121	5	2025-12-18	50.00	Pendiente
1019	5119	5	2025-12-18	50.00	Pagado
1022	5125	5	2025-12-19	50.00	Pendiente
1023	5126	5	2025-12-19	50.00	Pendiente
1024	5127	5	2025-12-19	50.00	Pendiente
1025	5128	5	2025-12-19	50.00	Pendiente
1026	5129	5	2025-12-19	50.00	Pendiente
1027	5130	5	2025-12-19	50.00	Pendiente
1028	5131	5	2025-12-19	50.00	Pendiente
1029	5132	5	2025-12-19	50.00	Pendiente
1030	5133	5	2025-12-19	50.00	Pendiente
1031	5134	5	2025-12-19	50.00	Pendiente
1032	5135	5	2025-12-19	50.00	Pendiente
1033	5136	5	2025-12-19	50.00	Pendiente
1034	5137	5	2025-12-19	50.00	Pendiente
1035	5138	5	2025-12-19	50.00	Pendiente
1036	5139	5	2025-12-19	50.00	Pendiente
1037	5140	5	2025-12-19	50.00	Pendiente
1038	5141	5	2025-12-19	50.00	Pendiente
1039	5142	5	2025-12-19	50.00	Pendiente
1040	5143	5	2025-12-19	50.00	Pendiente
1041	5144	5	2025-12-19	50.00	Pendiente
\.


--
-- Data for Name: familiares; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.familiares (idfamiliares, idsocio, nombrefamiliar, apellidopaternofamiliar, apellidomaternofamiliar, parentesco, telefono, dni, essocio) FROM stdin;
1	1	Carmela	Vargas	Llosa	Esposa	999000111	40000001	f
2	1	Junior	Vargas	Llosa	Hijo	999000222	60000001	t
3	7	Doña	Florinda	Meza	Madre	999000333	40000002	f
4	11	Antonela	Roccuzzo	Messi	Esposa	999000444	40000003	f
\.


--
-- Data for Name: movimiento; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.movimiento (idmovimiento, idsocio, tipo, descripcion, monto, fecha) FROM stdin;
1	1	Ingreso	Pago de cuota mensual Octubre	20.00	2025-10-01
2	2	Ingreso	Pago de cuota mensual Octubre	20.00	2025-10-02
3	3	Egreso	Compra de útiles de limpieza	50.00	2025-10-05
4	13	Ingreso	Pago parcial de multa anterior	20.00	2025-10-10
5	22	Egreso	monto representativo	10.00	2025-12-17
6	22	Ingreso	DONATIVOnAVIDAD	10.00	2025-12-18
\.


--
-- Data for Name: pagos; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.pagos (idpago, iddeuda, fecha, monto, carnet_registrador) FROM stdin;
1	1001	2025-10-20	20.00	\N
2	1019	2025-12-18	50.00	C-003
\.


--
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.rol (idrol, nombrerol) FROM stdin;
1	Presidente
2	Tesorera
3	Secretaria
4	Dirigente
6	Vocal
7	Socio
5	Delegadoporrubro
\.


--
-- Data for Name: rolsocio; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.rolsocio (idrolsocio, idrol, idsocio, fechainicio, fechafin) FROM stdin;
1	1	1	2025-01-01	\N
2	2	2	2025-01-01	\N
4	4	4	2025-01-01	\N
5	5	5	2025-01-01	\N
6	6	6	2025-01-01	\N
7	7	7	2025-01-01	\N
8	7	8	2025-01-01	\N
9	7	9	2025-01-01	\N
10	7	10	2025-01-01	\N
11	7	11	2025-01-01	\N
12	7	12	2025-01-01	\N
13	7	13	2025-01-01	\N
14	7	14	2025-01-01	\N
15	7	15	2025-01-01	\N
16	7	16	2025-01-01	\N
17	7	17	2025-01-01	\N
18	7	18	2025-01-01	\N
28	6	25	2025-12-17	2029-12-17
29	6	25	2025-12-17	2029-12-17
19	4	20	2025-12-17	2029-12-17
25	1	21	2025-12-17	2029-12-17
26	2	22	2025-12-17	2029-12-17
27	4	23	2025-12-17	2029-12-17
3	3	3	2025-12-17	2029-12-17
30	5	24	2025-12-19	2029-12-19
\.


--
-- Data for Name: socioasistencia; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.socioasistencia (idsocioasistencia, idsocio, idasistencia, estado, puntualidad, motivo) FROM stdin;
5001	1	200	Asistió	Puntual	\N
5002	2	200	Asistió	Puntual	\N
5003	3	200	Asistió	Puntual	\N
5004	4	200	Asistió	Puntual	\N
5005	5	200	Asistió	Puntual	\N
5006	6	200	Asistió	Tarde	Tráfico en Javier Prado
5007	7	200	Falta	\N	Salud: Operación programada
5008	8	200	Falta	\N	Trabajo: Turno de guardia
5009	9	200	Falta	\N	Viaje: Fuera del país
5010	10	200	Falta	\N	Salud: Descanso médico
5011	11	200	Falta	\N	Personal: Fallecimiento familiar
5012	12	200	Falta	\N	Trabajo: Comisión de servicios
5013	13	200	Falta	\N	\N
5014	14	200	Falta	\N	\N
5015	1	202	Presente	Temprano	
5016	2	202	Ausente		
5017	4	202	Justificado		Viaje
5018	5	202	Ausente		
5019	6	202	Ausente		
5020	7	202	Ausente		
5021	8	202	Presente	Tardanza	
5022	9	202	Ausente		
5023	10	202	Ausente		
5024	11	202	Ausente		
5025	12	202	Ausente		
5026	13	202	Ausente		
5027	14	202	Ausente		
5028	15	202	Ausente		
5029	19	202	Ausente		
5030	20	202	Ausente		
5031	24	202	Ausente		
5032	21	202	Ausente		
5033	3	202	Ausente		
5034	22	202	Ausente		
5035	23	202	Ausente		
5036	25	202	Ausente		
5037	1	203	Presente	Temprano	
5038	2	203	Ausente		
5039	4	203	Justificado		Viaje
5040	5	203	Ausente		
5041	6	203	Ausente		
5042	7	203	Ausente		
5043	8	203	Presente	Tardanza	
5044	9	203	Ausente		
5045	10	203	Ausente		
5046	11	203	Ausente		
5047	12	203	Ausente		
5048	13	203	Ausente		
5049	14	203	Ausente		
5050	15	203	Ausente		
5051	19	203	Ausente		
5052	20	203	Ausente		
5053	24	203	Ausente		
5054	21	203	Ausente		
5055	3	203	Ausente		
5056	22	203	Ausente		
5057	23	203	Ausente		
5058	25	203	Ausente		
5059	1	204	Presente	Temprano	
5060	2	204	Ausente		
5061	4	204	Justificado		Viaje
5062	5	204	Ausente		
5063	6	204	Ausente		
5064	7	204	Ausente		
5065	8	204	Presente	Tardanza	
5066	9	204	Ausente		
5067	10	204	Ausente		
5068	11	204	Ausente		
5069	12	204	Ausente		
5070	13	204	Ausente		
5071	14	204	Ausente		
5072	15	204	Ausente		
5073	19	204	Ausente		
5074	20	204	Ausente		
5075	24	204	Ausente		
5076	21	204	Ausente		
5077	3	204	Ausente		
5078	22	204	Ausente		
5079	23	204	Ausente		
5080	25	204	Ausente		
5081	1	205	Presente	Temprano	
5082	2	205	Presente	Temprano	
5083	4	205	Justificado		Viaje
5084	5	205	Ausente		
5085	6	205	Presente	Temprano	
5086	7	205	Presente	Tardanza	
5087	8	205	Presente	Temprano	
5088	9	205	Presente	Temprano	
5089	10	205	Presente	Temprano	
5090	11	205	Presente	Temprano	
5091	12	205	Presente	Temprano	
5092	13	205	Presente	Temprano	
5093	14	205	Presente	Temprano	
5094	15	205	Presente	Temprano	
5095	19	205	Presente	Temprano	
5096	20	205	Presente	Temprano	
5097	24	205	Presente	Temprano	
5098	21	205	Presente	Temprano	
5099	3	205	Presente	Temprano	
5100	22	205	Presente	Temprano	
5101	23	205	Presente	Temprano	
5102	25	205	Presente	Temprano	
5103	1	206	Ausente		
5104	2	206	Ausente		
5105	4	206	Ausente		
5106	5	206	Ausente		
5107	6	206	Ausente		
5108	7	206	Ausente		
5109	8	206	Ausente		
5110	9	206	Ausente		
5111	10	206	Ausente		
5112	11	206	Ausente		
5113	12	206	Ausente		
5114	13	206	Ausente		
5115	14	206	Ausente		
5116	15	206	Ausente		
5117	19	206	Ausente		
5118	20	206	Ausente		
5119	24	206	Ausente		
5120	21	206	Ausente		
5121	3	206	Ausente		
5122	22	206	Justificado		Viaje
5123	23	206	Presente	Temprano	
5124	25	206	Presente	Tardanza	
5125	1	207	Ausente		
5126	2	207	Ausente		
5127	4	207	Ausente		
5128	5	207	Ausente		
5129	6	207	Ausente		
5130	7	207	Ausente		
5131	8	207	Ausente		
5132	9	207	Ausente		
5133	10	207	Ausente		
5134	11	207	Ausente		
5135	12	207	Ausente		
5136	13	207	Ausente		
5137	14	207	Ausente		
5138	15	207	Ausente		
5139	19	207	Ausente		
5140	20	207	Ausente		
5141	24	207	Ausente		
5142	21	207	Ausente		
5143	3	207	Ausente		
5144	22	207	Ausente		
5145	23	207	Justificado		Otro
5146	25	207	Presente	Tardanza	
5147	26	207	Presente	Temprano	
\.


--
-- Data for Name: socios; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.socios (idsocio, nombresocio, apellidopaternosocio, apellidomaternosocio, direccion, distrito, estadocivil, dni, numero_carnet, telefono, contrasena, estado, tipo, correo, correo_verificado, cuenta_bloqueada, fecha_bloqueo, intentos_fallidos, token) FROM stdin;
2	Maria	Soto	Gomez	Jr. Luna 202	Lince	Soltera	10000002	C-002	900111333	pass2	Activo	Regular	teso@asoc.com	\N	\N	\N	\N	\N
4	Pedro	Castillo	Diaz	Av. Peru 404	Lima	Divorciado	10000004	C-004	900111555	pass4	Activo	Regular	diri@asoc.com	\N	\N	\N	\N	\N
5	Juan	Perez	Albela	Jr. Ica 505	Breña	Soltero	10000005	C-005	900111666	pass5	Activo	Premium	dele@asoc.com	\N	\N	\N	\N	\N
6	Ana	Torres	Paz	Av. Arequipa 606	Lince	Viuda	10000006	C-006	900111777	pass6	Activo	Regular	vocal@asoc.com	\N	\N	\N	\N	\N
7	Roberto	Gomez	Bolaños	Vecindad 8	Mexico	Casado	10000007	C-007	900111888	pass7	Activo	Regular	socio1@asoc.com	\N	\N	\N	\N	\N
8	Flor	Linda	Hermosa	Campo 1	Comas	Soltera	10000008	C-008	900111999	pass8	Activo	Regular	socio2@asoc.com	\N	\N	\N	\N	\N
9	Luis	Miguel	Gallego	Sol 2	Miami	Divorciado	10000009	C-009	900111000	pass9	Activo	Premium	socio3@asoc.com	\N	\N	\N	\N	\N
10	Shakira	Mebarak	Ripoll	Barranquilla 1	Colombia	Soltera	10000010	C-010	900222111	pass10	Activo	Premium	socio4@asoc.com	\N	\N	\N	\N	\N
11	Lionel	Messi	Cuccittini	Rosario 10	Argentina	Casado	10000011	C-011	900222222	pass11	Activo	Premium	socio5@asoc.com	\N	\N	\N	\N	\N
12	Cristiano	Ronaldo	Dos Santos	Madeira 7	Portugal	Casado	10000012	C-012	900222333	pass12	Activo	Premium	socio6@asoc.com	\N	\N	\N	\N	\N
13	Neymar	Da Silva	Santos	Sao Paulo 11	Brasil	Soltero	10000013	C-013	900222444	pass13	Activo	Regular	socio7@asoc.com	\N	\N	\N	\N	\N
14	Kylian	Mbappe	Lottin	Paris 7	Francia	Soltero	10000014	C-014	900222555	pass14	Activo	Regular	socio8@asoc.com	\N	\N	\N	\N	\N
15	Erling	Haaland	Braut	Oslo 9	Noruega	Soltero	10000015	C-015	900222666	pass15	Activo	Regular	socio9@asoc.com	\N	\N	\N	\N	\N
16	Darth	Vader	Skywalker	Estrella Muerte 1	Espacio	Viudo	10000016	C-016	900666666	darkside	Bloqueado	Regular	bloq1@asoc.com	\N	\N	\N	\N	\N
17	Voldemort	Riddle	Tom	Mansion 1	Londres	Soltero	10000017	C-017	900777777	avada	Bloqueado	Regular	bloq2@asoc.com	\N	\N	\N	\N	\N
18	Sauron	Mordor	Ring	Torre 1	Mordor	Soltero	10000018	C-018	900888888	precious	Inactivo	Regular	inact@asoc.com	\N	\N	\N	\N	\N
19	Prueba01	ApellidoPaternoPrueba	ApellidoMaternoPrueba	av. 3 de octubre	Majes	Soltero	56784578	CARNET-100	789456123	$2a$10$DZjGT4jOEGYkqQ3GwOc/me63ZjbEjFSlgeM/BEf0hqWpLPqP73ejq	Activo	Interno	qwer@gmail.com	f	f	\N	0	5ce55e2e-9033-43ec-9c38-de762a31f1c0
20	Delegado1	Gonza		av.Marcoaurelio	Majes	soltero	12457845	CARNET-101	789456789	$2a$10$VCWHXaryrDm67S82LByhsObkq18VqgoxRvNAqdrrgRnBcTh0zwUUy	Activo	Interno	qwert@gmail.com	f	f	\N	0	5817a0e3-1675-4cb6-9260-4a95aded1c8b
24	Delegado	A	M	Pje Sta Cruz 104-101	Majes	Arequipa	78521475	CARNET-105	129467824	$2a$10$POZlk9lhh9ZGZPDnG1hFuOPA761xBn.7D.Znw6RC26kxLbIZ3JB1W	Activo	Interno	los1swords@gmail.com	f	f	\N	0	71252927-1f9c-492a-8052-00549f1470e8
21	Presidente01	ApellidoPaternoPrueba	M	av peru	Majes	Soltero	78455689	CARNET-102	7856231256	$2a$10$/eSQl6osiWxd/HWwJoKvFuaXAgC1Ga7mI6RWdexX5fR.S5RZVcZU.	Activo	Interno	qwer1@gmail.com	f	f	\N	0	b4d1a7a3-580d-47bf-bc5f-713aea046a16
3	Lucia	Mendez	Ruiz	Calle Mar 303	Surco	Casada	10000003	C-003	900111444	$2a$08$PzzY1aeVJIbJUx5QxxiawOTJ54bX9kgQW9Db6AW4X1FS8.6dZYPV.	Activo	Regular	secr@asoc.com	\N	f	\N	0	\N
22	Tesorera	p	m	Pje Sta Cruz 104-101	Majes	soltero	45658978	CARNET-103	123568978	$2a$10$RlmfdaOAGFDcxmPWd4ntbepVWolccMy9pqqu7ZxqWFxInCFOk5iNu	Activo	Interno	qwer2@gmail.com	f	f	\N	0	ccac3626-0da3-493a-a0e6-c7ab8b40d813
23	Dirigente	A	M	Pje Sta Cruz 104-101	Majes	Soltero	12967845	CARNET-104	123895673	$2a$10$kj8pPHxttDyABJtbAB8ueeM.y3ZAIINJKydDthsLqMBcG2AlUZQD2	Activo	Interno	qwer3@gmail.com	f	f	\N	0	517c168b-2cf0-4842-99ab-c2d89faa0576
25	Vocal	A	M	Pje Sta Cruz 104-101	Majes	Soltero	36452976	CARNET-106	75825252	$2a$10$.oN3I57gTw8yPzmG/SWj4.KqpiWfI5BejNWIwiThLchO4nmIDuxRa	Activo	Interno	qwer5@gmail.com	f	t	2025-12-17 13:59:42.377329	5	e5bcfc59-1fd3-4fb8-a586-3f07e2cfcb33
26	Socio01	P1	P2	Jr. Los Tulipanes 46	Majes	Soltero	45895678	CARNET-110	123456789	$2a$10$X559Qi.f7iK0c./VaTbbNO2kfhw8JHT2c8zpgyyF9uLWakQZiwTj2	Activo	Interno	sdfsdf@gmal.com	f	f	\N	0	0f3d8d74-3c8a-4dc6-8e57-266b9ab03189
1	Carlo	Vargas	Llosa	Av. Sol 101	Miraflores	Casado	10000001	C-001	900111222	pass1	Activo	Interno	presi@asoc.com	\N	\N	\N	\N	\N
27	Paco	Lalo	Gonza	av peru	Majes	soltero	78945623	CARNET-120	123456987	$2a$10$5zO/L9TkrC.Rmb.k2Yqet.d3.tryOwbNMFHEZnENpIRgUKCIi7nbO	Activo	Interno	asda@gmail.com	f	\N	\N	\N	ed8691e4-cf80-4a67-9afd-c15142b11bf8
\.


--
-- Data for Name: tarea; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.tarea (tareaid, nombre) FROM stdin;
1	Supervisión de obras
2	Cobro de cuotas
3	Redacción de actas
4	Organización de eventos
5	Limpieza del local
\.


--
-- Data for Name: tarea_rol; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.tarea_rol (idroltarea, idrol, tareaid) FROM stdin;
1	1	1
2	2	2
3	3	3
4	6	4
5	7	5
\.


--
-- Data for Name: tipodeuda; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.tipodeuda (idtipodeuda, nombre, monto) FROM stdin;
1	Cuota Mensual	20.00
2	Multa por Inasistencia Injustificada	50.00
4	Cuota de Ingreso	100.00
5	FaltarAsociacion	50.00
3	Multa por Tardanza	0.00
\.


--
-- Name: asistencia_idasistencia_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.asistencia_idasistencia_seq', 207, true);


--
-- Name: deuda_iddeuda_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.deuda_iddeuda_seq', 1041, true);


--
-- Name: familiares_idfamiliares_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.familiares_idfamiliares_seq', 4, true);


--
-- Name: movimiento_idmovimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.movimiento_idmovimiento_seq', 6, true);


--
-- Name: pagos_idpago_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.pagos_idpago_seq', 2, true);


--
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.rol_idrol_seq', 7, true);


--
-- Name: rolsocio_idrolsocio_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.rolsocio_idrolsocio_seq', 30, true);


--
-- Name: socioasistencia_idsocioasistencia_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.socioasistencia_idsocioasistencia_seq', 5147, true);


--
-- Name: socios_idsocio_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.socios_idsocio_seq', 27, true);


--
-- Name: tarea_rol_idroltarea_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tarea_rol_idroltarea_seq', 1, false);


--
-- Name: tarea_tareaid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tarea_tareaid_seq', 1, false);


--
-- Name: tipodeuda_idtipodeuda_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tipodeuda_idtipodeuda_seq', 2, true);


--
-- Name: asistencia asistencia_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.asistencia
    ADD CONSTRAINT asistencia_pkey PRIMARY KEY (idasistencia);


--
-- Name: deuda deuda_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deuda
    ADD CONSTRAINT deuda_pkey PRIMARY KEY (iddeuda);


--
-- Name: familiares familiares_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.familiares
    ADD CONSTRAINT familiares_pkey PRIMARY KEY (idfamiliares);


--
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (idmovimiento);


--
-- Name: pagos pagos_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.pagos
    ADD CONSTRAINT pagos_pkey PRIMARY KEY (idpago);


--
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);


--
-- Name: rolsocio rolsocio_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rolsocio
    ADD CONSTRAINT rolsocio_pkey PRIMARY KEY (idrolsocio);


--
-- Name: socioasistencia socioasistencia_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socioasistencia
    ADD CONSTRAINT socioasistencia_pkey PRIMARY KEY (idsocioasistencia);


--
-- Name: socios socios_dni_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socios
    ADD CONSTRAINT socios_dni_key UNIQUE (dni);


--
-- Name: socios socios_numero_carnet_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socios
    ADD CONSTRAINT socios_numero_carnet_key UNIQUE (numero_carnet);


--
-- Name: socios socios_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socios
    ADD CONSTRAINT socios_pkey PRIMARY KEY (idsocio);


--
-- Name: tarea tarea_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarea
    ADD CONSTRAINT tarea_pkey PRIMARY KEY (tareaid);


--
-- Name: tarea_rol tarea_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarea_rol
    ADD CONSTRAINT tarea_rol_pkey PRIMARY KEY (idroltarea);


--
-- Name: tipodeuda tipodeuda_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipodeuda
    ADD CONSTRAINT tipodeuda_pkey PRIMARY KEY (idtipodeuda);


--
-- Name: deuda deuda_idsocioasistencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deuda
    ADD CONSTRAINT deuda_idsocioasistencia_fkey FOREIGN KEY (idsocioasistencia) REFERENCES public.socioasistencia(idsocioasistencia);


--
-- Name: deuda deuda_idtipodeuda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.deuda
    ADD CONSTRAINT deuda_idtipodeuda_fkey FOREIGN KEY (idtipodeuda) REFERENCES public.tipodeuda(idtipodeuda);


--
-- Name: familiares familiares_idsocio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.familiares
    ADD CONSTRAINT familiares_idsocio_fkey FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);


--
-- Name: movimiento movimiento_idsocio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_idsocio_fkey FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);


--
-- Name: pagos pagos_iddeuda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.pagos
    ADD CONSTRAINT pagos_iddeuda_fkey FOREIGN KEY (iddeuda) REFERENCES public.deuda(iddeuda);


--
-- Name: rolsocio rolsocio_idrol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rolsocio
    ADD CONSTRAINT rolsocio_idrol_fkey FOREIGN KEY (idrol) REFERENCES public.rol(idrol);


--
-- Name: rolsocio rolsocio_idsocio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rolsocio
    ADD CONSTRAINT rolsocio_idsocio_fkey FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);


--
-- Name: socioasistencia socioasistencia_idasistencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socioasistencia
    ADD CONSTRAINT socioasistencia_idasistencia_fkey FOREIGN KEY (idasistencia) REFERENCES public.asistencia(idasistencia);


--
-- Name: socioasistencia socioasistencia_idsocio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.socioasistencia
    ADD CONSTRAINT socioasistencia_idsocio_fkey FOREIGN KEY (idsocio) REFERENCES public.socios(idsocio);


--
-- Name: tarea_rol tarea_rol_idrol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarea_rol
    ADD CONSTRAINT tarea_rol_idrol_fkey FOREIGN KEY (idrol) REFERENCES public.rol(idrol);


--
-- Name: tarea_rol tarea_rol_tareaid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tarea_rol
    ADD CONSTRAINT tarea_rol_tareaid_fkey FOREIGN KEY (tareaid) REFERENCES public.tarea(tareaid);


--
-- PostgreSQL database dump complete
--

\unrestrict xXdBiu9VMMTS37FmZeadXZrFnXhUCOjdRN8ilmlH2JojVxOCd8oXuVT3ODDTqBL

