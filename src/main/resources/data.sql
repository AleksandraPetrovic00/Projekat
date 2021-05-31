INSERT INTO FITNES_CENTAR (naziv, adresa, broj_telefona_centrale, email) VALUES ('The Workout Zone', 'Zlatne Grede 4', '011200300', 'twz@gmail.com');
INSERT INTO FITNES_CENTAR (naziv, adresa, broj_telefona_centrale, email) VALUES ('Athlemotive', 'Strazilovska 12', '021255255', 'althlemotive@gmail.com');

INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('aleksandrap', '000719', 'Aleksandra', 'Petrovic', '0658848889', 'aleksandrap1986@gmail.com', '19.07.2000.', 'ADMINISTRATOR', 'TRUE', 1);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('brkicee', 'elib2r1', 'Elena', 'Brkic', '0633672330', 'brkic.elena@gmail.com', '27.08.2000.', 'CLAN', 'TRUE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('nadjakanjuh', 'glasnogovornica','Nadja', 'Kanjuh', '0600797566','nadjakanjuh@gmail.com', '24.07.2000.','TRENER', 'TRUE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('markovasilic', 'marko','Marko', 'Vasilic', '0635697566','markovasilic@gmail.com', '15.09.1998.','CLAN', 'TRUE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('natkapatka', 'bijelopolje','Natasa', 'Djacic', '065214589','djacicnatasa@gmail.com', '06.02.2000.','CLAN', 'FALSE', 1);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('spankistepa', 'srb123','Stefan', 'Mladenovic', '066427302','stefanmladen@gmail.com', '03.11.2000.','CLAN', 'TRUE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('nikoladzoni', '1301n1k','Nikola', 'Petrovic', '065214546','nikolap@gmail.com', '13.01.2006.','TRENER', 'TRUE', 1);

INSERT INTO TRENING (naziv, opis, tip_treninga, trajanje, korisnik_trening_id) VALUES ('Trening1', 'Full body workout','HIIT', '120', 3);
INSERT INTO TRENING (naziv, opis, tip_treninga, trajanje, korisnik_trening_id) VALUES ('Trening2', 'Gluteus&Core','CORE', '120', 3);
INSERT INTO TRENING (naziv, opis, tip_treninga, trajanje, korisnik_trening_id) VALUES ('Trening3', 'Tone Up','HIIT', '120', 7);

INSERT INTO SALA (kapacitet, oznaka_sale, fitnescentar_sala_id) VALUES ('20', 'A1', 1);

INSERT INTO OCENA (ocena, korisnik_ocena_id, termin_ocena_id) VALUES ('10', 5, 1);

INSERT INTO TERMIN (broj_prijavljenih_clanova, vreme_odrzavanja, cena, sala_termin_id, trening_termin_id) VALUES ('10', 'Ponedeljak 10.00', '500', 1, 3);

INSERT INTO ODRADJENI_TRENING (odradjeni_trening_termin_id, odradjeni_trening_clan_id) VALUES (1, 2);


